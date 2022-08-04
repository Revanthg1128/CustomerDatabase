package com.sample.network.remote

import com.sample.data.model.networkmodel.CustomerResponse
import com.sample.network.model.BaseApiResponse
import com.sample.network.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {
    suspend fun getCustomers(): Flow<NetworkResult<CustomerResponse>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getAllCustomers() })
        }.flowOn(Dispatchers.IO)
    }
}
