package com.app.demoprojectfortask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.demoprojectfortask.R
import com.app.demoprojectfortask.data.remote.models.PostResponseItem
import com.app.demoprojectfortask.databinding.ItemPostBinding

class PostAdapter(private val posts: List<PostResponseItem>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemPostBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_post, parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    inner class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostResponseItem) {
            binding.post = post
            binding.executePendingBindings()
        }
    }
}
