package com.example.stc.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stc.databinding.CharacterListItemBinding
import com.example.stc.data.remote.model.response.charachter.Result


class ResultsAdapter(
    val navigate: (item: Result?) -> Unit
) : PagingDataAdapter<Result, ResultsAdapter.ItemViewHolder>(ItemDiffCallback()) {



    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = CharacterListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ItemViewHolder(binding)
    }



    inner class ItemViewHolder(private val binding: CharacterListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result?) {
            binding.characterNameTextView.text = item?.name
            val imageUrl = "${item?.thumbnail?.path}.${item?.thumbnail?.extension}"
            Glide.with(binding.characterImageView.context)
                .load(imageUrl)
                .into(binding.characterImageView)


            binding.characterImageView.setOnClickListener{
                navigate(item)
            }
        }
    }
    class ItemDiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
}
