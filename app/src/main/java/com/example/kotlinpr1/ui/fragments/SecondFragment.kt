package com.example.kotlinpr1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.kotlinpr1.databinding.FragmentSecondBinding
import com.example.kotlinpr1.ui.viewModel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val navController = NavHostFragment.findNavController(this)

        binding.FirstFragmentBtn.setOnClickListener {
            navController.navigate(R.id.action_secondFragment_to_firstFragment)
        }

        binding.ThirdFragmentBtn.setOnClickListener {
            navController.navigate(R.id.action_secondFragment_to_thirdFragment)
        }

        binding.backBtn.setOnClickListener {
            navController.popBackStack()
        }*/

        quizViewModel.getAll.observe(viewLifecycleOwner) {
            binding.questionHere.text = it.last().Question
            binding.ans1.text = it.last().Answer1
            binding.ans2.text = it.last().Answer2
            binding.ans3.text = it.last().Answer3
            binding.ans4.text = it.last().Answer4
        }

    }
}