package com.sample.estore

import com.sample.estore.data.model.*
import com.sample.estore.domain.model.StoreItem

class TestUtils {
    companion object {

        const val PAGE_ID = 1
        const val PAGE_COUNT = 30

        fun createStoreData() = listOf(StoreItem("name", "1234", "5.0", "10.0", "des", "brand", ""))

        fun createStoreResponse() =
            StoreItemResponse(Embedded(listOf(createProduct())))

        private fun createProduct(): Product {
            return Product(
                10.00,
                5.00,
                "1234",
                "name",
                "des",
                BrandDetails(
                    Brand("brand"),
                    listOf(ImageUrls("", ""))
                )
            )
        }
    }
}