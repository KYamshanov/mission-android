package ru.kyamshanov.mission.ui_core.ui.utils

class ComposableFunc {
}

inline fun compose(content : ()->Unit) { content.invoke() }