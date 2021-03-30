package com.sample.estore.data.repository

import com.sample.estore.domain.model.StoreItem
import io.reactivex.Observable

interface StoreRepository {
    fun getStoreItems(pageId: Int, pageSize: Int): Observable<List<StoreItem>>
}