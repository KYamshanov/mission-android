package ru.kyamshanov.mission.navigation_core.api

interface ResultProvider {

    fun <T : Any?> get(key: String, defaultValue: T): T
}