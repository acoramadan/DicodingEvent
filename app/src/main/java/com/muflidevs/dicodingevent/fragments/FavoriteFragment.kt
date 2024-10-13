package com.muflidevs.dicodingevent.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.muflidevs.dicodingevent.databinding.FragmentFavoriteBinding
import com.muflidevs.dicodingevent.ui.DetailActivity
import com.muflidevs.dicodingevent.ui.adapter.FavoriteListAdapter
import com.muflidevs.dicodingevent.ui.adapter.SpaceItemDecoration
import com.muflidevs.dicodingevent.ui.viewmodel.MainViewModelFavorite
import com.muflidevs.dicodingevent.ui.viewmodel.ViewModelFactory


class FavoriteFragment : Fragment() {
    private var _binding : FragmentFavoriteBinding? = null
    private lateinit var viewModel : MainViewModelFavorite
    private lateinit var adapter : FavoriteListAdapter
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = obtainViewModel()

        adapter = FavoriteListAdapter(requireContext()) { detailData ->
            val intent = Intent(requireContext(),DetailActivity::class.java)
            intent.putExtra("EXTRA_DETAIL",detailData)
            startActivity(intent)
        }

        binding.rvFavorite.adapter = adapter
        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavorite.addItemDecoration(SpaceItemDecoration(24))

        this.viewModel.getAllEvents().observe(viewLifecycleOwner){ events ->
            if(events != null) adapter.submitList(events)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun obtainViewModel() : MainViewModelFavorite {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        return ViewModelProvider(this,factory)[MainViewModelFavorite::class.java]
    }

}