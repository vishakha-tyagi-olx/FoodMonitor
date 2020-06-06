package com.shield.foodmonitor.ui.fragment

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
import kotlinx.android.synthetic.main.shield_content_fragment.*

class ShieldContentFragment : AppCompatDialogFragment(){

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
        return inflater.inflate(R.layout.shield_content_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crossIcon.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.cross_icon))
        crossIcon.setOnClickListener(View.OnClickListener {
            dismiss()
        })
    }
}