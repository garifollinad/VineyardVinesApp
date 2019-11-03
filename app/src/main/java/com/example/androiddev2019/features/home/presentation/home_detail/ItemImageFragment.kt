package com.example.androiddev2019.features.home.presentation.home_detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.androiddev2019.R
import com.example.androiddev2019.features.home.presentation.home_detail.ImageHomeViewPager.Companion.IMAGE_HOME
import kotlinx.android.synthetic.main.fragment_item_image.*

class ItemImageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageStr = arguments?.getString(IMAGE_HOME)
        Glide
            .with(this)
            .load(imageStr)
            .into(image)
    }


}
