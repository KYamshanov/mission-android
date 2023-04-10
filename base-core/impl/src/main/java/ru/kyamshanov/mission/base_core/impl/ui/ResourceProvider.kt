package ru.kyamshanov.mission.base_core.impl.ui

import android.content.Context
import java.lang.ref.WeakReference
import javax.inject.Inject
import ru.kyamshanov.mission.base_core.api.ResourcesProvider

internal class ResourcesProviderImpl @Inject constructor(
    context: Context,
) : ResourcesProvider {

    private val weakContext = WeakReference(context)

    override fun getString(id: Int): String = weakContext.get()?.run {
        getString(id)
    } ?: throw IllegalStateException("Application context is released")

    override fun getString(id: Int, vararg formatArgs: Any): String = weakContext.get()?.run {
        getString(id, *formatArgs)
    } ?: throw IllegalStateException("Application context is released")

    override fun getQuantityString(id: Int, count: Int): String = weakContext.get()?.resources?.run {
        getQuantityString(id, count)
    } ?: throw IllegalStateException("Application context is released")

    override fun getQuantityString(id: Int, count: Int, vararg formatArgs: Any): String =
        weakContext.get()?.resources?.run {
            getQuantityString(id, count, *formatArgs)
        } ?: throw IllegalStateException("Application context is released")
}