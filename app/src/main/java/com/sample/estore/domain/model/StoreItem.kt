package com.sample.estore.domain.model

import android.os.Parcelable
import androidx.core.text.HtmlCompat
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoreItem(
    val name: String,
    val sku: String,
    val finalPrice: String,
    val price: String,
    val shortDescription: String,
    val brandName: String,
    val imageUrl: String?
) : Parcelable {
    val description: String
        get() = HtmlCompat.fromHtml(shortDescription, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}
