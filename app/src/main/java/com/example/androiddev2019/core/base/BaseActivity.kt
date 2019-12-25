
package com.example.androiddev2019.core.base

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.example.androiddev2019.R

abstract class BaseActivity : AppCompatActivity(){


    companion object {
        const val ANIMATION = "animation"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setLanguage("ru")
    }

    public override fun onResume() {
        super.onResume()
        //checkForRoot()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        }
        else {
            super.onBackPressed()
        }
    }

    fun showFragment(fragment: Fragment,
                     tag: String? = null,
                     container: Int = R.id.base_container,
                     withDelay: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
        if (tag.isNullOrEmpty()) {
            transaction.replace(container, fragment, tag)
        }
        else {
            transaction.replace(container, fragment, tag)
                .addToBackStack(tag)
        }
        if (withDelay) {
            Handler().postDelayed({
                transaction.commit()
            }, 2000)
        }
        else {
            transaction.commit()
        }
    }

    fun showActivity(context: Context, cls: Class<*>, finish: Boolean) {
        Handler().postDelayed({
            val intent = Intent(context,cls)
            startActivity(intent)
            if (finish) {
                finishAffinity()
            }
        },2500)
    }

    fun showActivity(context: Context?, cls: Class<*>) {
        val intent = Intent(context, cls)
        startActivity(intent)
        finishAffinity()
    }


}



