package com.example.kotlinpr1.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kotlinpr1.R
import com.example.kotlinpr1.data.repositories.QuestionDao
import com.example.kotlinpr1.data.repositories.QuestionsEntity
import com.example.kotlinpr1.data.repositories.QuizDataBase
import com.example.kotlinpr1.data.repositories.QuizRepository
import com.example.kotlinpr1.databinding.ActivityMainBinding
import com.example.kotlinpr1.domain.ApiInterface
import com.example.kotlinpr1.ui.fragments.FirstFragment
import com.example.kotlinpr1.ui.fragments.SecondFragment
import com.example.kotlinpr1.ui.fragments.ThirdFragment
import com.example.kotlinpr1.ui.viewModel.MainActivityViewModel
import com.example.kotlinpr1.ui.viewModel.QuizViewModel
import com.google.android.material.navigation.NavigationView
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    //private val QuizViewModel: QuizViewModel by activityViewModels()
    private lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    //var getAll: LiveData<List<QuestionsEntity>> = QuizViewModel(application).getAll
    //private lateinit var repository: QuizRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //QuizViewModel = ViewModelProvider(this)[QuizViewModel::class.java]


        //Quiz Logic starts
        val endpoint: String = "https://opentdb.com/"

        //val doneLayout: ConstraintLayout
        //Get data from API using retrofit
        val retrofit =
            Retrofit.Builder().baseUrl(endpoint).addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(ApiInterface::class.java)
        var newjson: String = ""
        //val call = service.getQuizResults()


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
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )

                    Log.d("Pretty Printed JSON :", prettyJson)
                    newjson = prettyJson

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }

            println(newjson)
        }


        //Quiz Logic ends


        //DB logic starts
        /*val weatherDao = QuizDataBase.getDataBase(application).QuestionDao()
        repository = QuizRepository(weatherDao)
        getAll = repository.getAll
        val entity = QuestionsEntity(1, "What is the capital of India?", "Delhi", "Mumbai", "Kolkata", "Chennai")*/
        //QuizViewModel.insertQuestions(entity)

        /*val QuestionDao = QuizDataBase.getDataBase(application).QuestionDao()
        val repository = QuizRepository(QuestionDao)
        repository.insertQuestions(entity)
        repository.getAll.observe(this){
            it.forEach{
                println(it)
            }
        }*/


        //DB logic ends


        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close)

        //change the icon of the drawer to hamburger symbol
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navView.setNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {
                R.id.nav_home -> {
                    //open first fragment
                    replaceFragment(FirstFragment(), it.title.toString())
                    /*val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, FirstFragment())
                    transaction.commit()*/
                }

                R.id.nav_settings -> {
                    //open third fragment
                    replaceFragment(ThirdFragment(), it.title.toString())
                    /*val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, ThirdFragment())
                    transaction.commit()*/
                    //supportFragmentManager.beginTransaction().replace(R.id.fragment_container, GalleryFragment()).commit()
                    //drawerLayout.closeDrawers()
                }

                R.id.nav_ShowDatabase -> {
                    //show toast with blank text
                    replaceFragment(SecondFragment(), it.title.toString())
                    Toast.makeText(this, "To do", Toast.LENGTH_SHORT).show()
                }

                R.id.nav_logOut -> {
                    Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                }

                R.id.nav_exit -> {
                    //close app
                    finishAffinity()
                }
            }
            true
        }


    }

    fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
