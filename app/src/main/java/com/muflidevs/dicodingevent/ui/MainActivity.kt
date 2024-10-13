package com.muflidevs.dicodingevent.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.replace
import com.google.android.material.navigation.NavigationBarView
import com.muflidevs.dicodingevent.R
import com.muflidevs.dicodingevent.databinding.ActivityMainBinding
import com.muflidevs.dicodingevent.fragments.FavoriteFragment
import com.muflidevs.dicodingevent.fragments.FinishedFragment
import com.muflidevs.dicodingevent.fragments.HomeFragment
import com.muflidevs.dicodingevent.fragments.UpcomingFragments

class MainActivity : AppCompatActivity(){
    private lateinit var binding : ActivityMainBinding
    private lateinit var navigationBar : NavigationBarView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigationBar = binding.bottomNavigation
        navBarClick()
    }

    private fun navBarClick() {
        navigationBar = binding.bottomNavigation

        val fragmentManager = supportFragmentManager
        val finishedFragment = FinishedFragment()
        val homeFragment = HomeFragment()
        val upcomingFragment = UpcomingFragments()
        val favoriteFragment = FavoriteFragment()
        fragmentManager.beginTransaction().replace(binding.frameContainer.id,homeFragment,HomeFragment::class.java.simpleName).commit()
        navigationBar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.upcoming -> {
                    fragmentManager.beginTransaction()
                        .replace(binding.frameContainer.id,
                            upcomingFragment,
                            UpcomingFragments::class.java.simpleName)
                        .commit()
                    true
                }
                R.id.finished -> {
                    fragmentManager.beginTransaction()
                        .replace(binding.frameContainer.id
                            ,finishedFragment,
                            FinishedFragment::class.java.simpleName)
                        .commit()
                    true
                }
                R.id.home -> {
                    fragmentManager.beginTransaction()
                        .replace(binding.frameContainer.id
                            ,homeFragment
                            ,HomeFragment::class.java.simpleName)
                        .commit()
                    true
                }
                R.id.favorite -> {
                    fragmentManager.beginTransaction()
                        .replace(binding.frameContainer.id
                        ,favoriteFragment
                        ,FavoriteFragment::class.java.simpleName).commit()
                    true
                }
                else -> false
            }
        }
    }
}