package com.sample.estore.ui.shoppinglist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.estore.di.MainScheduler
import com.sample.estore.domain.model.StoreItem
import com.sample.estore.domain.usecase.GetShoppingListUseCase
import com.sample.estore.domain.usecase.PagingData
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class ShoppingListViewModel @ViewModelInject constructor(
    private val getShoppingListUseCase: GetShoppingListUseCase,
    @MainScheduler private val mainScheduler: Scheduler
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val storeItemListMutable: MutableLiveData<List<StoreItem>> = MutableLiveData()
    val storeData: LiveData<List<StoreItem>>
        get() = storeItemListMutable

    fun fetchShoppingList(page: Int, totalItemsCount: Int) {
        compositeDisposable.add(
            getShoppingListUseCase.execute(PagingData(page, totalItemsCount))
                .observeOn(mainScheduler)
                .subscribe({
                    storeItemListMutable.postValue(it)
                }, {
                    val x = it
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}