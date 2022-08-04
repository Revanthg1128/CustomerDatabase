package com.sample.network.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val customersService: CustomersService
){
    suspend fun getAllCustomers() = customersService.getAllCustomers()
}