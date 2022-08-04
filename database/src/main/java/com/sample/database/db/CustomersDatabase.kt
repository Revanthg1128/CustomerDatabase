package com.sample.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sample.database.db.customer.Customer
import com.sample.database.db.customer.CustomerDao

@Database(
    entities = [Customer::class],
    version = 1,
    exportSchema = false
)
abstract class CustomersDatabase : RoomDatabase() {

    abstract val customerDao: CustomerDao

    companion object {

        @Volatile
        private var instance: CustomersDatabase? = null

        fun getInstance(context: Context): CustomersDatabase {
            synchronized(this) {
                var mInstance = instance

                if (mInstance == null) {
                    mInstance = Room.databaseBuilder(
                        context.applicationContext,
                        CustomersDatabase::class.java,
                        "customers_database"
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    instance = mInstance
                }
                return mInstance
            }
        }
    }
}
