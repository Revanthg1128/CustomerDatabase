package com.sample.customerdatabase.adapter.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.customerdatabase.R
import com.sample.customerdatabase.databinding.LayoutRecyclerViewItemCustomerBinding
import com.sample.customerdatabase.extension.listen
import com.sample.customerdatabase.listener.OnRecyclerViewItemClick
import com.sample.data.model.CustomerItem

class CustomersRecyclerViewAdapter(private val onRecyclerViewItemClick: OnRecyclerViewItemClick?) :
    RecyclerView.Adapter<CustomersRecyclerViewAdapter.CustomersViewHolder>() {

    private var customerListItems = mutableListOf<CustomerItem>()

    inner class CustomersViewHolder(val binding: LayoutRecyclerViewItemCustomerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            LayoutRecyclerViewItemCustomerBinding.inflate(inflater, parent, false)
        return CustomersViewHolder(binding).listen { position, _ ->
            onRecyclerViewItemClick?.onItemClick(position)
        }
    }

    override fun onBindViewHolder(holder: CustomersViewHolder, position: Int) {
        holder.binding.tvCustomerName.text = holder.binding.root.context.getString(
            R.string.person_name,
            customerListItems[position].title.orEmpty(),
            customerListItems[position].firstName,
            customerListItems[position].lastName
        )
    }

    override fun getItemCount() = customerListItems.size

    fun setData(customerListItems: List<CustomerItem>) {
        this.customerListItems.clear()
        this.customerListItems.addAll(customerListItems)
        notifyDataSetChanged()
    }
}