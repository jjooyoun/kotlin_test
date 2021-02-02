package com.example.myapplication.ui.employee

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentEmployeeBinding
import com.example.myapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeFragment : BaseFragment(R.layout.fragment_employee) {

    private val viewModel: EmployeeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentEmployeeBinding.bind(view)

        val employeeAdapter = EmployeeAdapter()
        binding.apply {
            recyclerEmployee.apply {
                adapter = employeeAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.employee.observe(viewLifecycleOwner) {
            employeeAdapter.submitList(it)
        }
    }
}
