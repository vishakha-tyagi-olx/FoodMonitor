package com.shield.foodmonitor.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.shield.foodmonitor.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.confirm_details_layout.*
import kotlinx.android.synthetic.main.food_item_layout.view.*

class ConfirmDetailsFragment: Fragment(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        change.setOnClickListener(this)
        change2.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
           R.id.change->{
               addressVal.text.clear()
           }
            R.id.change2->{
                phoneNoVal.text.clear()
            }
        }
    }
}