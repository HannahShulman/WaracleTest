package com.hanna.waracle.test.data.mapper

import com.google.common.truth.Truth.assertThat
import com.hanna.waracle.test.domain.models.dto.model.CakeItemDto
import org.junit.Test

class CakeMapperTest {

    private val mapper = CakeMapper()

    @Test
    fun `GIVEN a CakeMapper WHEN CakeItemDto is passed THEN mapped object has mapped title correctly`() {
        val cakeDto = CakeItemDto("cake dto title", "cake description", "image_url")
        val cakeItem = mapper.map(cakeDto)

        assertThat(cakeItem.title).contains("Cake dto title")
    }


    @Test
    fun `GIVEN a CakeMapper WHEN CakeItemDto is passed THEN mapped object has mapped description correctly`() {
        val cakeDto = CakeItemDto("cake dto title", "cake description", "image_url")
        val cakeItem = mapper.map(cakeDto)

        assertThat(cakeItem.description).contains("Cake description")
    }


    @Test
    fun `GIVEN a CakeMapper WHEN CakeItemDto is passed THEN mapped object has mapped url correctly`() {
        val cakeDto = CakeItemDto("cake dto title", "cake description", "image_url")
        val cakeItem = mapper.map(cakeDto)

        assertThat(cakeItem.url).contains("image_url")
    }
}