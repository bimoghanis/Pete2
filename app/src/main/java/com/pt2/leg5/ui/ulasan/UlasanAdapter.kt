package com.pt2.leg5.ui.ulasan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pt2.leg5.R
import com.pt2.leg5.db.UlasanEntity

class UlasanAdapter : ListAdapter<UlasanEntity, UlasanAdapter.UlasanViewHolder>(UlasanDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UlasanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_ulasan, parent, false)
        return UlasanViewHolder(view)
    }

    override fun onBindViewHolder(holder: UlasanViewHolder, position: Int) {
        val ulasan = getItem(position)
        holder.bind(ulasan)
    }

    inner class UlasanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewRestaurantName: TextView = itemView.findViewById(R.id.textViewRestaurantName)
        private val textViewRestaurantAddress: TextView = itemView.findViewById(R.id.textViewRestaurantAddress)
        private val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        private val textViewCleanStatus: TextView = itemView.findViewById(R.id.textViewCleanStatus)

        fun bind(ulasan: UlasanEntity) {
            textViewRestaurantName.text = ulasan.restaurantName
            textViewRestaurantAddress.text = ulasan.restaurantAddress
            textViewDescription.text = ulasan.description
            textViewCleanStatus.text = if (ulasan.isClean) "Clean" else "Not Clean"
        }
    }

    private class UlasanDiffCallback : DiffUtil.ItemCallback<UlasanEntity>() {
        override fun areItemsTheSame(oldItem: UlasanEntity, newItem: UlasanEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UlasanEntity, newItem: UlasanEntity): Boolean {
            return oldItem == newItem
        }
    }
}
