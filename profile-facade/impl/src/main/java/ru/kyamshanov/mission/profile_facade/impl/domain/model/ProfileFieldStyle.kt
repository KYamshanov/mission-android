package ru.kyamshanov.mission.profile_facade.impl.domain.model

import kotlin.reflect.KClass

internal class ProfileFieldStyle(
    val key: String,
    val type: KClass<*>,
)