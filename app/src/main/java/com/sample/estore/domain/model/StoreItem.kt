package com.sample.estore.domain.model

data class StoreItem(
    val name: String,
    val sku: String,
    val finalPrice: String,
    val price: String,
    val shortDescription: String,
    val brandName: String,
    val imageUrl: String?
)