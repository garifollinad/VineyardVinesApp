package com.example.androiddev2019.features.home.presentation.type

import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.androiddev2019.R
import com.example.androiddev2019.features.home.data.model.Type

class HomeClothTypeAdapter(data: List<Type>) :
    BaseQuickAdapter<Type, BaseViewHolder>(R.layout.recycleview_row_cloth_type, data) {

    override fun convert(helper: BaseViewHolder?, item: Type) {
        helper?.let { holder ->
            val tvName = holder.itemView.findViewById<TextView>(R.id.tvName)
            tvName.text = item.type
            Glide
                .with(holder.itemView)
                .load(item.typeImage)
                .into((holder.getView(R.id.imgCloth)))
        }
    }

    fun clearAll() {
        data.clear()
        notifyDataSetChanged()
    }
}
