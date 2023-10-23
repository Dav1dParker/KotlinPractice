package com.example.kotlinpr1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kotlinpr1.R
import com.example.kotlinpr1.databinding.FragmentQuestionBinding
import com.example.kotlinpr1.ui.activities.MainActivity
import com.example.kotlinpr1.ui.viewModel.MainActivityViewModel
import com.example.kotlinpr1.ui.viewModel.QuizViewModel


class QuestionScreenFragment : Fragment() {

    private lateinit var binding: FragmentQuestionBinding

    private val quizViewModel: QuizViewModel by activityViewModels()
    private val pointsHandler: MainActivityViewModel by activityViewModels()

    private val ansMass = arrayListOf<String>()
    private var correctAnswer = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextQuestionButton.isEnabled = false
        quizViewModel.getAll.observe(viewLifecycleOwner) {
            ansMass.clear()
            val questionNumber = pointsHandler.getOrder()[pointsHandler.getCounter()]
            println(questionNumber)
            //val questionNumber = (0..9).random()
            binding.questionHere.text = it.get(questionNumber).Question
            ansMass.add(it.get(questionNumber).Answer1)
            ansMass.add(it.get(questionNumber).Answer2)
            ansMass.add(it.get(questionNumber).Answer3)
            ansMass.add(it.get(questionNumber).Answer4)
            correctAnswer = it.get(questionNumber).Answer1
            Toast.makeText(context, correctAnswer, Toast.LENGTH_SHORT).show()
            ansMass.shuffle()
            binding.ans1.text = ansMass[0]
            binding.ans2.text = ansMass[1]
            binding.ans3.text = ansMass[2]
            binding.ans4.text = ansMass[3]
        }

        binding.ans1.setOnClickListener {
            CheckIfCorrect(0)
        }
        binding.ans2.setOnClickListener {
            CheckIfCorrect(1)
        }
        binding.ans3.setOnClickListener {
            CheckIfCorrect(2)
        }
        binding.ans4.setOnClickListener {
            CheckIfCorrect(3)
        }

        binding.nextQuestionButton.setOnClickListener {
            //reload this fragment
            pointsHandler.countPlusPlus()
            if (pointsHandler.getCounter() == 10) {
                Toast.makeText(context, "END", Toast.LENGTH_SHORT).show()
                (activity as MainActivity).replaceFragment(
                    MainScreenFragment(),
                    getString(R.string.Home)
                )
            } else {
                (activity as MainActivity).replaceFragment(
                    QuestionScreenFragment(),
                    getString(R.string.ShowDatabase)
                )
            }
        }

    }


    private fun CheckIfCorrect(choose: Int) {
        disableButtons()
        revealCorrect()
        if (ansMass[choose] == correctAnswer) {
            //Make toast that answer is correct
            //Toast.makeText(context, getString(R.string.Correct), Toast.LENGTH_SHORT).show()
            when (choose) {
                0 -> binding.ans1.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.HighlightedCorrectAnswer
                    )
                )

                1 -> binding.ans2.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.HighlightedCorrectAnswer
                    )
                )

                2 -> binding.ans3.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.HighlightedCorrectAnswer
                    )
                )

                3 -> binding.ans4.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.HighlightedCorrectAnswer
                    )
                )
            }
        } else {
            when (choose) {
                0 -> binding.ans1.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.HighlightedWrongAnswer
                    )
                )

                1 -> binding.ans2.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.HighlightedWrongAnswer
                    )
                )

                2 -> binding.ans3.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.HighlightedWrongAnswer
                    )
                )

                3 -> binding.ans4.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.HighlightedWrongAnswer
                    )
                )
            }
            //Make toast that answer is incorrect
            //Toast.makeText(context, getString(R.string.Incorrect), Toast.LENGTH_SHORT).show()
        }
        //disableButtons()
        binding.nextQuestionButton.isEnabled = true
    }


    private fun disableButtons() {
        binding.ans1.isEnabled = false
        binding.ans2.isEnabled = false
        binding.ans3.isEnabled = false
        binding.ans4.isEnabled = false
    }

    private fun revealCorrect() {
        binding.ans1.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.wrongAnswer
            )
        )
        binding.ans2.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.wrongAnswer
            )
        )
        binding.ans3.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.wrongAnswer
            )
        )
        binding.ans4.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.wrongAnswer
            )
        )
        binding.ans1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.ans2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.ans3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.ans4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        when (correctAnswer) {
            ansMass[0] -> binding.ans1.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.correctAnswer
                )
            )

            ansMass[1] -> binding.ans2.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.correctAnswer
                )
            )

            ansMass[2] -> binding.ans3.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.correctAnswer
                )
            )

            ansMass[3] -> binding.ans4.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.correctAnswer
                )
            )
        }
    }

}