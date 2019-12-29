package com.example.easysend.features.delivery.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.example.easysend.databinding.FragmentDeliveryUploadSuratJalanBinding
import com.example.easysend.di.Injectable
import com.example.easysend.utils.setLocalImage
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.constant.ImageProvider

class UploadSuratJalanFragment: Fragment(), Injectable {

    companion object{
        private const val PROFILE_IMAGE_REQ_CODE = 101
        private const val GALLERY_IMAGE_REQ_CODE = 102
        private const val CAMERA_IMAGE_REQ_CODE = 103
    }

    private lateinit var binding:FragmentDeliveryUploadSuratJalanBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeliveryUploadSuratJalanBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    fun initUI(){
        binding.appBarLayout.toolbar.title = "Upload Surat Jalan"
        (activity as AppCompatActivity).setSupportActionBar(binding.appBarLayout.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        requireActivity().onBackPressedDispatcher.addCallback(this@UploadSuratJalanFragment) {
            binding.root.findNavController().popBackStack()
        }
        binding.btnOk.setOnClickListener {  binding.root.findNavController().popBackStack() }
        val myItems = listOf("Camera", "Gallery")
        binding.frameImage.setOnClickListener {
            MaterialDialog(requireContext()).show {
                listItems(items = myItems) { dialog, index, text ->
                    when(index){
                        0->{
                            ImagePicker.with(this@UploadSuratJalanFragment)
                                .provider(ImageProvider.CAMERA) // Default will be ImageProvider.BOTH
                                .compress(1024) // Final image size will be less than 1 MB(Optional)
                                .start(CAMERA_IMAGE_REQ_CODE)
                        }
                        1->{
                            ImagePicker.with(this@UploadSuratJalanFragment)
                                .galleryOnly() // User can only select image from Gallery(Optional)
                                .maxResultSize(1080, 1920) // Final image resolution will be less than 1080 x 1920(Optional)
                                .start(GALLERY_IMAGE_REQ_CODE)
                        }
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                // File object will not be null for RESULT_OK
                val file = ImagePicker.getFile(data)
                Toast.makeText(requireContext(), "Getting Image", Toast.LENGTH_SHORT).show()
                Log.e("TAG", "Path:${file?.absolutePath}")
                when (requestCode) {
                    GALLERY_IMAGE_REQ_CODE -> {
                        binding.lblUploadGambar.visibility = View.GONE
                        binding.inputImage.setLocalImage(file!!)
                    }
                    CAMERA_IMAGE_REQ_CODE -> {
                        binding.lblUploadGambar.visibility = View.GONE
                        binding.inputImage.setLocalImage(file!!, false)
                    }
                }
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}