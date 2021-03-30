package com.sample.estore.ui.shoppinglist

import androidx.hilt.lifecycle.ViewModelInject
import com.sample.estore.di.MainScheduler
import com.sample.estore.domain.usecase.GetShoppingListUseCase
import io.reactivex.Scheduler

class ShoppingListViewModel @ViewModelInject constructor(
    private val getShoppingListUseCase: GetShoppingListUseCase,
    @MainScheduler private val mainScheduler: Scheduler
) {
}