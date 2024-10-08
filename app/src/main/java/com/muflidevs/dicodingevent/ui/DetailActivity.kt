package com.muflidevs.dicodingevent.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.muflidevs.dicodingevent.data.response.DetailData
import com.muflidevs.dicodingevent.databinding.ActivityDetailBinding



class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailData: DetailData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        detailData = intent.getParcelableExtra("EXTRA_DETAIL") ?: return

        displayEventDetails(detailData)

        binding.back.setOnClickListener{
            finish()
        }
    }

    private fun displayEventDetails(detailData: DetailData) {
        binding.name.text = detailData.name
        binding.ownerName.text = detailData.ownerName
        binding.quota.text = detailData.quota?.toString()
        binding.beginTime.text = detailData.beginTime
        binding.description.text = detailData.summary

        Glide.with(this)
            .load(detailData.imageLogo)
            .into(binding.image)
    }
}
