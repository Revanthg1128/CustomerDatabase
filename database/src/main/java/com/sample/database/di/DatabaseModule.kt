package com.sample.database.di

import android.content.Context
import com.sample.database.db.CustomersDatabase
import com.sample.database.db.customer.CustomerDao
import com.sample.database.repository.RoomDBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideCustomerDao(@ApplicationContext context: Context): CustomerDao {
        return CustomersDatabase.getInstance(context).customerDao
    }

    @Provides
    fun provideRoomDBRepository(
        customerDao: CustomerDao
    ) = RoomDBRepository(
        customerDao = customerDao
    )
}
