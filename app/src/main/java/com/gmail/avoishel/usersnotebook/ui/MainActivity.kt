package com.gmail.avoishel.usersnotebook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
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

        binding.bottomNavigationView.setupWithNavController(navController)

        // --- hiding bottomNavigationMenu with some fragments
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.userListFragment -> showBottomNav()
                R.id.favoriteFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.userNavHostFragment)
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
    }
}