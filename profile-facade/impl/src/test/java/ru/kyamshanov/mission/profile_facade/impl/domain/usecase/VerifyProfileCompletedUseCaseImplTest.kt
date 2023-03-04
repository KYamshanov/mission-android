package ru.kyamshanov.mission.profile_facade.impl.domain.usecase

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.kyamshanov.mission.profile_facade.impl.domain.model.ProfileFieldStyle
import ru.kyamshanov.mission.profile_facade.impl.domain.model.ProfileInfoMap

internal class VerifyProfileCompletedUseCaseImplTest {

    private lateinit var useCase: VerifyProfileCompletedUseCaseImpl

    @Before
    fun setUp() {
        useCase = VerifyProfileCompletedUseCaseImpl(neededFields)
    }

    @Test
    fun verify() {
        val requiredFields = useCase.verify(
            ProfileInfoMap(
                userId = "", info = mapOf()
            )
        )
        Assert.assertEquals(2, requiredFields.size)
    }

    @Test
    fun `verify profile with age`() {
        val requiredFields = useCase.verify(
            ProfileInfoMap(
                userId = "", info = mapOf("Age" to 4)
            )
        )
        Assert.assertEquals(1, requiredFields.size)
    }

    @Test
    fun `verify profile with age and name`() {
        val requiredFields = useCase.verify(
            ProfileInfoMap(
                userId = "", info = mapOf("Age" to 4, "Name" to "name")
            )
        )
        println(requiredFields.map { it.type }.joinToString(", "))
        Assert.assertEquals(0, requiredFields.size)
    }

    private companion object {

        val neededFields: List<ProfileFieldStyle> = listOf(
            ProfileFieldStyle("Age", Number::class),
            ProfileFieldStyle("Name", String::class)
        )
    }
}