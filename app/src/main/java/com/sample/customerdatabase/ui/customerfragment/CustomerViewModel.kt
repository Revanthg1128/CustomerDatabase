package com.sample.customerdatabase.ui.customerfragment

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.customerdatabase.ui.BaseViewModel
import com.sample.data.model.CustomerItem
import com.sample.database.db.customer.Customer
import com.sample.database.repository.RoomDBRepository
import com.sample.network.remote.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val repository: Repository,
    private val roomDBRepository: RoomDBRepository
) : BaseViewModel(roomDBRepository) {

    private val _response: MutableLiveData<List<CustomerItem>> =
        MutableLiveData()
    val response: LiveData<List<CustomerItem>> = _response

    private val _loading: MutableLiveData<Int> = MutableLiveData(View.GONE)
    val loading: LiveData<Int> = _loading

    fun fetchCustomers() = viewModelScope.launch {
        showLoading()
        repository.getCustomers().collect { values ->
            hideLoading()
            values.data?.results?.map {
                CustomerItem(
                    title = it.name?.title,
                    firstName = it.name?.first.orEmpty(),
                    lastName = it.name?.last.orEmpty()
                )
            }?.let {
                saveCustomersToDatabase(it)
            }
        }
    }

    fun fetchCustomersFromDatabase() = viewModelScope.launch {
        showLoading()
        _response.value = roomDBRepository.getAllCustomers().map {
            CustomerItem(
                id = it.id,
                firstName = it.firstName,
                lastName = it.lastName
            )
        }
        hideLoading()
    }

    private fun saveCustomersToDatabase(customers: List<CustomerItem>) {
        viewModelScope.launch {
            roomDBRepository.insertCustomers(customers.map {
                Customer(
                    title = it.title,
                    firstName = it.firstName,
                    lastName = it.lastName
                )
            })
            fetchCustomersFromDatabase()
        }
    }

    private fun showLoading() {
        _loading.postValue(View.VISIBLE)
    }

    private fun hideLoading() {
        _loading.postValue(View.GONE)
    }

}