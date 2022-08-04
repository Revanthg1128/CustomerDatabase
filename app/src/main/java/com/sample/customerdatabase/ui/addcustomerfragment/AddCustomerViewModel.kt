package com.sample.customerdatabase.ui.addcustomerfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.customerdatabase.ui.BaseViewModel
import com.sample.database.db.customer.Customer
import com.sample.database.repository.RoomDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCustomerViewModel @Inject constructor(
    private val roomDBRepository: RoomDBRepository
) : BaseViewModel(roomDBRepository) {

    private val _savedLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val savedLiveData: LiveData<Boolean> = _savedLiveData

    fun addCustomerToDatabase(customer: Customer) {
        viewModelScope.launch {
            roomDBRepository.insertCustomer(customer)
            _savedLiveData.postValue(true)
        }
    }
}