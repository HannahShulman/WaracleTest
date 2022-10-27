package com.hanna.waracle.test.data.repository

import com.hanna.waracle.test.data.api.ResponseWrapper
import com.hanna.waracle.test.domain.models.entities.CakeItem

interface CakesRepository {

    suspend fun getCakes(): ResponseWrapper<List<CakeItem>>
}