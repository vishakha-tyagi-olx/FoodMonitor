package com.shield.foodmonitor.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.shield.foodmonitor.R
import com.shield.foodmonitor.data.api.ApiHelper
import com.shield.foodmonitor.data.api.ApiServiceImpl
import com.shield.foodmonitor.data.model.FoodItem
import com.shield.foodmonitor.ui.adapter.FoodListAdapter
import com.shield.foodmonitor.ui.base.ViewModelFactory
import com.shield.foodmonitor.ui.listeners.OrderNowClickListener
import com.shield.foodmonitor.ui.viewmodel.FoodListViewModel
import com.shield.foodmonitor.utils.Status
import kotlinx.android.synthetic.main.food_list_layout.*

class FoodListFragment: Fragment(), OrderNowClickListener{

    private lateinit var foodListViewModel: FoodListViewModel
    private lateinit var adapter: FoodListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.food_list_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setupViewModel()
    }

    private fun setupViewModel() {
        foodListViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(FoodListViewModel::class.java)
    }

    private fun setUpView() {
        adapter = FoodListAdapter(arrayListOf(), this)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        foodListViewModel.getFoodList().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { foodList -> renderList(foodList) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(foodList: List<FoodItem>) {
        adapter.addData(foodList)
        adapter.notifyDataSetChanged()
    }

    override fun onOrderNowClick(foodItem: FoodItem) {
    }

}