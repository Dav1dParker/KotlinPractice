package com.example.kotlinpr1.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinpr1.data.repositories.QuestionsEntity
import com.example.kotlinpr1.databinding.FragmentFirstBinding
import com.example.kotlinpr1.ui.activities.MainActivity
import com.example.kotlinpr1.ui.viewModel.QuizViewModel
import java.util.Locale


@Suppress("DEPRECATION")
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val quizViewModel: QuizViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("First Fragment")
        //initialize view model
        //quizViewModel = ViewModelProvider(this)[QuizViewModel::class.java]
        println("Second Fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //val navController = NavHostFragment.findNavController(this)

        binding.SecondFragmentBtn.setOnClickListener {
            //navController.navigate(R.id.action_firstFragment_to_secondFragment)
        }

        binding.ThirdFragmentBtn.setOnClickListener {
            //navController.navigate(R.id.action_firstFragment_to_thirdFragment)
        }

        binding.backBtn.setOnClickListener {
            //navController.popBackStack()
        }

        //On click listener to change language form english to russian and vice versa
        binding.LanguageChange.setOnClickListener {
            lateinit var locale: Locale
            if (resources.configuration.locales[0] == Locale("en")) {
                locale = Locale("ru")
            } else {
                locale = Locale("en")
            }
            Locale.setDefault(locale)
            val config = resources.configuration
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)
            val refresh = Intent(activity, MainActivity::class.java)
            activity?.finish()
            startActivity(refresh)
        }

        //db logic starts
        val entity = QuestionsEntity(null, "What is the capital of India?", "Delhi", "Mumbai", "Kolkata", "Chennai")
        quizViewModel.insertQuestions(entity)
        quizViewModel.getAll.observe(viewLifecycleOwner){
            println(it)
        }
        //db logic ends
    }
}