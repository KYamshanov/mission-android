package ru.kyamshanov.mission.base_core.api

interface MissionPreferences {

    fun saveValue(key: String, value: String)

    fun getValue(key: String): String?

    fun remove(key: String)
}