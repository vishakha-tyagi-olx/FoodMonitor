package com.shield.foodmonitor.ui.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.shield.foodmonitor.R
import com.shield.foodmonitor.data.api.ApiServiceImpl
import com.shield.foodmonitor.data.model.FoodStepDetail
import com.shield.foodmonitor.ui.base.ViewModelFactory
import com.shield.foodmonitor.ui.viewmodel.PostFoodStepViewModel
import com.shield.foodmonitor.utils.Status
import com.shield.foodmonitor.utils.Utility.Companion.convertToString
import kotlinx.android.synthetic.main.admin_fragment_layout.*


class AdminFragment: Fragment(), AdapterView.OnItemSelectedListener {

    private val CAMERA_REQUEST = 222
    private val MY_CAMERA_PERMISSION_CODE = 111
    private val EMPTY_STRING = ""
    private var stepStatus = EMPTY_STRING

    private lateinit var postFoodStepViewModel: PostFoodStepViewModel
    private lateinit var spinnerAdapter: SpinnerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.admin_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setupViewModel()
    }

    private fun setupObserver(foodStepDetail: FoodStepDetail) {
        postFoodStepViewModel.getUploadStepStatus().observe(this, androidx.lifecycle.Observer {
            when(it.status){
                Status.SUCCESS -> {
                    progressIcon.visibility = View.GONE
                    // reset spinner
                    resetSpinner()
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    progressIcon.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressIcon.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
        postFoodStepViewModel.uploadStepStatus(foodStepDetail)
    }

    private fun resetSpinner(){
        stepsSpinner.setSelection(0)
        stepStatus = EMPTY_STRING
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions!!, grantResults)
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this.context, "camera permission granted", Toast.LENGTH_LONG).show()
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            } else {
                Toast.makeText(this.context, "camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode === CAMERA_REQUEST && resultCode === Activity.RESULT_OK) {
            val photoBitmap = data?.extras?.get("data") as Bitmap
            val encodedString = convertToString(photoBitmap)
            setupObserver(FoodStepDetail(stepStatus, encodedString))
            setDummyImage(encodedString)
        }
    }

    private fun setDummyImage(encodedString: String?) {
        val decodedBytes: ByteArray = Base64.decode(encodedString, Base64.DEFAULT)
        val decodedBitmap =
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        dummyImage.setImageBitmap(decodedBitmap)
    }

    private fun setupViewModel() {
        postFoodStepViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiServiceImpl())
        ).get(PostFoodStepViewModel::class.java)

    }

    private fun setUpView() {
        prepareSpinner()
        sendPics.setOnClickListener {
            if (checkSelfPermission(context!!, Manifest.permission.CAMERA) !== PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    MY_CAMERA_PERMISSION_CODE
                )
            } else {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
        }
    }

    private fun prepareSpinner() {
        ArrayAdapter.createFromResource(
            context!!,
            R.array.step_details,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            stepsSpinner.adapter = adapter
        }
        stepsSpinner.onItemSelectedListener = this
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        stepStatus = parent?.getItemAtPosition(position) as String
    }


}