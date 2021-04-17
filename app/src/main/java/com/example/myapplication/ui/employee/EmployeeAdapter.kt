package com.example.myapplication.ui.employee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.myapplication.data.local.Employee
import com.example.myapplication.databinding.ItemEmployeeBinding

class EmployeeAdapter(
    private val glide: RequestManager,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding =
            ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val currentItem = employeeList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    inner class EmployeeViewHolder(private val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val employee = employeeList[position]
                        listener.onItemClick(employee)
                    }
                }
            }
        }

        fun bind(employee: Employee) {
            binding.apply {
                name.text = employee.full_name
                email.text = employee.email_address
                team.text = employee.team
                glide.load(employee.photo_url_large).into(employeeImage)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(employee: Employee)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Employee>() {
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var employeeList: List<Employee>
        get() = differ.currentList
        set(value) = differ.submitList(value)
}