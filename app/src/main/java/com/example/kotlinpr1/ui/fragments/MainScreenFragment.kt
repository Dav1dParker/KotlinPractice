package com.example.kotlinpr1.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kotlinpr1.R
import com.example.kotlinpr1.data.repositories.QuestionsEntity
import com.example.kotlinpr1.databinding.FragmentMainscreenBinding
import com.example.kotlinpr1.domain.ApiInterface
import com.example.kotlinpr1.domain.QuizResponse
import com.example.kotlinpr1.ui.activities.MainActivity
import com.example.kotlinpr1.ui.viewModel.MainActivityViewModel
import com.example.kotlinpr1.ui.viewModel.QuizViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Arrays
import java.util.Locale


@Suppress("DEPRECATION")
class MainScreenFragment : Fragment() {

    private lateinit var binding: FragmentMainscreenBinding
    private val quizViewModel: QuizViewModel by activityViewModels()
    private val pointsHandler: MainActivityViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainscreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.UpdateDBButton.setOnClickListener {
            //Quiz Logic starts
            val endpoint: String = "https://opentdb.com/"
            //Get data from API using retrofit
            try {
                for (i in 1..10) {
                    val retrofit =
                        Retrofit.Builder().baseUrl(endpoint)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                    val service = retrofit.create(ApiInterface::class.java)
                    var newjson: String = ""
                    CoroutineScope(Dispatchers.IO).launch {
                        /*
                         * For @Query: You need to replace the following line with val response = service.getEmployees(2)
                         * For @Path: You need to replace the following line with val response = service.getEmployee(53)
                         */

                        // Do the GET request and get response
                        val response = service.getData()

                        withContext(Dispatchers.Main) {
                            if (response.isSuccessful) {

                                // Convert raw JSON to pretty JSON using GSON library
                                val gson = GsonBuilder().setPrettyPrinting().create()
                                newjson = gson.toJson(
                                    JsonParser.parseString(
                                        response.body()
                                            ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                                    )
                                )

                                Log.d("Pretty Printed JSON :", newjson)
                                //newjson = prettyJson

                            } else {

                                Log.e("RETROFIT_ERROR", response.code().toString())

                            }
                        }
                        val gson = Gson()
                        val quizResponse: QuizResponse =
                            gson.fromJson(newjson, QuizResponse::class.java)!!

                        val quizModel = quizResponse.results?.firstOrNull()
                        val resultArray = mutableListOf<Any>()
                        quizModel?.let {
                            resultArray.add(it.category ?: "")
                            resultArray.add(it.type ?: "")
                            resultArray.add(it.difficulty ?: "")
                            //clearing question
                            var temp: String = it.question ?: ""
                            temp = temp.replace("\\\\[^\"]+;".toRegex(), "")
                            temp = temp.replace("&[^;]+;".toRegex(), "")
                            resultArray.add(temp) //add cleared question to temporal array
                            //clearing correct answer
                            temp = it.correct_answer ?: ""
                            temp = temp.replace("\\\\[^\"]+;".toRegex(), "")
                            temp = temp.replace("&[^;]+;".toRegex(), "")
                            resultArray.add(temp)//add cleared correct answer to temporal array

                            val tempArray = it.incorrect_answers ?: ArrayList<String>()
                            for (i in tempArray.indices) {
                                temp = tempArray[i]
                                temp = temp.replace("\\\\[^\"]+;".toRegex(), "")
                                temp = temp.replace("&[^;]+;".toRegex(), "")
                                tempArray[i].replace(tempArray[i], temp)
                            }

                            resultArray.add(tempArray)
                        }

                        //println(resultArray)
                        //adding data to database
                        val temp = resultArray[5] as ArrayList<*>
                        val entity = QuestionsEntity(
                            i,
                            resultArray[3].toString(),
                            resultArray[4].toString(),
                            temp[0].toString(),
                            temp[1].toString(),
                            temp[2].toString()
                        )
                        quizViewModel.insertQuestions(entity)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context, getString(R.string.Done), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                /*requireActivity().runOnUiThread {
                    Toast.makeText(context, getString(R.string.Done), Toast.LENGTH_SHORT).show()
                }*/
            } catch (e: Exception) {
                requireActivity().runOnUiThread {
                    Toast.makeText(context, R.string.DBerror, Toast.LENGTH_LONG).show()
                }

            }
        }
        //Quiz Logic ends


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

        binding.startQuizButton.setOnClickListener {
            pointsHandler.genNewOrder()
            //println(pointsHandler.getOrder())
            pointsHandler.clearCounter()
            pointsHandler.clearPointsCounter()
            (activity as MainActivity).replaceFragment(
                QuestionScreenFragment(),
                getString(R.string.ShowDatabase)
            )
        }

    }
}