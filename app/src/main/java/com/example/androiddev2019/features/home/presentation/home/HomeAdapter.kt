package com.example.androiddev2019.features.home.presentation.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androiddev2019.R
import com.example.androiddev2019.features.home.data.model.Cloth
import kotlinx.android.synthetic.main.recycleview_row.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeAdapter(val listener: HomeListener): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var listCloth: ArrayList<Cloth> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycleview_row, parent, false)
        )
    }

    override fun getItemCount(): Int =  listCloth.size

    override fun onBindViewHolder(viewHolder: HomeViewHolder, position: Int) {
        viewHolder.bindView(listCloth.get(position))
        viewHolder.itemView.setOnClickListener {
            listener.onClick(listCloth.get(position))
        }
    }

    fun initCloths(list: ArrayList<Cloth>) {
        listCloth.clear()
        listCloth.addAll(list)
        notifyDataSetChanged()
    }

    class HomeViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bindView(item: Cloth) = with(view) {
            tvShortDescription.text = item.shortDescription
            tvName.text = item.name
            Glide
                .with(context)
                .load(item.pictureUrl)
                .into(imgCloth)
        }
    }
}

interface HomeListener {
    fun onClick(item: Cloth)
}
