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

class VerticalListAdapter(private val context : Context) : ListAdapter<DetailData,
        VerticalListAdapter.MyViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemVerticalBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder : MyViewHolder, position : Int) {
        val imageLogo = getItem(position)
        val title = getItem(position)
        holder.bind(imageLogo)
        holder.bind(title)
    }
    inner class MyViewHolder(val binding : ItemVerticalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detail : DetailData) {
            Glide.with(context)
                .load(detail.imageLogo)
                .into(binding.image)
            binding.judul.text = "${detail.name}"
            binding.penyelenggara.text = "${detail.ownerName}"
            binding.waktu.text = getWaktu(detail)
        }
        fun getWaktu(detail: DetailData) : String {
            return "${detail.beginTime} - ${detail.endTime}"
        }
    }
    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailData>() {
            override fun areContentsTheSame(oldItem: DetailData, newItem: DetailData): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: DetailData, newItem: DetailData): Boolean {
                return oldItem == newItem
            }
        }
    }
}