package com.example.kotlinpr1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlinpr1.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = NavHostFragment.findNavController(this)

        binding.FirstFragmentBtn.setOnClickListener {
            navController.navigate(R.id.action_thirdFragment_to_firstFragment)
        }

        binding.SecondFragmentBtn.setOnClickListener {
            navController.navigate(R.id.action_thirdFragment_to_secondFragment)
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
//        binding.SecondFragmentBtn.setOnClickListener {
//            val transaction = fragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container_view, SecondFragment())
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }
//
//        binding.backBtn.setOnClickListener {
//            fragmentManager.popBackStack()
//        }
    }

}