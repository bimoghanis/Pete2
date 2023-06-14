package com.pt2.leg5.ui.ulasan

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pt2.leg5.R
import com.pt2.leg5.db.UlasanEntity

class UlasanAdapter : ListAdapter<UlasanEntity, UlasanAdapter.UlasanViewHolder>(UlasanDiffCallback()) {

    private var onItemClick: ((UlasanEntity) -> Unit)? = null

    fun setOnItemClickListener(listener: (UlasanEntity) -> Unit) {
        onItemClick = listener
    }

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
        private val textViewPackaging: TextView = itemView.findViewById(R.id.textViewPackaging)
        private val textViewPelayanan: TextView = itemView.findViewById(R.id.textViewPelayanan)
        private val textViewRekomendasi: TextView = itemView.findViewById(R.id.textViewRekomendasi)
        private val imageViewPhoto: ImageView = itemView.findViewById(R.id.imageViewPhoto)

        fun bind(ulasan: UlasanEntity) {
            textViewRestaurantName.text = ulasan.restaurantName
            textViewRestaurantAddress.text = ulasan.restaurantAddress
            textViewDescription.text = ulasan.description
            textViewCleanStatus.text = if (ulasan.isClean) "Clean 👍" else "Not Clean"
            textViewPackaging.text = if (ulasan.packaging) "Take Away 👍" else "Dine in only"
            textViewPelayanan.text = if (ulasan.services) "Good Services 👍" else "Bad Services"
            textViewRekomendasi.text = if (ulasan.recommend) "Recommended 😘" else "Not Recommended"

            val photoo = ulasan.photoo // Simpan nilai photoo dalam variabel lokal
            if (photoo != null) {
                val bitmap = BitmapFactory.decodeByteArray(photoo, 0, photoo.size)
                imageViewPhoto.setImageBitmap(bitmap)
            } else {
                // Setel gambar placeholder atau tampilkan sesuatu jika tidak ada gambar
            }
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val ulasan = getItem(position)
                    onItemClick?.invoke(ulasan)
                }
            }
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
