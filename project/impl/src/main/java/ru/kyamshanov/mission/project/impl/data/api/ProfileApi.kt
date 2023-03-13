package ru.kyamshanov.mission.project.impl.data.api

import ru.kyamshanov.mission.project.impl.data.model.MappingRqDto
import ru.kyamshanov.mission.project.impl.data.model.MappingRsDto

internal interface ProfileApi {

    suspend fun mappingUsers(body: MappingRqDto): MappingRsDto
}