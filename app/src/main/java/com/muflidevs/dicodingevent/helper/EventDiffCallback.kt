package com.muflidevs.dicodingevent.helper

import androidx.recyclerview.widget.DiffUtil
import com.muflidevs.dicodingevent.data.response.DetailData

class EventDiffCallback(private val oldEventList : List<DetailData>,
                        private val newEventList : List<DetailData>)
    : DiffUtil.Callback() {
    override fun getNewListSize(): Int = newEventList.size
    override fun getOldListSize() : Int = oldEventList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldEventList[oldItemPosition].id == newEventList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEvent = oldEventList[oldItemPosition]
        val newEvent = newEventList[oldItemPosition]
        return oldEvent.name == newEvent.name && oldEvent.description == newEvent.description

    }
}