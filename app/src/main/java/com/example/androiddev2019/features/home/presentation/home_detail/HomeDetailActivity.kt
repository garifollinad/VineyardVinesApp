package com.example.androiddev2019.features.home.presentation.home_detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.androiddev2019.R
import com.example.androiddev2019.features.home.data.model.Cloth
import com.example.androiddev2019.features.home.presentation.home.WomenClothFragment.Companion.HOME_DETAIL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeDetailActivity : AppCompatActivity() {

    var list: ArrayList<String>? = null
    private lateinit var imageViewPager: ImageHomeViewPager
    private lateinit var tvFullDescription: TextView
    private lateinit var tvName: TextView
    private lateinit var backBtnCollapsed: ImageView
    private lateinit var tvTime: TextView
    private lateinit var shareMe: Button
    private lateinit var images: ViewPager
    private lateinit var wormDotsIndicator: com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_detail)
        val cloth = intent.getSerializableExtra(HOME_DETAIL) as Cloth
        Log.d("put_detail_get", cloth.toString())
        bindView()
        setData(cloth)
    }

    private fun setImages(list: ArrayList<String>) {
        imageViewPager = ImageHomeViewPager(list, supportFragmentManager)
        images.adapter = imageViewPager
        wormDotsIndicator.setViewPager(images)
    }

    private fun bindView() {
        backBtnCollapsed = findViewById(R.id.backBtnCollapsed)
        shareMe = findViewById(R.id.shareMe)
        tvName = findViewById(R.id.tvName)
        tvTime = findViewById(R.id.tvTime)
        tvFullDescription = findViewById(R.id.tvFullDescription)
        images = findViewById(R.id.images)
        wormDotsIndicator = findViewById(R.id.wormDotsIndicator)

        backBtnCollapsed.setOnClickListener {
            finish()
        }

        shareMe.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody = tvFullDescription.text.toString()
            val shareSub = "HOT SALES! 50% off"
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub)
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
            this.startActivity(Intent.createChooser(sharingIntent, "Share using"))
        }
    }

    private fun setData(cloth: Cloth){
        tvName.text = cloth.name
        tvFullDescription.text = cloth.fullDescription
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val currentDateTimeString = formatter.format(Date())
        tvTime.text = currentDateTimeString
        list = ArrayList<String>()
        list?.apply{
            add(cloth.pictureUrl)
            add("https://image.shopittome.com/apparel_images/fb/vineyard-vines-vineyard-vines-performance-jersey-half-zip-pullover-abv7a79f7f2_zoom.jpg")
            add("https://n.nordstrommedia.com/ImageGallery/store/product/Zoom/6/_106361186.jpg?h=365&w=240&dpr=2&quality=45&fit=fill&fm=jpg")
            add("https://n.nordstrommedia.com/ImageGallery/store/product/Zoom/17/_104355897.jpg?h=365&w=240&dpr=2&quality=45&fit=fill&fm=jpg")
        }
        Log.d("put_detail_data", list.toString())
        list?.let{
            setImages(it)
        }
    }
}
