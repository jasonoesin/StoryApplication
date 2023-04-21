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
import com.example.storyapplication.databinding.ItemRvStoryBinding
import com.example.storyapplication.responses.ListStoryItem
import com.example.storyapplication.utilities.NavigationUtil
import androidx.core.util.Pair as APair

class StoryPagingAdapter(private val context: Context):
    PagingDataAdapter<ListStoryItem, StoryPagingAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var binding: ItemRvStoryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryPagingAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding= ItemRvStoryBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryPagingAdapter.ViewHolder, position: Int) {
        val data = getItem(position)
        data?.let { holder.bind(it) }
    }

    //View Holder
    inner class ViewHolder(itemView: ItemRvStoryBinding) : RecyclerView.ViewHolder(itemView.root){
        fun bind(item: ListStoryItem){

            val imgPhoto: ImageView = itemView.findViewById(R.id.image)
            val tvName: TextView = itemView.findViewById(R.id.name)


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