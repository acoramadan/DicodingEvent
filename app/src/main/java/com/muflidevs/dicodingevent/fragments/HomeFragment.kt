package com.muflidevs.dicodingevent.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.muflidevs.dicodingevent.data.response.DetailData
import com.muflidevs.dicodingevent.databinding.FragmentHomeBinding
import com.muflidevs.dicodingevent.ui.viewmodel.MainViewModel
import com.muflidevs.dicodingevent.ui.viewmodel.MainViewModelFinish
import com.muflidevs.dicodingevent.ui.adapter.HorizontalListAdapter
import com.muflidevs.dicodingevent.ui.adapter.SpaceItemDecoration
import com.muflidevs.dicodingevent.ui.adapter.VerticalListAdapter

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var rvHorizontalAdapter : HorizontalListAdapter
    private lateinit var rvVerticalAdapter: VerticalListAdapter

    companion object {
        private const val TAG = "HomeFragment"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        val mainViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]
        val mainViewVertical = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[MainViewModelFinish::class.java]
        mainViewModel.listEvent.observe(viewLifecycleOwner){ value ->
            setEventData(value)
        }
        mainViewVertical.listEvent.observe(viewLifecycleOwner) { value ->
            setEventDataVertical(value)
        }
    }
    private fun setEventData(event : List<DetailData>) {
            rvHorizontalAdapter.submitList(event)
    }
    private fun setEventDataVertical(event : List<DetailData>) {
            rvVerticalAdapter.submitList(event)
    }
    private fun setupRecyclerView() {
        rvHorizontalAdapter = HorizontalListAdapter(requireContext())
        binding.horizontalOnly.layoutManager = LinearLayoutManager(context, GridLayoutManager.HORIZONTAL, false)
        binding.horizontalOnly.adapter = rvHorizontalAdapter

        rvVerticalAdapter = VerticalListAdapter(requireContext())
        binding.verticalOnly.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.verticalOnly.adapter = rvVerticalAdapter
        binding.verticalOnly.addItemDecoration(
           SpaceItemDecoration(24)
        )
        binding.verticalOnly.clipToPadding = false
    }
}