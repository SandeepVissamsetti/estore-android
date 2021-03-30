package com.sample.estore.ui.providers

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

class ImageResourceLoaderImpl(private val context: Context) : ImageResourceLoader {

    private val glideRequestManager: RequestManager = Glide.with(context)

    override fun loadImageIntoView(
        url: String,
        imageViewToLoad: ImageView,
        resultCallback: ImageLoaderCallback?
    ) {
        getDefaultRequestBuilder(url)
            .listener(getRequestListener(resultCallback))
            .apply(getDefaultRequestOptions())
            .into(imageViewToLoad)
    }

    private fun getDefaultRequestBuilder(url: String): RequestBuilder<Drawable> {
        return glideRequestManager.load(url)
    }

    private fun getDefaultRequestOptions(): RequestOptions {
        return RequestOptions()
            .format(DecodeFormat.PREFER_ARGB_8888)
            .skipMemoryCache(false)
    }

    private fun getRequestListener(resultCallback: ImageLoaderCallback?): RequestListener<Drawable> {
        return object : RequestListener<Drawable> {
            override fun onResourceReady(
                resource: Drawable,
                model: Any,
                target: Target<Drawable>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                return resultCallback?.onLoadingSuccess(resource, target) ?: false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any,
                target: Target<Drawable>,
                isFirstResource: Boolean
            ): Boolean {
                return resultCallback?.onLoadingError() ?: false
            }
        }
    }
}