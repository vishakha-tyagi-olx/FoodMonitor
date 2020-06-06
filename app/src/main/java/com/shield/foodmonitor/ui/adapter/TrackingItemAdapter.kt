package com.shield.foodmonitor.ui.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.shield.foodmonitor.R
import com.shield.foodmonitor.data.model.TrackItem
import com.shield.foodmonitor.ui.listeners.ImageClickListener
import com.shield.foodmonitor.utils.Utility
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.food_item_layout.view.*
import kotlinx.android.synthetic.main.food_list_layout.view.*
import kotlinx.android.synthetic.main.track_order_item.view.*
import kotlinx.android.synthetic.main.trak_order_checklist_item.view.*

class TrackingItemAdapter (private val chefName: String, private val itemList: ArrayList<TrackItem>, private val imageClickListener: ImageClickListener): RecyclerView.Adapter<TrackingItemAdapter.DataViewHolder>() {

    class DataViewHolder(private val chefName: String, itemView: View, private val imageClickListener: ImageClickListener) : RecyclerView.ViewHolder(itemView) {
        fun bind(trackItem: TrackItem) {
            val bitmap = Utility.decodeBase64String(trackItem.image!!)
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 120, 100, false)
            itemView.picture.setImageBitmap(bitmap)
            itemView.subTitle.text = "Mohit Khurana"
            itemView.itemTitle.text = trackItem.status?.toUpperCase()
            itemView.itemTitle.typeface = Utility.FontHelper.boldfont
            itemView.subTitle.typeface = Utility.FontHelper.bookfont
            itemView.desc.typeface = Utility.FontHelper.regular_font
            Utility.stepdescMap().let { map ->
                if(map.size > 0) {
                    itemView.desc.text = map.get(trackItem.status)
                }
            }
            createCheckList(itemView.checkList, trackItem)
            itemView.picture.setOnClickListener {
                imageClickListener.onImageClick(Utility.convertBitmapToByteArray(bitmap))
            }
        }

        private fun createCheckList(listView: LinearLayout,  trackItem: TrackItem) {
            if(listView.childCount > 0) listView.removeAllViews()
            val checkList = Utility.checkListMap().get(trackItem.status)
            var i = 0
            if (checkList != null) {
                for ( item in checkList){
                    val view = LayoutInflater.from(listView.context).inflate(
                        R.layout.trak_order_checklist_item, listView,
                        false
                    )
                    view.sectionItem.text = item
                    view.sectionItem.typeface = Utility.FontHelper.regular_font
                    listView.addView(view)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(chefName,
            LayoutInflater.from(parent.context).inflate(
                R.layout.track_order_item, parent,
                false
            ), imageClickListener
        )

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: TrackingItemAdapter.DataViewHolder, position: Int) =
        holder.bind(itemList[position])

    fun addData(list: List<TrackItem>) {
        itemList.clear()
        itemList.addAll(list)
    }


}