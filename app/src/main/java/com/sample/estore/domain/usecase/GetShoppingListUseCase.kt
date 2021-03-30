package com.sample.estore.domain.usecase

import com.sample.estore.data.repository.StoreRepository
import com.sample.estore.domain.model.StoreItem
import io.reactivex.Observable
import javax.inject.Inject

class GetShoppingListUseCase @Inject constructor(private val storeRepository: StoreRepository) :
    BaseUseCase<Nothing, Observable<List<StoreItem>>> {
    override fun execute(params: Nothing): Observable<List<StoreItem>> {
       return storeRepository.getStoreItems(1,30)
    }
}