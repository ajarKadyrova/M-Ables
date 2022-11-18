package com.example.m_ables

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.m_ables.databinding.ActivityMainBinding
import com.example.m_ables.databinding.FragmentAuthBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.frame_layout_main)

        if(checkConnection()){
            //val config = AppBarConfiguration(navController.graph)
            val appBarConfiguration = AppBarConfiguration(setOf(R.id.studentsFragment,
                R.id.lessonsFragment, R.id.accountFragment))
            findViewById<Toolbar>(R.id.toolbar).setupWithNavController(navController, appBarConfiguration)
            NavigationUI.setupWithNavController(binding.bottomNavView, navController)
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.authFragment || destination.id == R.id.registerFragment ||
                destination.id == R.id.forgotPasswordFragment || destination.id == R.id.userTypeFragment) {
                binding.bottomNavView.visibility = View.GONE
                binding.toolbar.visibility = View.GONE
            } else {
                binding.bottomNavView.visibility = View.VISIBLE
                binding.toolbar.visibility = View.VISIBLE
            }
        }
    }

    private fun checkConnection(): Boolean {
        val connectManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectManager.activeNetworkInfo
        return if (networkInfo != null && networkInfo.isConnected) {
            true
        } else {
            Toast.makeText(applicationContext, resources.getString(R.string.no_internet), Toast.LENGTH_LONG).show()
            false
        }
    }


}