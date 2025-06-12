package com.juandelarosa.binkssake

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import binkssake.feature.stores.api.StoresApi
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val storesApi: StoresApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val container = FrameLayout(this).apply {
            id = View.generateViewId()
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        setContentView(container)

        supportFragmentManager.beginTransaction()
            .replace(container.id, storesApi.create())
            .commit()
    }
}
