package com.muflidevs.dicodingevent.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView
import com.muflidevs.dicodingevent.networking.Network
import com.muflidevs.dicodingevent.R
import com.muflidevs.dicodingevent.data.remote.response.DetailData
import com.muflidevs.dicodingevent.databinding.FragmentFinishedBinding
import com.muflidevs.dicodingevent.ui.DetailActivity
import com.muflidevs.dicodingevent.ui.settings.SettingsActivity
import com.muflidevs.dicodingevent.ui.adapter.SpaceItemDecoration
import com.muflidevs.dicodingevent.ui.viewmodel.MainViewModelFinish
import com.muflidevs.dicodingevent.ui.adapter.VerticalListAdapter


class FinishedFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentFinishedBinding
    private lateinit var searchBar: SearchBar
    private lateinit var searcView: SearchView
    private lateinit var rvVerticalAdapter: VerticalListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searcView = binding.searchView
        searchBar = binding.searchBar
        searchBar.setOnClickListener(this)
        setupRecyclerView()
        val vertical = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModelFinish::class.java]
        vertical.listEvent.observe(viewLifecycleOwner) { value ->
            setEventDataVertical(value)
        }
        vertical.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
        if (!Network.isNetworkAvailable(context)) {
            Toast.makeText(
                context,
                "No internet connection. Please turn on your network",
                Toast.LENGTH_LONG
            ).show()
            vertical.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
        }

    }

    private fun searchFinishedEvents(query: String?) {
        val vertical = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModelFinish::class.java]

        vertical.searchFinishedEvents(0, 20, query)
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
        binding.verticalOnly.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.verticalOnly.adapter = rvVerticalAdapter
        binding.verticalOnly.addItemDecoration(
            SpaceItemDecoration(24)
        )
        binding.verticalOnly.clipToPadding = false

        binding.settings.setOnClickListener {
            val intent = Intent(requireContext(), SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.searchBar -> {
                searcView.visibility = VISIBLE
                with(binding) {
                    searchView.setupWithSearchBar(searchBar)
                    searchView
                        .editText
                        .setOnEditorActionListener { _, _, _ ->
                            val query = searcView.text.toString()
                            searchBar.setText(query)
                            searchView.hide()

                            if (query.isNotEmpty()) {
                                searchFinishedEvents(query)
                            } else {
                                searchFinishedEvents(null)
                            }
                            false
                        }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) VISIBLE else View.GONE
    }
}