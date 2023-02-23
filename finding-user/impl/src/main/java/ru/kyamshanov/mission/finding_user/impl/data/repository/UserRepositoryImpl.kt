package ru.kyamshanov.mission.finding_user.impl.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.kyamshanov.mission.finding_user.impl.domain.model.UserInfo
import ru.kyamshanov.mission.finding_user.impl.domain.repository.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(

) : UserRepository {

    override fun findByName(firstName: String, secondName: String): Flow<UserInfo> = flowOf(
        UserInfo(firstName = "Константи", lastName = "Ямшанов", patronymic = "Алексеевич"),
        UserInfo(firstName = "Ваилий", lastName = "Васильевич", patronymic = "Петрович"),
        UserInfo(firstName = firstName, lastName = secondName, patronymic = "Петрович")
    )
}