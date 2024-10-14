package com.muflidevs.dicodingevent.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.muflidevs.dicodingevent.networking.Network
import com.muflidevs.dicodingevent.data.remote.response.DetailData
import com.muflidevs.dicodingevent.databinding.FragmentUpcomingFragmentsBinding
import com.muflidevs.dicodingevent.ui.DetailActivity
import com.muflidevs.dicodingevent.ui.settings.SettingsActivity
import com.muflidevs.dicodingevent.ui.adapter.SpaceItemDecoration
import com.muflidevs.dicodingevent.ui.viewmodel.MainViewModel
import com.muflidevs.dicodingevent.ui.adapter.VerticalListAdapter

class UpcomingFragments : Fragment() {
    private lateinit var rvVerticalAdapter: VerticalListAdapter
    private lateinit var binding: FragmentUpcomingFragmentsBinding

    //createview
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpcomingFragmentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        val mainViewVertical = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
        mainViewVertical.listEvent.observe(viewLifecycleOwner) { value ->
            setEventDataVertical(value)
        }
        mainViewVertical.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
        if (!Network.isNetworkAvailable(context)) {
            Toast.makeText(
                context,
                "No internet connection. Please turn on your network",
                Toast.LENGTH_LONG
            ).show()
            mainViewVertical.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
        }
        binding.settings.setOnClickListener {
            val intent = Intent(requireContext(), SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setEventDataVertical(event: List<DetailData>) {
        rvVerticalAdapter.submitList(event)
    }

    private fun setupRecyclerView() {

        rvVerticalAdapter = VerticalListAdapter(requireContext()) { detailData ->
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("EXTRA_DETAIL", detailData)
            startActivity(intent)
        }
        binding.verticalOnly.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        binding.verticalOnly.adapter = rvVerticalAdapter
        binding.verticalOnly.addItemDecoration(SpaceItemDecoration(24))
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}