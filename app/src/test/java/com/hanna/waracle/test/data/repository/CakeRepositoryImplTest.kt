package com.hanna.waracle.test.data.repository

import com.google.common.truth.Truth.assertThat
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.hanna.waracle.test.BaseTest
import com.hanna.waracle.test.data.api.CakesApi
import com.hanna.waracle.test.data.api.ResponseWrapper
import com.hanna.waracle.test.data.mapper.CakeMapper
import com.hanna.waracle.test.data.network.NetworkResponse
import com.hanna.waracle.test.domain.models.dto.model.CakeItemDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

//TODO("Each assert/verification, should be separated into a separate test")
@OptIn(ExperimentalCoroutinesApi::class)
class CakeRepositoryImplTest : BaseTest() {

    private val cakeApi = mock<CakesApi>()
    private val cakeMapper = spy<CakeMapper>()
    private lateinit var cakesRepository: CakesRepository

    @Before
    fun setUp() {
        cakesRepository = CakesRepositoryImpl(cakeApi, cakeMapper)
    }

    @Test
    fun `positive get cakes summary`() = runSuspendTest {
        val cakesBodyResponse = loadCakes()
        val cakeResponse = NetworkResponse.Success(cakesBodyResponse)
        whenever(cakeApi.getCakes()).thenReturn(cakeResponse)

        val response = cakesRepository.getCakes()

        verify(cakeMapper, times(cakesBodyResponse.size)).map(any())
        assertThat(response).isInstanceOf(ResponseWrapper.Success::class.java)
    }

    @Test
    fun `negative get cakes summary`() = runSuspendTest {
        val fetchCakesResponse = NetworkResponse.Error(RuntimeException("Failed!!"))
        whenever(cakeApi.getCakes()).thenReturn(fetchCakesResponse)

        val response = cakesRepository.getCakes()

        verify(cakeMapper, never()).map(any())
        assertThat(response).isInstanceOf(ResponseWrapper.Error::class.java)
    }

    private fun loadCakes(): ArrayList<CakeItemDto> {
        val gson = GsonBuilder().create()
        val dataResponseString = javaClass.classLoader?.getResource("data.json")?.readText()
        return gson.fromJson(
            dataResponseString,
            object : TypeToken<ArrayList<CakeItemDto>>() {}.type
        )
    }
}