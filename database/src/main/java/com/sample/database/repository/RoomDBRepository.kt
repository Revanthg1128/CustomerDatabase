package com.sample.database.repository

import com.sample.database.db.customer.Customer
import com.sample.database.db.customer.CustomerDao
import javax.inject.Inject

class RoomDBRepository @Inject constructor(
    private val customerDao: CustomerDao
) {
    fun getAllCustomers() = customerDao.getAll()
    fun getCustomer(id: Long) = customerDao.get(id)
    suspend fun insertCustomer(customer: Customer) = customerDao.insert(customer)
    suspend fun insertCustomers(customers: List<Customer>) = customerDao.insertAll(customers)
}
