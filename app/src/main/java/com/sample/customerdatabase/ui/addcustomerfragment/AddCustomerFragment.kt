package com.sample.customerdatabase.ui.addcustomerfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sample.customerdatabase.databinding.FragmentAddCustomerBinding
import com.sample.customerdatabase.ui.BaseFragment
import com.sample.database.db.customer.Customer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class AddCustomerFragment : BaseFragment() {

    private val addCustomerViewModel by viewModels<AddCustomerViewModel>()
    private var _binding: FragmentAddCustomerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCustomerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.btnAddCustomer.setOnClickListener {
            addCustomerViewModel.addCustomerToDatabase(
                Customer(
                    title = binding.etTitle.text.toString(),
                    firstName = binding.etFirstName.text.toString(),
                    lastName = binding.etLastName.text.toString()
                )
            )
        }

        binding.etFirstName.addTextChangedListener {
            updateCTAButton()
        }

        binding.etLastName.addTextChangedListener {
            updateCTAButton()
        }
    }

    private fun initObservers() {
        addCustomerViewModel.savedLiveData.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
            }
        }
    }

    private fun updateCTAButton() {
        binding.btnAddCustomer.isEnabled =
            binding.etFirstName.text?.isNotBlank() == true && binding.etLastName.text?.isNotBlank() == true
    }
}
