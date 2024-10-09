package com.muflidevs.dicodingevent.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
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
        binding.filledButton.setOnClickListener {
            val link : String = detailData.link.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(intent)
        }
        binding.back.setOnClickListener{
            finish()
        }
    }

    private fun displayEventDetails(detailData: DetailData) {
        binding.name.text = detailData.name
        binding.ownerName.text = detailData.ownerName
        binding.quota.text = (detailData.quota - detailData.registrans).toString()
        binding.beginTime.text = detailData.beginTime
        binding.category.text = detailData.category
        binding.description.text = Html.fromHtml(detailData.description,Html.FROM_HTML_MODE_LEGACY)
        Glide.with(this)
            .load(detailData.imageLogo)
            .into(binding.image)
    }
}
