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
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailData: DetailData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil objek DetailData dari Intent
        detailData = intent.getParcelableExtra<DetailData>("EXTRA_DETAIL") ?: return

        // Tampilkan data di View
        displayEventDetails(detailData)
    }

    private fun displayEventDetails(detailData: DetailData) {
        binding.name.text = detailData.name
        binding.ownerName.text = detailData.ownerName
        binding.quota.text = detailData.quota?.toString()
        binding.description.text = detailData.summary

        Glide.with(this)
            .load(detailData.imageLogo)
            .into(binding.image)
    }
}
