package com.hanna.waracle.test.utils.di

import com.hanna.waracle.test.utils.ui.GlideHelper
import com.hanna.waracle.test.utils.ui.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ImageModule {

    @Provides
    @Singleton
    fun provideImageLoader(): ImageLoader {
        return GlideHelper()
    }
}