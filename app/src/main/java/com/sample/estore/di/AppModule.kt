package com.sample.estore.di

import com.google.gson.Gson
import com.sample.estore.ui.providers.ImageResourceLoader
import com.sample.estore.ui.providers.ImageResourceLoaderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @BackgroundScheduler
    @Provides
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @MainScheduler
    @Provides
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun bindImageLoader(imageResourceLoaderImpl: ImageResourceLoaderImpl): ImageResourceLoader =
        imageResourceLoaderImpl
}

