package com.example.storyapplication.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storyapplication.R
import com.example.storyapplication.activities.DetailActivity
import androidx.core.util.Pair as APair
import com.example.storyapplication.databinding.ItemRvStoryBinding
import com.example.storyapplication.responses.ListStoryItem
import com.example.storyapplication.utilities.NavigationUtil

class StoryAdapter(private val context: Context) :
    PagingDataAdapter<ListStoryItem, StoryAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRvStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    inner class MyViewHolder(private val binding: ItemRvStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListStoryItem) {
            var imgPhoto: ImageView = itemView.findViewById(R.id.image)
            var tvName: TextView = itemView.findViewById(R.id.name)

            binding.apply {
                name.text = item.name
            }

            Glide.with(context)
                .load(item.photoUrl)
                .into(binding.image)

            binding.root.setOnClickListener {
                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        context as Activity,
                        APair(imgPhoto as View, "image"),
                        APair(tvName as View, "name")
                    )

                NavigationUtil.toDetail(context, DetailActivity::class.java, item.id, optionsCompat.toBundle())
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListStoryItem>() {
            override fun areItemsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}