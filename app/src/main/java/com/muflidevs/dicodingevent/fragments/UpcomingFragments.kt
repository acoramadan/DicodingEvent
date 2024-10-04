
package com.muflidevs.dicodingevent.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muflidevs.dicodingevent.R
import com.muflidevs.dicodingevent.databinding.FragmentUpcomingFragmentsBinding

class UpcomingFragments : Fragment() {
    private lateinit var binding : FragmentUpcomingFragmentsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpcomingFragmentsBinding.inflate(inflater,container,false)
        return binding.root
    }

}