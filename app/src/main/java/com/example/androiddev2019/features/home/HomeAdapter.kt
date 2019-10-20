package com.example.androiddev2019.features.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androiddev2019.R
import com.example.androiddev2019.core.model.Cloth
import kotlinx.android.synthetic.main.recycleview_row.view.*
import java.util.*

class HomeAdapter(val listener: HomeListener, val listCloth: ArrayList<Cloth>): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycleview_row, parent,false))
    }

    override fun getItemCount(): Int =  listCloth.size

    override fun onBindViewHolder(viewHolder: HomeViewHolder, position: Int) {
        viewHolder.bindView(listCloth.get(position))
        viewHolder.itemView.setOnClickListener {
            listener.onClick(listCloth.get(position))
        }
    }

    class HomeViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bindView(item: Cloth) = with(view) {
            tvShortDescription.text = item.shortDescription
            tvName.text = item.name
            tvTime.text = item.date
            Glide
                .with(context)
                .load(item.pictureUrl)
                .into(imgCloth)

            shareMe.setOnClickListener {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                val shareBody = tvShortDescription.text.toString()
                val shareSub = "HOT SALES! 50% off"
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub)
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
                context.startActivity(Intent.createChooser(sharingIntent, "Share using"))
            }
        }

    }
}

interface HomeListener {
    fun onClick(item: Cloth)
}