package com.example.kotlinpr1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kotlinpr1.R
import com.example.kotlinpr1.databinding.FragmentEndScreenBinding
import com.example.kotlinpr1.databinding.FragmentQuestionBinding
import com.example.kotlinpr1.ui.activities.MainActivity
import com.example.kotlinpr1.ui.viewModel.MainActivityViewModel


class EndScreenFragment : Fragment() {
    private lateinit var binding: FragmentEndScreenBinding
    private val pointsHandler: MainActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEndScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pointsCounter.text = pointsHandler.getPointsCounter().toString() + "/10"

        binding.ReturnToMainMenu.setOnClickListener {
            pointsHandler.clearPointsCounter()
            pointsHandler.clearCounter()
            (activity as MainActivity).replaceFragment(
                MainScreenFragment(),
                getString(R.string.Home)
            )
        }
        binding.TryAgainButton.setOnClickListener {
            pointsHandler.clearPointsCounter()
            pointsHandler.clearCounter()
            (activity as MainActivity).replaceFragment(
                QuestionScreenFragment(),
                getString(R.string.ShowDatabase)
            )
        }
    }


}