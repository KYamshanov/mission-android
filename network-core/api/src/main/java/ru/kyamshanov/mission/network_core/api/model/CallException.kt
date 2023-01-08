package ru.kyamshanov.mission.network_core.api.model

data class CallException(
    val status: Int,
    override val message: String? = null
) : Exception()