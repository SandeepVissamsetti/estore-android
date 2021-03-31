package com.sample.estore.di

import com.sample.estore.data.api.StoreApiClient
import com.sample.estore.data.repository.StoreRepository
import com.sample.estore.data.repository.StoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideShoppingRepository(
        apiClient: StoreApiClient
    ): StoreRepository = StoreRepositoryImpl(apiClient)
}