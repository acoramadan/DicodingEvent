package com.muflidevs.dicodingevent.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.muflidevs.dicodingevent.R
import com.muflidevs.dicodingevent.data.database.local.entity.DetailDataEntity
import com.muflidevs.dicodingevent.data.remote.response.DetailData
import com.muflidevs.dicodingevent.databinding.ActivityDetailBinding
import com.muflidevs.dicodingevent.ui.viewmodel.MainViewModelFavorite
import com.muflidevs.dicodingevent.ui.viewmodel.ViewModelFactory


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailData: DetailData
    private lateinit var eventInsertViewModel: MainViewModelFavorite
    private var favorite = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eventInsertViewModel = obtainViewModel(this@DetailActivity)
        @Suppress("DEPRECATION")
        detailData = intent.getParcelableExtra("EXTRA_DETAIL") ?: return


        displayEventDetails(detailData)
        isFavorite()

        binding.filledButton.setOnClickListener {
            val link: String = detailData.link.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(intent)
        }

        binding.back.setOnClickListener {
            finish()
        }

        binding.btnFavorite.setOnClickListener {
            if (!favorite) saveFavoriteEvent()
            else deleteFavoriteEvent()
        }

    }


    private fun displayEventDetails(detailData: DetailData) {
        binding.name.text = detailData.name
        binding.ownerName.text = detailData.ownerName
        binding.quota.text = (detailData.quota - detailData.registrants).toString()
        binding.beginTime.text = detailData.beginTime
        binding.category.text = detailData.category
        binding.description.text = Html.fromHtml(detailData.description, Html.FROM_HTML_MODE_LEGACY)
        Glide.with(this)
            .load(detailData.imageLogo)
            .into(binding.image)
    }


    private fun obtainViewModel(activity: AppCompatActivity): MainViewModelFavorite {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModelFavorite::class.java]
    }

    private fun deleteFavoriteEvent() {
        eventInsertViewModel.delete(detailData)
        Toast.makeText(this, "Event berhasil dihapus dari daftar favorit", Toast.LENGTH_SHORT)
            .show()
    }

    private fun saveFavoriteEvent() {
        val title = binding.name.text.toString()
        val ownerName = binding.ownerName.text.toString()
        val quota = detailData.quota
        val beginTime = binding.beginTime.text.toString()
        val category = binding.category.text.toString()
        val description = binding.description.text.toString()

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

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun isFavorite() {
        eventInsertViewModel.getAllEvents().observe(this) { favoriteEvents ->
            favorite = favoriteEvents.any { it.id == detailData.id }
            updateIcon()
        }
    }

    private fun updateIcon() {
        if (favorite) {
            binding.btnFavorite.setImageResource(R.drawable.favorite_added)
            binding.btnFavorite.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.favorite))
            binding.btnFavorite.imageTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
        } else {
            binding.btnFavorite.setImageResource(R.drawable.favorite)
            binding.btnFavorite.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white))
            binding.btnFavorite.imageTintList =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.favorite))
        }
    }
}
