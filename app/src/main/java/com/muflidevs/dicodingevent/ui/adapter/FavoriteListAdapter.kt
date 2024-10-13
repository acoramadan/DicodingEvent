package com.muflidevs.dicodingevent.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muflidevs.dicodingevent.data.response.DetailData
import com.muflidevs.dicodingevent.databinding.ItemVerticalBinding

class FavoriteListAdapter(private val context: Context, private val onItemClicked : (DetailData) -> Unit) : ListAdapter<DetailData,FavoriteListAdapter.FavoriteViewHolder>(
    DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent : ViewGroup,viewType : Int) :FavoriteViewHolder {
        val binding = ItemVerticalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val detailData = getItem(position)
        holder.bind(detailData)
    }

    inner class FavoriteViewHolder(private val binding : ItemVerticalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detailData: DetailData) {
            binding.judul.text = detailData.name
            binding.penyelenggara.text = detailData.ownerName
            binding.waktu.text = getWaktu(detailData)
            Glide.with(itemView.context)
                .load(detailData.imageLogo)
                .into(binding.image)

            itemView.setOnClickListener{
                onItemClicked(detailData)
            }
        }
    }
    private fun getWaktu(detail: DetailData) : String {
        return "Waktu Mulai : ${detail.beginTime}\nWaktu Selesai : ${detail.endTime}"
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailData>() {
            override fun areItemsTheSame(oldItem: DetailData, newItem: DetailData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DetailData, newItem: DetailData): Boolean {
                return oldItem == newItem
            }
        }
    }
}
