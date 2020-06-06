package com.shield.foodmonitor.ui.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.shield.foodmonitor.FoodMonitorApplication
import com.shield.foodmonitor.R
import com.shield.foodmonitor.utils.Constants
import com.shield.foodmonitor.utils.Utility
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.confirm_details_layout.*

class ConfirmDetailsFragment: Fragment(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return inflater.inflate(R.layout.confirm_details_layout, container, false)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dishName.text = arguments?.getString("name")
        dishPrice.text = "Rs. " + arguments?.getString("price") + "/-"
        totalPrice.text = "Total: Rs. "+ arguments?.getString("price")
        shield.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.shield))
        monitorImg.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.monitor_img))
        Picasso.with(context).load(arguments?.getString("image")).resize(40, 40).into(foodPic);
        setOnClickListeners()

    }



    private fun setOnClickListeners() {
        change0.setOnClickListener(this)
        change.setOnClickListener(this)
        change2.setOnClickListener(this)
        confirmAction.setOnClickListener(this)
        FoodMonitorApplication.showKeyboard(nameVal)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.change0->{
              //  nameVal.text.clear()
              //  nameVal.parent.requestChildFocus()
            }
           R.id.change->{
              // addressVal.text.clear()
           }
            R.id.change2->{
             //   phoneNoVal.text.clear()
            }
            R.id.confirmAction ->{
                Utility.saveBoolean(context!!, Constants.IS_ORDER_RECEIVED, true)
                Utility.saveString(context!!, Constants.CLIENT_NAME, name.text.toString())
                Utility.saveString(context!!, Constants.PHONE_NO, phoneNo.text.toString())
                Utility.saveString(context!!, Constants.ADDRESS, addressVal.text.toString())

                OrderConfirmedFragment().show(activity?.supportFragmentManager!!, OrderConfirmedFragment::class.simpleName)
              //  activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, OrderConfirmedFragment())?.commit()
            }
        }
    }

}