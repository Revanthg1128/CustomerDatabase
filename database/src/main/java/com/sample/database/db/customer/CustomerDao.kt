package com.sample.database.db.customer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(customers: List<Customer>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customer: Customer): Long

    @Query("SELECT * FROM customers ORDER BY id ASC")
    fun getAll(): MutableList<Customer>

    @Query("SELECT * FROM customers WHERE id = :id")
    fun get(id: Long): LiveData<Customer>
}