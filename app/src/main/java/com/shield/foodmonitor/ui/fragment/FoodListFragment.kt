package com.shield.foodmonitor.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.shield.foodmonitor.R
import com.shield.foodmonitor.data.api.ApiHelper
import com.shield.foodmonitor.data.api.ApiServiceImpl
import com.shield.foodmonitor.data.model.FoodItem
import com.shield.foodmonitor.data.model.FoodResponse
import com.shield.foodmonitor.ui.adapter.FoodListAdapter
import com.shield.foodmonitor.ui.base.ViewModelFactory
import com.shield.foodmonitor.ui.listeners.OrderNowClickListener
import com.shield.foodmonitor.ui.viewmodel.FoodListViewModel
import com.shield.foodmonitor.utils.Constants
import com.shield.foodmonitor.utils.Status
import com.shield.foodmonitor.utils.Utility
import kotlinx.android.synthetic.main.food_item_layout.*
import kotlinx.android.synthetic.main.food_list_layout.*
import kotlinx.android.synthetic.main.food_list_layout.title
import kotlinx.android.synthetic.main.track_order_layout.*

class FoodListFragment: Fragment(), OrderNowClickListener, View.OnClickListener{

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
        setupObserver()
    }

    private fun setupViewModel() {
        foodListViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory()
        ).get(FoodListViewModel::class.java)
    }

    private fun setUpView() {
        adapter = FoodListAdapter(arrayListOf(), this)
        switchToAdmin.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.switch_to_admin))
        showTrackYourOrder()
        switchToAdmin.setOnClickListener(this)
        rightArrow.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.right_arrow))
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        title.setTypeface(Utility.FontHelper.custom_font)
    }

    private fun showTrackYourOrder() {
         if(Utility.getBoolean(context!!, Constants.IS_ORDER_RECEIVED)) {
             trackYourOrder.setImageDrawable(
                 ContextCompat.getDrawable(
                     context!!,
                     R.drawable.ic_track_order
                 )
             )
             trackYourOrder.setOnClickListener(this)
             trackYourOrderIcon.setImageDrawable(
                 ContextCompat.getDrawable(
                     context!!,
                     R.drawable.track_order_icon
                 )
             )
             trackYourOrder.visibility = View.VISIBLE
             trackYourOrderIcon.visibility = View.VISIBLE
             rightArrow.visibility = View.VISIBLE
             trackYourOrder.setOnClickListener(this)
         }
    }

    private fun setupObserver() {
        foodListViewModel.getFoodList().observe(this, Observer {foodlist->
            when (foodlist.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    if(foodlist!= null && foodlist.data!= null && foodlist.data.foodList!=null && foodlist.data.foodList.size > 0){
                        renderList(foodlist.data)
                        recyclerView.visibility = View.VISIBLE
                    }
                    else {
                        showEmptyView()
                    }

                    foodlist.data?.let { foodList -> renderList(foodList) }

                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    noResult.text = foodlist.message
                    showEmptyView()
                    Toast.makeText(activity, foodlist.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun showEmptyView() {
        noResult.visibility = View.VISIBLE
    }

    private fun renderList(foodList: FoodResponse) {
        adapter.addData(foodList.foodList)
        adapter.notifyDataSetChanged()
    }

    override fun onOrderNowClick(foodItem: FoodItem) {
        val bundle = Bundle()
        bundle.putString("price", foodItem.price)
        bundle.putString("name", foodItem.name)
        bundle.putString("image", foodItem.image)
        val fragment = ConfirmDetailsFragment()
        fragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, fragment)?.addToBackStack(null)?.commit()

    }

    override fun onshieldClickListener() {
        ShieldContentFragment().show(activity?.supportFragmentManager!!, ShieldContentFragment::class.simpleName)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.trackYourOrder->{
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, TrackOrderFragment())?.addToBackStack(null)?.commit()
            }
            R.id.switchToAdmin ->{
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, AdminFragment())?.addToBackStack(null)?.commit()
            }
        }
    }

}