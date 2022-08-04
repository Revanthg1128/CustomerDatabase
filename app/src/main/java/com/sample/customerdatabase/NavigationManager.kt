package com.sample.customerdatabase

import android.app.Activity
import androidx.navigation.findNavController
import javax.inject.Inject

class NavigationManager @Inject constructor(
    private val activity: Activity
) {
    fun navigateToAddCustomer() {
        activity.findNavController(R.id.nav_host_fragment_content_main).navigate(
            R.id.action_CustomerFragment_to_AddCustomerFragment
        )
    }
}
