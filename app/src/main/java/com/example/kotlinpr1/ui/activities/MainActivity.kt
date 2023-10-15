package com.example.kotlinpr1.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlinpr1.R
import com.example.kotlinpr1.databinding.ActivityMainBinding
import com.example.kotlinpr1.ui.fragments.FirstFragment
import com.example.kotlinpr1.ui.fragments.SecondFragment
import com.example.kotlinpr1.ui.fragments.ThirdFragment
import com.example.kotlinpr1.ui.viewModel.MainActivityViewModel
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var toggle: ActionBarDrawerToggle



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close)

        //change the icon of the drawer to hamburger symbol
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    //open first fragment
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, FirstFragment())
                    transaction.commit()
                }

                R.id.nav_settings -> {
                    //open third fragment
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, ThirdFragment())
                    transaction.commit()
                    //supportFragmentManager.beginTransaction().replace(R.id.fragment_container, GalleryFragment()).commit()
                    //drawerLayout.closeDrawers()
                }

                R.id.nav_ShowDatabase -> {
                    //show toast with blank text
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
