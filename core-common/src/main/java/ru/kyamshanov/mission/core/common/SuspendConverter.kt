package ru.kyamshanov.mission.core.common

interface SuspendConverter<Source : Any?, Target : Any?> {

    suspend operator fun invoke(source: Source): Target
}