package com.hanna.waracle.test.data.mapper

import com.hanna.waracle.test.domain.models.dto.model.CakeItemDto
import com.hanna.waracle.test.domain.models.entities.CakeItem
import javax.inject.Inject

class CakeMapper @Inject constructor() {
    fun map(cakeItemDto: CakeItemDto): CakeItem {
        return CakeItem(
            title = cakeItemDto.title.replaceFirstChar { it.uppercaseChar() },
            description = cakeItemDto.desc.replaceFirstChar { it.uppercaseChar() },
            url = cakeItemDto.image
        )
    }
}