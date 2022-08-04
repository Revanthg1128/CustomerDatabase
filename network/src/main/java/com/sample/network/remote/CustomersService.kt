package com.sample.network.remote

import com.sample.data.model.networkmodel.CustomerResponse
import com.sample.network.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface CustomersService {

    @GET(Constants.CUSTOMERS)
    suspend fun getAllCustomers(): Response<CustomerResponse>

}