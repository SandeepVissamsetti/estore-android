package com.sample.estore.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sample.estore.TestUtils
import com.sample.estore.data.api.StoreApiClient
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.lang.RuntimeException

class StoreRemoteDataSourceTest {

    companion object {
        private const val PAGE_ID = 1
        private const val PAGE_COUNT = 30
    }

    private lateinit var subject: StoreApiClient
    private lateinit var storeApiService: StoreApiService

    @Before
    fun setUp() {
        storeApiService = mock()
        subject = StoreApiClient(storeApiService, Schedulers.trampoline())
    }

    @Test
    fun `Given the user scrolls the list, when the repository requests data to load, then the api service fetches the data`() {
        val storeDataResponse = TestUtils.createStoreResponse()
        whenever(storeApiService.getShoppingList(PAGE_ID, PAGE_COUNT)).thenReturn(
            Observable.just(
                Response.success(storeDataResponse)
            )
        )
        val result = subject.getShoppingList(
            PAGE_ID,
            PAGE_COUNT
        )
        verify(storeApiService).getShoppingList(any(), any())
        val testSubscriber = result.test()
        testSubscriber.assertComplete()
        testSubscriber.assertResult(storeDataResponse)
    }

    @Test
    fun `Given the user scrolls the list, when the repository requests data to load, then the api service throws error if the api returns error`() {
        val error = RuntimeException()
        whenever(storeApiService.getShoppingList(any(), any())).thenReturn(
            Observable.error(error)
        )
        val result = subject.getShoppingList(
            PAGE_ID,
            PAGE_COUNT
        )
        val testSubscriber = result.test()
        verify(storeApiService).getShoppingList(any(), any())
        testSubscriber.assertError(error)
    }

}