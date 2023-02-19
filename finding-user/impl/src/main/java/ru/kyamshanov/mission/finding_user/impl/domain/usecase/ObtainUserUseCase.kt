package ru.kyamshanov.mission.finding_user.impl.domain.usecase

import kotlinx.coroutines.flow.toCollection
import ru.kyamshanov.mission.finding_user.impl.domain.model.UserInfo
import ru.kyamshanov.mission.finding_user.impl.domain.repository.UserRepository
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.ObtainUserUseCase.SearchInfo
import javax.inject.Inject

internal interface ObtainUserUseCase {

    suspend fun get(searchInfo: SearchInfo): List<UserInfo>

    data class SearchInfo(
        val firstName: String,
        val lastName: String
    )
}

internal class ObtainUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : ObtainUserUseCase {

    override suspend fun get(searchInfo: SearchInfo): List<UserInfo> =
        userRepository.findByName(searchInfo.firstName, searchInfo.lastName).toCollection(mutableListOf())
}