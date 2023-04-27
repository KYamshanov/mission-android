package ru.kyamshanov.mission.finding_user.impl.domain.usecase

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.toCollection
import ru.kyamshanov.mission.finding_user.impl.domain.model.InternalSearchStrategy
import ru.kyamshanov.mission.finding_user.impl.domain.model.UserInfo
import ru.kyamshanov.mission.finding_user.impl.domain.repository.UserRepository
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.ObtainUserUseCase.SearchInfo

internal interface ObtainUserUseCase {

    suspend fun get(searchInfo: SearchInfo): Result<List<UserInfo>>

    data class SearchInfo(
        val name: String,
    )
}

internal class ObtainUserUseCaseImpl @AssistedInject constructor(
    @Assisted private val searchStrategy: InternalSearchStrategy,
    private val userRepository: UserRepository,
) : ObtainUserUseCase {

    override suspend fun get(searchInfo: SearchInfo): Result<List<UserInfo>> = runCatching {
        userRepository.findByName(searchInfo.name).toCollection(mutableListOf())
    }

    @AssistedFactory
    internal interface Factory : ObtainUserUseCaseFactory {

        override fun create(searchStrategy: InternalSearchStrategy): ObtainUserUseCaseImpl
    }
}