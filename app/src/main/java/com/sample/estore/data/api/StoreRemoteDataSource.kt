package com.sample.estore.data.api

import com.sample.estore.data.model.StoreItemResponse
import io.reactivex.Observable

interface StoreRemoteDataSource {
    fun getShoppingList(pageId: Int, pageSize: Int): Observable<StoreItemResponse>
}