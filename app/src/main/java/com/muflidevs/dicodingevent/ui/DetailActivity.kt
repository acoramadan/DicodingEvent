package com.muflidevs.dicodingevent.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.muflidevs.dicodingevent.data.response.DetailData
import com.muflidevs.dicodingevent.databinding.ActivityDetailBinding
import com.muflidevs.dicodingevent.ui.viewmodel.EventDetailViewModel


class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    private lateinit var viewModel : EventDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[EventDetailViewModel::class.java]


        val eventId = intent.getIntExtra("EXTRA_ID", -1)
        Log.e("DetailActivity",eventId.toString())
        if (eventId != -1) {
            viewModel.eventDetails(eventId)

        }

        // Observe event details LiveData
        viewModel.eventDetail.observe(this) { event ->
            Log.e("DetailActivity.kt", "${event.get(eventId).name}\n$eventId")
           if(event != null) {
               displayEventDetails(event,eventId)
               Log.e("DetailActivity.kt", "${event.get(eventId).name}")
           }
        }
    }
    private fun displayEventDetails(event: List<DetailData>,position : Int) {
        binding.name.text = event.get(position).name
        binding.ownerName.text = event.get(position).ownerName
        binding.quota.text = event.get(position).quota?.toString()
        binding.description.text = event.get(position).summary
        Glide.with(this)
            .load(event.get(position).imageLogo)
            .into(binding.image)
    }
}