package ru.kyamshanov.mission.base_core.impl.ui

import ru.kyamshanov.mission.base_core.api.Device
import javax.inject.Inject

internal class DeviceImpl @Inject constructor() : Device {

    override val info: Map<String, Any>
        get() = mutableMapOf("front-type" to "android", "fingerprint" to "unknown")
}