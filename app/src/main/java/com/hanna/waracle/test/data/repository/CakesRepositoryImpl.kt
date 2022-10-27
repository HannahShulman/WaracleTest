package com.hanna.waracle.test.data.repository

import com.hanna.waracle.test.data.api.CakesApi
import com.hanna.waracle.test.data.api.ResponseWrapper
import com.hanna.waracle.test.data.mapper.CakeMapper
import com.hanna.waracle.test.data.network.NetworkResponse
import com.hanna.waracle.test.domain.models.entities.CakeItem
import javax.inject.Inject

class CakesRepositoryImpl @Inject constructor(
    private val cakesApi: CakesApi,
    private val cakeMapper: CakeMapper,
) : CakesRepository {

    override suspend fun getCakes(): ResponseWrapper<List<CakeItem>> {
        return when (val response = cakesApi.getCakes()) {
            is NetworkResponse.Success -> {
                val mappedItems = response.body.map { cakeMapper.map(it) }
                ResponseWrapper.Success(mappedItems)
            }
            else -> {
                ResponseWrapper.Error((response as NetworkResponse.Error).error)
            }
        }
    }
}
