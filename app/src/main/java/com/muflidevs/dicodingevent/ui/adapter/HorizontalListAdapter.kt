package com.muflidevs.dicodingevent.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muflidevs.dicodingevent.data.response.DetailData
import com.muflidevs.dicodingevent.databinding.ItemHorizontalBinding

class HorizontalListAdapter(private val context : Context) : ListAdapter<DetailData,
        HorizontalListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHorizontalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder : MyViewHolder, position : Int) {
        val detailData = getItem(position)
        holder.bind(detailData)
    }
    inner class MyViewHolder(val binding : ItemHorizontalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detail : DetailData) {
           Glide.with(context)
               .load(detail.imageLogo)
               .into(binding.headerImage)
            binding.title.text = detail.name
        }
    }
    companion object{
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