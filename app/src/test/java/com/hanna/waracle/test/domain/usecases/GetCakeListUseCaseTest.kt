package com.hanna.waracle.test.domain.usecases

import com.hanna.waracle.test.BaseTest
import com.hanna.waracle.test.data.repository.CakesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock

class GetCakeListUseCaseTest : BaseTest() {

    private val cakeRepository: CakesRepository = mock()
    private lateinit var getCakeListUseCase: GetCakeListUseCase

    @Before
    fun setUp() {
        getCakeListUseCase = GetCakeListUseCase(cakeRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when GetCakeListUseCase is invoked, repository calls getCakes`() =
        runSuspendTest {
            getCakeListUseCase.invoke()
            verify(cakeRepository).getCakes()
        }
}