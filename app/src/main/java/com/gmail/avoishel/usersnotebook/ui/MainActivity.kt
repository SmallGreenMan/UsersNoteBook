package com.gmail.avoishel.usersnotebook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.gmail.avoishel.usersnotebook.R
import com.gmail.avoishel.usersnotebook.databinding.ActivityMainBinding
import com.gmail.avoishel.usersnotebook.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = this.findNavController(R.id.userNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->  }
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.userNavHostFragment)
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
    }
}