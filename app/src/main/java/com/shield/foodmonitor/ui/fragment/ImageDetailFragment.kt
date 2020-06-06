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
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.image_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bitmapdata = arguments?.getByteArray("bitmap")
        bitmapdata?.let {
           image.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.size), 860, 720, false))
        }
        scaleGestureDetector = ScaleGestureDetector(activity, ScaleListener(mScaleFactor, image))
        image.setOnTouchListener { view, motionEvent ->
            scaleGestureDetector.onTouchEvent(motionEvent)
        }
    }


    fun onTouchEvent(motionEvent: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(motionEvent)
        return true
    }

    private class ScaleListener(var mScaleFactor: Float, val imageView: ImageView) : SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f))
            imageView.setScaleX(mScaleFactor)
            imageView.setScaleY(mScaleFactor)
            return true
        }
    }
}