package com.sample.estore.data.repository

import com.sample.estore.data.api.StoreRemoteDataSource
import com.sample.estore.data.model.toDomain
import com.sample.estore.domain.model.StoreItem
import io.reactivex.Observable
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(private val storeRemoteDataSource: StoreRemoteDataSource) :
    StoreRepository {
    override fun getStoreItems(pageId: Int, pageSize: Int): Observable<List<StoreItem>> {
        return storeRemoteDataSource.getShoppingList(pageId, pageSize)
            .map { it.embedded.products }
            .map { products ->
                products.map { it.toDomain() }
            }
    }
}