package com.shield.foodmonitor.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shield.foodmonitor.R
import com.shield.foodmonitor.data.model.TrackOrderResponse
import com.shield.foodmonitor.ui.adapter.TrackingItemAdapter
import com.shield.foodmonitor.ui.base.ViewModelFactory
import com.shield.foodmonitor.ui.listeners.ImageClickListener
import com.shield.foodmonitor.ui.viewmodel.PostFoodStepViewModel
import com.shield.foodmonitor.ui.viewmodel.TrackOrderViewModel
import com.shield.foodmonitor.utils.Status
import kotlinx.android.synthetic.main.food_list_layout.*
import kotlinx.android.synthetic.main.track_order_layout.*

class TrackOrderFragment: Fragment(), ImageClickListener {

    private lateinit var trackStatusViewModel: TrackOrderViewModel
    private lateinit var adapter: TrackingItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.track_order_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setupViewModel()
        setupObserver()
    }

    private fun setupObserver() {
        trackStatusViewModel.getOrderStatusList().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressIcon.visibility = View.GONE
                    it.data?.let {
                            checkList ->
                        if(checkList.checkList.size > 0) {renderList(checkList)} else showEmptyView()
                    }
                    llSafetyList.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressIcon.visibility = View.VISIBLE
                    llSafetyList.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressIcon.visibility = View.GONE
                    emptyView.text = it.message
                    showEmptyView()
                }
            }
        })
    }

    private fun showEmptyView() {
        emptyView.visibility = View.VISIBLE
    }

    private fun renderList(checkList: TrackOrderResponse) {
        adapter.addData(checkList.checkList)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        trackStatusViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory()
        ).get(TrackOrderViewModel::class.java)
        trackStatusViewModel.getOrderStatusList().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressIcon.visibility = View.GONE
                    it.data?.let { foodList -> renderList(foodList) }
                    llSafetyList.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressIcon.visibility = View.VISIBLE
                    llSafetyList.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressIcon.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
        trackStatusViewModel.fetchOrderStatusList()
    }

    private fun setUpView() {
        adapter = TrackingItemAdapter("", arrayListOf(), this)
        llSafetyList.layoutManager = LinearLayoutManager(activity)
        llSafetyList.adapter = adapter
    }

    override fun onImageClick(url: String?) {
    }

}