package com.muflidevs.dicodingevent.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muflidevs.dicodingevent.R
import com.muflidevs.dicodingevent.data.response.DetailData
import com.muflidevs.dicodingevent.data.response.EventsResponse
import com.muflidevs.dicodingevent.data.retrofit.ApiConfig
import com.muflidevs.dicodingevent.databinding.FragmentHomeBinding
import com.muflidevs.dicodingevent.databinding.ItemHorizontalBinding
import com.muflidevs.dicodingevent.ui.adapter.HorizontalListAdapter
import com.muflidevs.dicodingevent.ui.adapter.VerticalListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private lateinit var rvHorizontal : RecyclerView
    private lateinit var rvVertical : RecyclerView
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
        findEvent(8642)

    }
    private fun findEvent(eventId : Int) {
        val client = ApiConfig.getApiService().getEvents(eventId)
        client.enqueue(object : Callback<EventsResponse> {
            override fun onResponse(
                call : Call<EventsResponse>,
                response: Response<EventsResponse>
            ) {
                if(response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody!=null) {
                        setEventData(responseBody.data)
                    }
                }else {
                    Log.e(TAG,"onFailure : ${response.message()}")
                }
            }

            override fun onFailure(p0: Call<EventsResponse>, p1: Throwable) {
               Log.e(TAG,"onFailure : ${p1.message}")
            }
        })
    }

    private fun setEventData(event : List<DetailData>) {
            rvHorizontalAdapter.submitList(event)
    }
    private fun setupRecyclerView() {
        rvHorizontalAdapter = HorizontalListAdapter(requireContext())
        binding.horizontalOnly.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.horizontalOnly.adapter = rvHorizontalAdapter
    }
    private fun showRecyleList() {

    }
}