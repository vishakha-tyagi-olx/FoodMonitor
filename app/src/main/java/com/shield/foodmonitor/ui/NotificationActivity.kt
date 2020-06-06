package com.shield.foodmonitor.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shield.foodmonitor.R
import com.shield.foodmonitor.ui.fragment.FoodListFragment
import com.shield.foodmonitor.ui.fragment.MonitorReportFragment
import com.shield.foodmonitor.ui.fragment.TrackOrderFragment
import com.shield.foodmonitor.utils.Constants

class NotificationActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchFragment()

    }

    private fun launchFragment() {
        if (intent.hasExtra(Constants.Notification.NAVIGATE_TO)) {
            if (intent.getStringExtra(Constants.Notification.NAVIGATE_TO)
                    .equals(Constants.DELIVERY_DETAIL)
            ) {
                launchMonitorReportFragment()
            } else if (intent.getStringExtra(Constants.Notification.NAVIGATE_TO)
                    .equals(Constants.CHEF_DETAIL) || intent.getStringExtra(
                    Constants.Notification.NAVIGATE_TO
                ).equals(Constants.COOKING_DETAIL)
            ) {
                launchTrackingFragment()
            } else
                launchHomeFragment()
        } else {
            launchHomeFragment()
        }
    }

    private fun launchHomeFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.container, FoodListFragment()).commit()
    }

    private fun launchTrackingFragment() {
        supportFragmentManager?.beginTransaction()?.replace(R.id.container, TrackOrderFragment())
            ?.commit()
    }

    private fun launchMonitorReportFragment() {
        val fragment = MonitorReportFragment()
        if(intent.hasExtra(Constants.Notification.IMAGE_URL)) {
            val bundle = Bundle()
            bundle.putString(
                Constants.Notification.IMAGE_URL,
                intent.getStringExtra(Constants.Notification.IMAGE_URL)
            )
            fragment.arguments = bundle
        }
        supportFragmentManager?.beginTransaction()?.replace(R.id.container, fragment)
            ?.commit()
    }
}