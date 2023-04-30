package ru.kyamshanov.mission.navigation_core.api

interface ResultProvider {

    fun <T : Any?> get(key: String, defaultValue: T): T

    suspend fun <T : Any?> awaitResult(key: String, defaultValue: T): T

    fun notify(key: String)
}