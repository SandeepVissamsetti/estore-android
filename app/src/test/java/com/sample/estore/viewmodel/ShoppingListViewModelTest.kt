package com.sample.estore.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.sample.estore.TestUtils
import com.sample.estore.domain.usecase.GetShoppingListUseCase
import com.sample.estore.ui.shoppinglist.ShoppingListViewModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import com.nhaarman.mockitokotlin2.verify

class ShoppingListViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var subject: ShoppingListViewModel
    private lateinit var getShoppingListUseCase: GetShoppingListUseCase

    @Before
    fun setUp() {
        getShoppingListUseCase = mock()
        subject = ShoppingListViewModel(
            getShoppingListUseCase,
            Schedulers.trampoline()
        )
    }

    @Test
    fun `Given the user loads the app, when the user scrolls, then the viewmodel fetches shopping information and returns if result found`() {
        val storeData = TestUtils.createStoreData()
        whenever(getShoppingListUseCase.execute(any())).thenReturn(Observable.just(storeData))
        subject.fetchShoppingList(TestUtils.PAGE_ID, TestUtils.PAGE_COUNT)
        verify(getShoppingListUseCase).execute(any())
    }
}