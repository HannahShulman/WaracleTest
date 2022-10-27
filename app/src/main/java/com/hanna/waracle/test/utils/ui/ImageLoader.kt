package com.hanna.waracle.test.utils.ui

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(url: String, view: ImageView)
}