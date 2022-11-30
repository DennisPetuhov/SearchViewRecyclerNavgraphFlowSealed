package com.example.searchviewrecyclernavgraphflowsealed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.searchviewrecyclernavgraphflowsealed.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        bottomNavigation()
    }

    fun bottomNavigation() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mmm) as NavHostFragment
        val navController = navHostFragment.navController

        binding.myBottomBar.setupWithNavController(navController)

//
//        val controller=findNavController(R.id.mmm)
//        binding.myBottomBar.setupWithNavController(controller)
    }
}