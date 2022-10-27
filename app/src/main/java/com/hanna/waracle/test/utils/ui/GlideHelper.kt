package com.hanna.waracle.test.utils.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hanna.waracle.test.R

class GlideHelper : ImageLoader {
    override fun loadImage(url: String, view: ImageView) {
        Glide.with(view).load(url).placeholder(R.drawable.placeholder).into(view)
    }
}