package com.example.stc.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stc.data.remote.model.response.charachter.Item
import com.example.stc.databinding.ItemListItemBinding

class ComicsItemsAdapter : ListAdapter<Item, ComicsItemsAdapter.ComicsViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val binding =
            ItemListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ComicsViewHolder(private val binding: ItemListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: Item?) {
            binding.comicItemNameTV.text = comic?.name
            val imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/80/5e3d7536c8ada.jpg"
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
