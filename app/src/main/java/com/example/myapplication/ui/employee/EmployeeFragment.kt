package com.example.myapplication.ui.employee

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.local.Employee
import com.example.myapplication.databinding.FragmentEmployeeBinding
import com.example.myapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class EmployeeFragment : BaseFragment(R.layout.fragment_employee),
    EmployeeAdapter.OnItemClickListener {

    private val viewModel: EmployeeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentEmployeeBinding.bind(view)

        val employeeAdapter = EmployeeAdapter(this)

        binding.apply {
            recyclerEmployee.apply {
                adapter = employeeAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    employeeAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                }
            }).attachToRecyclerView(recyclerEmployee)
        }

        viewModel.employee.observe(viewLifecycleOwner) {
            employeeAdapter.submitList(it)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.employeeEvent.collect { event ->
                when (event) {
                    is EmployeeViewModel.EmployeeEvent.TestMessage -> {
                        // TODO 
                    }
                }
            }
        }
    }

    override fun onItemClick(employee: Employee) {
        viewModel.onEmployeeSelected(employee)
    }
}
