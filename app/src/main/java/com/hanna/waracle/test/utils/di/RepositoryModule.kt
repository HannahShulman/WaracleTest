package com.hanna.waracle.test.utils.di

import com.hanna.waracle.test.data.api.CakesApi
import com.hanna.waracle.test.data.mapper.CakeMapper
import com.hanna.waracle.test.data.repository.CakesRepository
import com.hanna.waracle.test.data.repository.CakesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun provideCakesRepository(
        cakesApi: CakesApi,
        cakeMapper: CakeMapper
    ): CakesRepository {
        return CakesRepositoryImpl(
            cakesApi,
            cakeMapper
        )
    }
}