package com.example.myapplication.ui.employee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.local.Employee
import com.example.myapplication.databinding.ItemEmployeeBinding

class EmployeeAdapter : ListAdapter<Employee, EmployeeAdapter.EmployeeViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding =
            ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class EmployeeViewHolder(private val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(employee: Employee) {
            binding.apply {
                name.text = employee.full_name
                email.text = employee.email_address
                team.text = employee.team
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Employee>() {
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee) =
            oldItem == newItem
    }
}