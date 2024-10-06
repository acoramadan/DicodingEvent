package com.muflidevs.dicodingevent

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationBarView
import com.muflidevs.dicodingevent.data.response.EventsResponse
import com.muflidevs.dicodingevent.databinding.ActivityMainBinding
import com.muflidevs.dicodingevent.fragments.FinishedFragment
import com.muflidevs.dicodingevent.fragments.HomeFragment
import com.muflidevs.dicodingevent.fragments.UpcomingFragments
import com.muflidevs.dicodingevent.ui.adapter.HorizontalListAdapter
import com.muflidevs.dicodingevent.ui.adapter.VerticalListAdapter

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
        val fragmentFinishedManager = fragmentManager.findFragmentByTag(FinishedFragment::class.java.simpleName)
        val fragmentHomeManager = fragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)
        val fragmentUpcomingManager = fragmentManager.findFragmentByTag(UpcomingFragments::class.java.simpleName)
        fragmentManager.beginTransaction().add(binding.frameContainer.id,homeFragment,HomeFragment::class.java.simpleName).commit()
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
                else -> false
            }
        }
    }
}