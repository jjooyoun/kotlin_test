package com.example.myapplication.ui.employee

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.myapplication.R
import com.example.myapplication.data.local.Employee
import com.example.myapplication.databinding.FragmentEmployeeBinding
import com.example.myapplication.ui.base.BaseFragment
import com.example.myapplication.util.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class EmployeeFragment : BaseFragment(R.layout.fragment_employee),
    EmployeeAdapter.OnItemClickListener {

    @Inject
    lateinit var glide: RequestManager

    private val viewModel: EmployeeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentEmployeeBinding.bind(view)

        val employeeAdapter = EmployeeAdapter(glide, this)

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
                    val item = employeeAdapter.employeeList[viewHolder.adapterPosition]
                    viewModel.deleteEmployee(item)
                }
            }).attachToRecyclerView(recyclerEmployee)
        }

        viewModel.employee.observe(viewLifecycleOwner) {
            employeeAdapter.employeeList = it.data!!
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.employeeEvent.collect { event ->
                when (event) {
                    is EmployeeViewModel.EmployeeEvent.TestMessage -> {
                        // TODO
                        setFragmentResult(
                            "",
                            bundleOf()
                        )
                        findNavController().popBackStack()
                    }
                    EmployeeViewModel.EmployeeEvent.TestNavigateMessage -> {
                        val action = EmployeeFragmentDirections.actionGlobalCustomDialogFragment()
                        findNavController().navigate(action)
                    }
                }.exhaustive
            }
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onItemClick(employee: Employee) {
        viewModel.onEmployeeSelected(employee)
    }
}
