package com.shield.foodmonitor.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.shield.foodmonitor.R
import com.shield.foodmonitor.data.model.FoodItem
import com.shield.foodmonitor.ui.listeners.OrderNowClickListener
import com.shield.foodmonitor.utils.Utility
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_item_layout.view.*

class FoodListAdapter(private val foodList: ArrayList<FoodItem>, private val orderNowClickListener: OrderNowClickListener): RecyclerView.Adapter<FoodListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View, private val orderNowClickListener: OrderNowClickListener) : RecyclerView.ViewHolder(itemView) {



        fun bind(foodItem: FoodItem) {
            itemView.vegNonveg.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_veg_mark))
            itemView.itemName.text = foodItem.name
            itemView.itemName.typeface = Utility.FontHelper.regular_font
            itemView.itemprice.text = "Rs. " + foodItem.price
            itemView.itemprice.typeface = Utility.FontHelper.mediumfont
            itemView.shieldTag.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.shield))
            itemView.shieldTag.setOnClickListener(View.OnClickListener {
                orderNowClickListener.onshieldClickListener()
            })
            Glide.with(itemView.context!!)
                .load(foodItem.image)
                .transform(RoundedCorners(32)).placeholder(R.drawable.placeholder).into(itemView.foodPic);
          //  Picasso.with(itemView.context).load(foodItem.image).resize(480, 260).placeholder(R.drawable.placeholder).into(itemView.foodPic);
            itemView.orderNow.setOnClickListener {
                orderNowClickListener.onOrderNowClick(foodItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.food_item_layout, parent,
                false
            ), orderNowClickListener
        )

    override fun getItemCount(): Int = foodList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(foodList[position])

    fun addData(list: List<FoodItem>) {
        foodList.clear()
        foodList.addAll(list)
    }
}