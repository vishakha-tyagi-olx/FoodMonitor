package com.shield.foodmonitor.ui.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
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
import com.shield.foodmonitor.utils.Constants
import com.shield.foodmonitor.utils.Status
import com.shield.foodmonitor.utils.Utility
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
        trackStatusViewModel.getOrderStatusList().observe(this, Observer {foodlist->
            when (foodlist.status) {
                Status.SUCCESS -> {
                    progressIcon.visibility = View.GONE
                    if(foodlist!= null && foodlist.data!= null && foodlist.data.checkList!=null && foodlist.data.checkList.size > 0){
                        renderList(foodlist.data)
                    }
                    else {
                        showEmptyView()
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
                    emptyView.text = foodlist.message
                    showEmptyView()
                }
            }
        })
    }

    private fun showEmptyView() {
        emptyView.visibility = View.VISIBLE
    }

    private fun renderList(checkList: TrackOrderResponse) {
        if(checkList.checkList.size >= 3){
            Utility.saveString(context!!, Constants.PREV_UNIQUE_ID, Utility.getString(context!!, Constants.UNIQUE_ID)!!)
        }
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

                        if (it.data != null && it.data.checkList != null && it.data.checkList.size > 0) {
                            renderList(it.data)
                            llSafetyList.visibility = View.VISIBLE
                            liveTrackingLabel.visibility = View.VISIBLE
                            liveTrackingLabel.visibility = View.VISIBLE
                        }
                        else showEmptyView()
                }
                Status.LOADING -> {
                    progressIcon.visibility = View.VISIBLE
                    llSafetyList.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressIcon.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    emptyView.visibility = View.VISIBLE
                }
            }
        })
        trackStatusViewModel.fetchOrderStatusList()
    }

    private fun setUpView() {
        adapter = TrackingItemAdapter("", arrayListOf(), this)
        llSafetyList.layoutManager = LinearLayoutManager(activity)
        llSafetyList.adapter = adapter
        liveTrackingLabel.typeface = Utility.FontHelper.mediumfont
      //  liveTrackingLabelIcon.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.live_track_icon))
        backIcon.setOnClickListener(View.OnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        })

    }

    override fun onImageClick(bitmap: ByteArray) {
        val fragment = ImageDetailFragment()
        val bundle = Bundle()
        bundle.putByteArray("bitmap", bitmap)
        fragment.arguments = bundle
       fragment.show(activity?.supportFragmentManager!!, ImageDetailFragment::class.simpleName)
    }

}