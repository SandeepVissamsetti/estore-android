package com.sample.estore.data.api

import com.sample.estore.data.StoreApiService
import com.sample.estore.data.model.StoreItemResponse
import com.sample.estore.di.BackgroundScheduler
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class StoreApiClient @Inject constructor(
    private val apiService: StoreApiService,
    @BackgroundScheduler private val backgroundScheduler: Scheduler
) : StoreRemoteDataSource {
    override fun getShoppingList(pageId: Int, pageSize: Int): Observable<StoreItemResponse> {
        return apiService.getShoppingList(pageId, pageSize)
            .subscribeOn(backgroundScheduler)
            .map { it.body() }
    }
}

