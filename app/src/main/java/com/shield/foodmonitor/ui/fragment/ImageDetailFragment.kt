package com.shield.foodmonitor.ui.fragment

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.content.ContextCompat
import com.shield.foodmonitor.R
import com.shield.foodmonitor.utils.Constants
import kotlinx.android.synthetic.main.image_detail_fragment.*
import kotlinx.android.synthetic.main.order_confirmed_layout.*

class ImageDetailFragment : AppCompatDialogFragment() {

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            480,
           360
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.order_confirmed_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bitmapdata = arguments?.getByteArray("bitmap")
        bitmapdata?.let {
            image.setImageBitmap(BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.size))
        }

    }
}