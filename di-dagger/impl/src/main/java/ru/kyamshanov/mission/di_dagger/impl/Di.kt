package ru.kyamshanov.mission.di_dagger.impl

import ru.kyamshanov.mission.dagger.ComponentBuilder
import ru.kyamshanov.mission.dagger.CoreComponent
import kotlin.reflect.KClass

private const val CORE_COMPONENT_KEY = "core"

object Di {

    private val builders = mutableMapOf<KClass<*>, ComponentBuilder<*>>()
    private val componentsHolder = mutableMapOf<KClass<*>, MutableMap<Any, Any>>()

    @Suppress("UNCHECKED_CAST")
    inline fun <reified ComponentType : Any, ReturnType : ComponentType> getInternalComponent(holderId: Any? = null): ReturnType =
        getComponent(ComponentType::class, holderId) as ReturnType

    inline fun <reified T : Any> getComponent(holderId: Any? = null) =
        getComponent(T::class, holderId)

    inline fun <reified T : Any> releaseComponent(holderId: Any) =
        releaseComponent(T::class, holderId)

    inline fun <reified T : Any> registration(builder: ComponentBuilder<T>) =
        registration(T::class, builder)

    fun <T : Any> registration(clazz: KClass<T>, builder: ComponentBuilder<T>) {
        this.builders[clazz] = builder
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getBuilder(clazz: KClass<T>): ComponentBuilder<T>? =
        this.builders[clazz] as? ComponentBuilder<T>

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getComponent(clazz: KClass<T>, holderId: Any? = null): T? {
        var id = holderId

        if (id == null && CoreComponent::class.java.isAssignableFrom(clazz.java))
            id = CORE_COMPONENT_KEY

        return if (id != null) getSavableComponent(clazz, id)
        else builders[clazz]?.build() as? T
    }

    fun <T : Any> releaseComponent(clazz: KClass<T>, holderId: Any) {
        componentsHolder[clazz]?.let {
            it.remove(holderId)
            if (it.isEmpty()) componentsHolder.remove(clazz)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> getSavableComponent(clazz: KClass<T>, holderId: Any): T? {
        val holders = componentsHolder[clazz]
            ?: mutableMapOf<Any, Any>().also { componentsHolder[clazz] = it }
        return (holders[holderId]
            ?: builders[clazz]?.build()?.also { holders[holderId] = it }
            ?: releaseComponent(clazz, holderId)) as T?
    }
}