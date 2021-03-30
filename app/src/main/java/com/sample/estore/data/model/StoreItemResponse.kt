package com.sample.estore.data.model

import com.google.gson.annotations.SerializedName
import com.sample.estore.domain.model.StoreItem

data class StoreItemResponse(
    @SerializedName("_embedded") val embedded: Embedded,
)

data class ImageUrls(
    @SerializedName("url") val url: String,
    @SerializedName("thumbnail") val thumbnail: String
)

data class Embedded(
    @SerializedName("product") val products: List<Product>
)

data class Product(
    @SerializedName("price") val price: Int,
    @SerializedName("final_price") val finalPrice: Int,
    @SerializedName("sku") val sku: String,
    @SerializedName("name") val name: String,
    @SerializedName("short_description") val shortDescription: String,
    @SerializedName("_embedded") val brandDetails: BrandDetails
)


data class BrandDetails(
    @SerializedName("brand") val brand: Brand,
    @SerializedName("images") val images: List<ImageUrls>?
)

data class Brand(@SerializedName("name") val name: String)

fun Product.toDomain(): StoreItem = StoreItem(
    this.name, this.sku, this.finalPrice,
    this.price,
    this.shortDescription,
    this.brandDetails.brand.name,
    this.brandDetails.images?.let { it[0].url }
)