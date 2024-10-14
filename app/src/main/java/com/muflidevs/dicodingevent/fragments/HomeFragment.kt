package com.muflidevs.dicodingevent.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.muflidevs.dicodingevent.data.remote.response.DetailData
import com.muflidevs.dicodingevent.databinding.FragmentHomeBinding
import com.muflidevs.dicodingevent.networking.Network
import com.muflidevs.dicodingevent.ui.DetailActivity
import com.muflidevs.dicodingevent.ui.settings.SettingsActivity
import com.muflidevs.dicodingevent.ui.viewmodel.MainViewModel
import com.muflidevs.dicodingevent.ui.adapter.HorizontalListAdapter
import com.muflidevs.dicodingevent.ui.adapter.SpaceItemDecoration
import com.muflidevs.dicodingevent.ui.adapter.VerticalListAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var rvHorizontalAdapter: HorizontalListAdapter
    private lateinit var rvVerticalAdapter: VerticalListAdapter
    private var position: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        arguments?.let {
            position = it.getInt("EXTRA_ID", 0)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]

        mainViewModel.listEvent.observe(viewLifecycleOwner) { value ->
            setEventData(value)

        }
        mainViewModel.listEventVertical.observe(viewLifecycleOwner) { value ->
            setEventDataVertical(value)
        }
        mainViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
        if(!Network.isNetworkAvailable(context)) {
            Toast.makeText(context,"No internet connection. Please turn on your network", Toast.LENGTH_LONG).show()
            mainViewModel.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
        }
        binding.settings.setOnClickListener{
            val intent = Intent(requireContext(), SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setEventData(event: List<DetailData>) {
        rvHorizontalAdapter.submitList(event)
    }

    private fun setEventDataVertical(event: List<DetailData>) {
        rvVerticalAdapter.submitList(event)
    }

    private fun setupRecyclerView() {
        rvHorizontalAdapter = HorizontalListAdapter(requireContext()) { detailData ->
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("EXTRA_DETAIL", detailData)
            startActivity(intent)
        }

        binding.horizontalOnly.layoutManager = LinearLayoutManager(context, GridLayoutManager.HORIZONTAL, false)
        binding.horizontalOnly.adapter = rvHorizontalAdapter

        rvVerticalAdapter = VerticalListAdapter(requireContext()) { detailData ->
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("EXTRA_DETAIL", detailData)
            startActivity(intent)
        }

        binding.verticalOnly.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.verticalOnly.adapter = rvVerticalAdapter
        binding.verticalOnly.addItemDecoration(SpaceItemDecoration(24))
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
