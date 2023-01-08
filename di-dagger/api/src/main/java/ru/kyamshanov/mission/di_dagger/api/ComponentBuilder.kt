package ru.kyamshanov.mission.dagger

interface ComponentBuilder<T : Any> {

    fun build(): T
}