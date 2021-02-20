package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.ui.base.BaseFragment
import com.example.myapplication.util.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)

        binding.apply {
            getEmployee.setOnClickListener {
                viewModel.onGetEmployeeClick()
            }
        }

        setFragmentResultListener("") { _, bundle ->
            val result = bundle.getInt("")
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.mainEvent.collect { event ->
                when (event) {
                    is MainViewModel.MainEvent.NavigateToEmployeeScreen -> {
                        val action = MainFragmentDirections.actionMainFragmentToEmployeeFragment()
                        findNavController().navigate(action)
                    }
                }.exhaustive
            }
        }
    }
}