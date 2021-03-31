package com.sample.estore.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sample.estore.TestUtils
import com.sample.estore.data.api.StoreRemoteDataSource
import com.sample.estore.data.repository.StoreRepositoryImpl
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import java.lang.RuntimeException

class StoreRepositoryImplTest {
    companion object {
        private const val PAGE_ID = 1
        private const val PAGE_COUNT = 30
    }
    private lateinit var subject: StoreRepositoryImpl
    private lateinit var storeRemoteDataSource: StoreRemoteDataSource

    @Before
    fun setUp() {
        storeRemoteDataSource = mock()
        subject = StoreRepositoryImpl(storeRemoteDataSource)
    }

    @Test
    fun `Given the user scrolls the list, when the app requests data to load, then the repository fetches shopping list information`() {
        val storeData = TestUtils.createStoreData()
        val storeDataResponse = TestUtils.createStoreResponse()
        whenever(storeRemoteDataSource.getShoppingList(PAGE_ID, PAGE_COUNT)).thenReturn(
            Observable.just(
                storeDataResponse
            )
        )
        val result = subject.getStoreItems(PAGE_ID, PAGE_COUNT)
        verify(storeRemoteDataSource).getShoppingList(any(), any())
        val testSubscriber = result.test()
        testSubscriber.assertComplete()
        testSubscriber.assertResult(storeData)
    }

    @Test
    fun `Given the user scrolls the list, when the app requests data to load, then the repository throws error if the api returns error`() {
        val error = RuntimeException()
        whenever(storeRemoteDataSource.getShoppingList(any(), any())).thenReturn(
            Observable.error(error)
        )
        val result = subject.getStoreItems(PAGE_ID, PAGE_COUNT)
        val testSubscriber = result.test()
        verify(storeRemoteDataSource).getShoppingList(any(), any())
        testSubscriber.assertError(error)
    }
}