package com.example.storyapplication.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storyapplication.R
import com.example.storyapplication.activities.DetailActivity
import androidx.core.util.Pair as APair
import com.example.storyapplication.databinding.ItemRvStoryBinding
import com.example.storyapplication.responses.ListStoryItem
import com.example.storyapplication.utilities.NavigationUtil

class StoryAdapter(private val context: Context, private val data: List<ListStoryItem>): RecyclerView.Adapter<StoryAdapter.ViewHolder>() {
    private lateinit var binding: ItemRvStoryBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding= ItemRvStoryBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    //View Holder
    inner class ViewHolder(itemView: ItemRvStoryBinding) : RecyclerView.ViewHolder(itemView.root){
        fun bind(item: ListStoryItem){

            var imgPhoto: ImageView = itemView.findViewById(R.id.image)
            var tvName: TextView = itemView.findViewById(R.id.name)


            binding.apply {
                name.text = item.name
            }

            Glide.with(context)
                .load(item.photoUrl)
                .into(binding.image)

            binding.root.setOnClickListener {
//
//
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
}