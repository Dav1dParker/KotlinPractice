package com.example.kotlinpr1.ui.fragments

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinpr1.databinding.FragmentImageLoaderBinding
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class ImageLoader : Fragment() {

    private var _binding: FragmentImageLoaderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageLoaderBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.downloadButton.setOnClickListener {
            val imageUrl = binding.urlEditText.text.toString()

            Thread {
                val bitmap = downloadImageFromNetwork(imageUrl)

                val mainHandler = Handler(Looper.getMainLooper())
                mainHandler.post {
                    if (bitmap != null) {
                        binding.imageView.setImageBitmap(bitmap)

                        Thread {
                            saveImageToInternalStorage(bitmap)
                        }.start()
                    }
                }
            }.start()
        }

        return view
    }

    private fun downloadImageFromNetwork(imageUrl: String): Bitmap? {
        try {
            val url = URL(imageUrl)
            val connection = url.openConnection()
            connection.doInput = true
            connection.connect()
            val input = connection.getInputStream()
            return BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun saveImageToInternalStorage(bitmap: Bitmap) {
        try {
            val file = File(requireContext().filesDir, "downloaded_image.png")
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




