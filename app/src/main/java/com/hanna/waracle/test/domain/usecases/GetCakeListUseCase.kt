package com.hanna.waracle.test.domain.usecases

import com.hanna.waracle.test.data.api.ResponseWrapper
import com.hanna.waracle.test.data.repository.CakesRepository
import com.hanna.waracle.test.domain.models.entities.CakeItem
import javax.inject.Inject

class GetCakeListUseCase @Inject constructor(private val cakesRepository: CakesRepository) {

    suspend operator fun invoke(): ResponseWrapper<List<CakeItem>> {
        return cakesRepository.getCakes()
    }
}