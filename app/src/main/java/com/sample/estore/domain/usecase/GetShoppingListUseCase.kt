package com.sample.estore.domain.usecase

import com.sample.estore.data.repository.StoreRepository
import com.sample.estore.domain.model.StoreItem
import io.reactivex.Observable
import javax.inject.Inject

class GetShoppingListUseCase @Inject constructor(private val storeRepository: StoreRepository) :
    BaseUseCase<PagingData, Observable<List<StoreItem>>> {
    override fun execute(params: PagingData): Observable<List<StoreItem>> {
        return storeRepository.getStoreItems(params.page, params.itemsCount)
    }
}

data class PagingData(val page: Int, val itemsCount: Int)