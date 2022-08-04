package com.sample.customerdatabase.ui.customerfragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import com.sample.customerdatabase.R
import com.sample.customerdatabase.adapter.recyclerviewadapter.CustomersRecyclerViewAdapter
import com.sample.customerdatabase.databinding.FragmentCustomerBinding
import com.sample.customerdatabase.listener.OnRecyclerViewItemClick
import com.sample.customerdatabase.ui.BaseFragment
import com.sample.data.model.CustomerItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerFragment : BaseFragment(), OnRecyclerViewItemClick {
    private val customerViewModel by viewModels<CustomerViewModel>()
    private var _binding: FragmentCustomerBinding? = null
    private val binding get() = _binding!!

    private val customerList = mutableListOf<CustomerItem>()
    private val adapter = CustomersRecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomerBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = customerViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initViews()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        customerViewModel.fetchCustomersFromDatabase()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_download) {
            customerViewModel.fetchCustomers()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
        // TODO: handle the customer details screen
    }

    private fun initViews() {
        binding.rvCustomers.adapter = adapter.apply {
            setData(customerList)
        }
    }

    private fun initObservers() {
        customerViewModel.response.observe(viewLifecycleOwner) {
            customerList.clear()
            customerList.addAll(it)
            adapter.setData(customerList)
        }
    }
}