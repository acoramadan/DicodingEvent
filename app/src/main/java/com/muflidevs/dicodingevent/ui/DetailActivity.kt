package com.muflidevs.dicodingevent.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.muflidevs.dicodingevent.data.response.DetailData
import com.muflidevs.dicodingevent.databinding.ActivityDetailBinding
import com.muflidevs.dicodingevent.ui.viewmodel.MainViewModelFavorite
import com.muflidevs.dicodingevent.ui.viewmodel.ViewModelFactory


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailData: DetailData
    private lateinit var eventInsertViewModel : MainViewModelFavorite
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eventInsertViewModel = obtainViewModel(this@DetailActivity)
        @Suppress("DEPRECATION")
        detailData = intent.getParcelableExtra("EXTRA_DETAIL") ?: return

        displayEventDetails(detailData)

        binding.filledButton.setOnClickListener {
            val link: String = detailData.link.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(intent)
        }
        binding.back.setOnClickListener {
            finish()
        }
        binding.btnFavorite.setOnClickListener {
            saveFavoriteEvent()
        }
    }

    private fun displayEventDetails(detailData: DetailData) {
        binding.name.text = detailData.name
        binding.ownerName.text = detailData.ownerName
        binding.quota.text = (detailData.quota - detailData.registrants).toString()
        binding.beginTime.text = detailData.beginTime
        binding.category.text = detailData.category
        binding.description.text = Html.fromHtml(detailData.description,Html.FROM_HTML_MODE_LEGACY)
        Glide.with(this)
            .load(detailData.imageLogo)
            .into(binding.image)
    }

    private fun obtainViewModel(activity : AppCompatActivity): MainViewModelFavorite {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModelFavorite::class.java]
    }

    private fun saveFavoriteEvent() {
        val title = binding.name.text.toString()
        val ownerName = binding.ownerName.text.toString()
        val quota = binding.quota.text.toString().toInt()
        val beginTime = binding.beginTime.text.toString()
        val category = binding.category.text.toString()
        val description = binding.description.text.toString()
        val imageLogo = detailData.imageLogo

        detailData.apply {
            name = title
            this.ownerName = ownerName
            this.quota = quota
            this.beginTime = beginTime
            this.category = category
            this.description = description
        }

        eventInsertViewModel.insert(detailData)

        showToast("Event berhasil ditambahkan ke favorit")
    }
    private fun showToast(message:String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }
    companion object {
        const val EXTRA_EVENT = "extra_event"
    }
}
