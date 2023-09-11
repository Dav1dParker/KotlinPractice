package com.example.kotlinpr1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlinpr1.databinding.FragmentFirstBinding
import java.util.Locale


@Suppress("DEPRECATION")
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private var currentLanguage = "en"
    lateinit var locale: Locale

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //start2
        val fragmentManager = requireActivity().supportFragmentManager

        binding.SecondFragmentBtn.setOnClickListener {
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_view, SecondFragment())
            transaction.addToBackStack(null)
            transaction.commit()
            //Toast.makeText(context, "Hi",Toast.LENGTH_LONG).show()
        }

        binding.ThirdFragmentBtn.setOnClickListener {
            //Toast.makeText(context, "Hi",Toast.LENGTH_LONG).show()
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_view, ThirdFragment())
            transaction.addToBackStack(null)
            transaction.commit()
            //Toast.makeText(context, "Ho",Toast.LENGTH_LONG).show()
        }

        binding.backBtn.setOnClickListener {
            fragmentManager.popBackStack()
        }
        //end2


    }
}