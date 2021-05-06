package com.example.marvel.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel.data.model.Results
import kotlinx.android.synthetic.main.item_layout.view.*
import com.example.marvel.R

class MainAdapter(
    private val results: ArrayList<Results>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(results: Results) {
            itemView.textViewUserName.text = results.name

            Glide.with(itemView.imageViewAvatar.context)
                .load(results.thumbnail.path +"." +results.thumbnail.extension)
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(results[position])

    fun addData(list: List<Results>) {
        results.addAll(list)
    }

}