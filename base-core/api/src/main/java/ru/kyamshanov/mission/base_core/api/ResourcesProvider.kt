package ru.kyamshanov.mission.base_core.api

interface ResourcesProvider {

    fun getString(id: Int): String

    fun getString(id: Int, vararg formatArgs: Any): String
}