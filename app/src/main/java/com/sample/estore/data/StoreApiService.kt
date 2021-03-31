package com.sample.estore.data

import com.sample.estore.data.model.StoreItemResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface StoreApiService {
    @GET("v1/catalog/products")
    @Headers("Accept: application/json")
    fun getShoppingList(
        @Query("page") pageId: Int,
        @Query("page_size") pageSize: Int
    ): Observable<Response<StoreItemResponse>>
}