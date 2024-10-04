package com.muflidevs.dicodingevent.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView
import com.muflidevs.dicodingevent.R
import com.muflidevs.dicodingevent.databinding.FragmentFinishedBinding


class FinishedFragment : Fragment() {
    private lateinit var binding : FragmentFinishedBinding
    private lateinit var searchBar: SearchBar
    private lateinit var searcView : SearchView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentFinishedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searcView = binding.searchView
        searchBar = binding.searchBar
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    Toast.makeText(this@FinishedFragment.context, searchView.text,Toast.LENGTH_SHORT).show()
                    false
                }
        }
        super.onViewCreated(view, savedInstanceState)
    }

}