
package com.example.androiddev2019.core

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.androiddev2019.R
import com.example.androiddev2019.features.home.presentation.home.WomenClothFragment

object FragmentNavigator {

    fun openFragment(
        activity: BaseActivity,
        screen: String,
        data: Bundle? = null,
        tag: String? = null,
        targetFragment: Fragment? = null,
        requestCode: Int? = null
    ) {
        Log.d("screen_nav", screen)
        val fragment = when (screen) {
            Screen.WOMEN_CLOTH -> {
                WomenClothFragment.newInstance()
            }
            else -> {
                null
            }
        }

        if (targetFragment != null && requestCode != null) {
            fragment?.setTargetFragment(targetFragment, requestCode)
        }
        activity.showFragment(
            fragment = fragment!!,
            tag = tag,
            container = R.id.base_container
        )

    }
}


object Screen {

    const val SCREEN = "SCREEN"
    const val WOMEN_CLOTH = "WOMEN_CLOTH"
}
