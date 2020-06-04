package com.shield.foodmonitor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shield.foodmonitor.ui.fragment.AdminFragment
import com.shield.foodmonitor.ui.fragment.ConfirmDetailsFragment
import com.shield.foodmonitor.ui.fragment.FoodListFragment
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchHomeFragment()

    }

    private fun launchHomeFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.container, FoodListFragment()).commit()
    }

    private fun launchAdminFragment(){
        supportFragmentManager.beginTransaction().replace(R.id.container, AdminFragment()).commit()
    }

    protected fun removeAllFragments() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate(
                supportFragmentManager
                    .getBackStackEntryAt(0).id,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }
    }


