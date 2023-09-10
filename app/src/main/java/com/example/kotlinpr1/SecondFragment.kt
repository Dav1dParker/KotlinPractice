package com.example.kotlinpr1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlinpr1.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = NavHostFragment.findNavController(this)

        binding.FirstFragmentBtn.setOnClickListener {
            navController.navigate(R.id.action_secondFragment_to_firstFragment)
        }

        binding.ThirdFragmentBtn.setOnClickListener {
            navController.navigate(R.id.action_secondFragment_to_thirdFragment)
        }

        binding.backBtn.setOnClickListener {
            navController.popBackStack()
        }

//        val fragmentManager = requireActivity().supportFragmentManager
//
//        binding.FirstFragmentBtn.setOnClickListener {
//            val transaction = fragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container_view, FirstFragment())
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }
//
//        binding.ThirdFragmentBtn.setOnClickListener {
//            val transaction = fragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container_view, ThirdFragment())
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }
//
//        binding.backBtn.setOnClickListener {
//            fragmentManager.popBackStack()
//        }
    }
}