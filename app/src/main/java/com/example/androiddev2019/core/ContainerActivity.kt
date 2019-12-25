package com.example.androiddev2019.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.androiddev2019.R

class ContainerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        val screen =  intent.getStringExtra(Screen.SCREEN)
        FragmentNavigator.openFragment(this, screen, intent.extras)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {

        fun start(context: Context?, bundle: Bundle? = null, requestCode: Int? = null) {
            val intent = Intent(context, ContainerActivity::class.java)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            if (context is Activity) {
                if (requestCode == null) {
                    context.startActivity(intent)
                }
                else {
                    context.startActivityForResult(intent, requestCode)
                }
            }
        }

        fun start(fragment: Fragment?, bundle: Bundle? = null, requestCode: Int? = null) {
            val intent = Intent(fragment?.activity, ContainerActivity::class.java)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            if (requestCode == null) {
                fragment?.startActivity(intent)
            }
            else {
                fragment?.startActivityForResult(intent, requestCode)
            }
        }
    }
}

