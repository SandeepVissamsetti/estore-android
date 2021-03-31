package com.sample.estore.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.sample.estore.data.repository.StoreRepository
import com.sample.estore.domain.usecase.GetShoppingListUseCase
import com.sample.estore.domain.usecase.PagingData
import org.junit.Before
import org.junit.Test

class GetShoppingListUseCaseTest {
    companion object {
        private const val PAGE_ID = 1
        private const val PAGE_COUNT = 30
    }

    private lateinit var subject: GetShoppingListUseCase
    private lateinit var storeRepository: StoreRepository

    @Before
    fun setUp() {
        storeRepository = mock()
        subject = GetShoppingListUseCase(storeRepository)
    }

    @Test
    fun `Given the user opens the app, when the activity gets created, then the usecase gets the shopping list`() {
        subject.execute(PagingData(PAGE_ID, PAGE_COUNT))
        verify(storeRepository).getStoreItems(PAGE_ID, PAGE_COUNT)
    }
}