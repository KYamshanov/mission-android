package ru.kyamshanov.mission.core.common

interface Converter<Source : Any?, Target : Any?> {

    operator fun invoke(source: Source): Target
}