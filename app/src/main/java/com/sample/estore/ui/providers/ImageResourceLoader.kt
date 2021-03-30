package com.sample.estore.ui.providers

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.target.Target

interface ImageResourceLoader {
    fun loadImageIntoView(url: String, imageViewToLoad: ImageView, resultCallback: ImageLoaderCallback? = null)
}

interface ImageLoaderCallback {

    fun onLoadingSuccess(resource: Drawable, target: Target<Drawable>): Boolean = false

    fun onLoadingError(): Boolean = false
}