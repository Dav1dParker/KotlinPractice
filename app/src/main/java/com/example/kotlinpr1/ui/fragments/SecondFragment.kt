package com.example.kotlinpr1.ui.fragments

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kotlinpr1.R
import com.example.kotlinpr1.databinding.FragmentSecondBinding
import com.example.kotlinpr1.ui.viewModel.QuizViewModel


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    private val quizViewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        quizViewModel.getAll.observe(viewLifecycleOwner){
            binding.questionHere.text = it.last().Question
            binding.ans1.text = it.last().Answer1
            binding.ans2.text = it.last().Answer2
            binding.ans3.text = it.last().Answer3
            binding.ans4.text = it.last().Answer4
        }

        binding.ans1.setOnClickListener {
            CheckIfCorrect(1)
        }
        binding.ans2.setOnClickListener {
            CheckIfCorrect(2)
        }
        binding.ans3.setOnClickListener {
            CheckIfCorrect(3)
        }
        binding.ans4.setOnClickListener {
            CheckIfCorrect(4)
        }

    }



    fun CheckIfCorrect(choose: Int)
    {
        if (choose == 1)
        {
            //Make toast that answer is correct
            Toast.makeText(context, getString(R.string.Correct), Toast.LENGTH_SHORT).show()
        }
        else
        {
            //Make toast that answer is incorrect
            Toast.makeText(context, getString(R.string.Incorrect), Toast.LENGTH_SHORT).show()
        }
    }

}