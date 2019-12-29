package com.example.easysend.features.delivery.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.easysend.databinding.DialogSignatureBinding
import com.example.easysend.di.Injectable
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class SignatureDialogFragment : DialogFragment(), Injectable{
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE =
        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private lateinit var binding:DialogSignatureBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        verifyStoragePermissions()
        binding = DialogSignatureBinding.inflate(inflater, container, false)
        binding.btnOk.setOnClickListener {
            if (binding.inputNama.text!!.isNotEmpty()){
                val signatureBitmap = binding.signaturePad.signatureBitmap
                if (addJpgSignatureToGallery(signatureBitmap)) {
                    Toast.makeText(requireContext(), "Signature saved into the Gallery", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Unable to store the signature", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.btnHapus.setOnClickListener{
            binding.signaturePad.clear()
        }
        return binding.root
    }

    private fun sendResult(uri: String) {
        if (targetFragment == null) {
            return
        }
        val intent= Intent().apply{
            putExtra("URI", uri)
            putExtra("NAME", binding.inputNama.text.toString())
        }
        targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        dismiss()
    }

    fun getAlbumStorageDir(albumName: String?): File? { // Get the directory for the user's public pictures directory.
        val file = File(
            requireActivity().getExternalFilesDir(
                Environment.DIRECTORY_PICTURES
            ), albumName
        )
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created")
        }
        return file
    }

    @Throws(IOException::class)
    fun saveBitmapToJPG(bitmap: Bitmap, photo: File?) {
        val newBitmap =
            Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(newBitmap)
        canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        val stream = FileOutputStream(photo)
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream)
        stream.close()
    }

    fun addJpgSignatureToGallery(signature: Bitmap): Boolean {
        var result = false
        try {
            val photo = File(
                getAlbumStorageDir("EasySend"),
                String.format("Signature_${binding.inputNama.text}_%d.jpg", System.currentTimeMillis())
            )
            saveBitmapToJPG(signature, photo)
            scanMediaFile(photo)
            result = true
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    private fun scanMediaFile(photo: File) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        val contentUri: Uri = Uri.fromFile(photo)
        mediaScanIntent.data = contentUri
        this@SignatureDialogFragment.sendResult(mediaScanIntent.data.toString())
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_EXTERNAL_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size <= 0
                    || grantResults[0] != PackageManager.PERMISSION_GRANTED
                ) {
                    Snackbar.make(
                        binding.root,
                        "Cannot write images to external storage",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun verifyStoragePermissions() { // Check if we have write permission
        val permission = requireActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permission != PackageManager.PERMISSION_GRANTED) { // We don't have permission so prompt the user
            requireActivity().requestPermissions(PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE)
        }
    }
}