package com.shield.foodmonitor.ui.fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.constraintlayout.widget.ConstraintLayout
import com.shield.foodmonitor.R
import kotlinx.android.synthetic.main.image_detail_fragment.*


class ImageDetailFragment : AppCompatDialogFragment() {

    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var mScaleFactor = 1.0f

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bitmapdata = arguments?.getByteArray("bitmap")
        bitmapdata?.let {
           image.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.size), 1200, 920, false))
        }
    }
}