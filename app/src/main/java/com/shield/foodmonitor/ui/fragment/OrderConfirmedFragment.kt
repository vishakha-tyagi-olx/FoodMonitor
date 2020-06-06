package com.shield.foodmonitor.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.content.ContextCompat
import com.shield.foodmonitor.R
import kotlinx.android.synthetic.main.order_confirmed_layout.*

class OrderConfirmedFragment : AppCompatDialogFragment(), View.OnClickListener {

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
        return inflater.inflate(R.layout.order_confirmed_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crossIcon.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.cross_icon))
        /*done.setImageDrawable(
            ContextCompat.getDrawable(
                context!!,
                R.drawable.switch_to_admin_cta
            )
        )*/
        crossIcon.setOnClickListener(this)
        done.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.crossIcon -> {
                activity?.supportFragmentManager?.popBackStack()
                dismiss()
            }
            R.id.done ->{
                activity?.supportFragmentManager?.popBackStack()
                Toast.makeText(context!!, "You can switch to Admin and post pictures now.", Toast.LENGTH_LONG).show()
                dismiss()
            }
        }
    }
}