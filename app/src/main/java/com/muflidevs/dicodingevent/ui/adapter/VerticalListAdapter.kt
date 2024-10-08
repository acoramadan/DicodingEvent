package com.muflidevs.dicodingevent.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muflidevs.dicodingevent.R
import com.muflidevs.dicodingevent.data.response.DetailData
import com.muflidevs.dicodingevent.databinding.ItemVerticalBinding
import com.muflidevs.dicodingevent.fragments.HomeFragment
import com.muflidevs.dicodingevent.ui.DetailActivity

class VerticalListAdapter(private val context : Context, private val onItemClicked: (DetailData) -> Unit) : ListAdapter<DetailData,
        VerticalListAdapter.MyViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemVerticalBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder : MyViewHolder, position : Int) {
        val detailData = getItem(position)
        holder.bind(detailData)

        holder.itemView.setOnClickListener {
            val fragment = HomeFragment()
            val bundle = Bundle()
            bundle.putInt("EXTRA_ID", position)
            fragment.arguments = bundle

            (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.vertical_only, fragment)
                .addToBackStack(null)
                .commit()
        }

    }
    inner class MyViewHolder(val binding : ItemVerticalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detail : DetailData) {
            Glide.with(context)
                .load(detail.imageLogo)
                .into(binding.image)
            binding.judul.text = "${detail.name}"
            binding.penyelenggara.text = "${detail.ownerName}"
            binding.waktu.text = getWaktu(detail)

            binding.root.setOnClickListener{
                onItemClicked(detail)
            }
        }
        fun getWaktu(detail: DetailData) : String {
            return "Waktu Mulai : ${detail.beginTime}\nWaktu Selesai : ${detail.endTime}"
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