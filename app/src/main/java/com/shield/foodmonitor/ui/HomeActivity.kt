package com.shield.foodmonitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shield.foodmonitor.ui.fragment.FoodListFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.container, FoodListFragment()).commit()
    }
}