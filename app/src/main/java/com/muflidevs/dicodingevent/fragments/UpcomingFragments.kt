
package com.muflidevs.dicodingevent.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.muflidevs.dicodingevent.data.response.DetailData
import com.muflidevs.dicodingevent.databinding.FragmentUpcomingFragmentsBinding
import com.muflidevs.dicodingevent.ui.DetailActivity
import com.muflidevs.dicodingevent.ui.viewmodel.MainViewModel
import com.muflidevs.dicodingevent.ui.adapter.VerticalListAdapter

class UpcomingFragments : Fragment() {
    private lateinit var rvVerticalAdapter: VerticalListAdapter
    private lateinit var binding : FragmentUpcomingFragmentsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpcomingFragmentsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        val mainViewVertical = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]
        mainViewVertical.listEvent.observe(viewLifecycleOwner) { value ->
            setEventDataVertical(value)
        }
        mainViewVertical.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }
    private fun setEventDataVertical(event : List<DetailData>) {
        rvVerticalAdapter.submitList(event)
    }
    private fun setupRecyclerView() {

        rvVerticalAdapter = VerticalListAdapter(requireContext()) { detailData ->
            val intent = Intent(context,DetailActivity::class.java)
            startActivity(intent)
        }
        binding.verticalOnly.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,false)
        binding.verticalOnly.adapter = rvVerticalAdapter
    }

    private fun showLoading(isLoading : Boolean) {
        binding.progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
    }
}