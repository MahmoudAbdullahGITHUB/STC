package com.example.stc.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stc.data.remote.model.response.charachter.Item
import com.example.stc.databinding.ItemListItemBinding

class SeriesItemsAdapter : ListAdapter<Item, SeriesItemsAdapter.SeriesItemsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesItemsViewHolder {
        val binding =
            ItemListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesItemsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class SeriesItemsViewHolder(private val binding: ItemListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(series: Item?) {
            binding.comicItemNameTV.text = series?.name
            val imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/9/b0/4c7d666c0e58a.jpg"
            Glide.with(binding.comicItemNameIV.context)
                .load(imageUrl)
                .into(binding.comicItemNameIV)
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.resourceURI == newItem.resourceURI
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}
