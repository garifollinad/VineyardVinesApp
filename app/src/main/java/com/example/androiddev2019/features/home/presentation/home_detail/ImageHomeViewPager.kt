package com.example.androiddev2019.features.home.presentation.home_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ImageHomeViewPager(val images: List<String>, fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val fragment = ItemImageFragment()
        val bundle = Bundle()
        bundle.putString(IMAGE_HOME, images.get(position))
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int = images.size

    companion object {
        const val IMAGE_HOME = "IMAGE_HOME"
    }

}