package com.sample.estore.di

import com.google.gson.GsonBuilder
import com.sample.estore.BuildConfig
import com.sample.estore.data.StoreApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    @Singleton
    @Provides
    fun provideStoreApiClient(retrofitBuilder: Retrofit.Builder): StoreApiService {
        return retrofitBuilder.build().create(StoreApiService::class.java)
    }
}