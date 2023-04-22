package ru.kyamshanov.mission.ui_core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Surface(
    modifier: Modifier = Modifier,
    topContent: @Composable (() -> Unit)? = null,
    bottomContent: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit),
) = Column(modifier = modifier) {
    topContent?.invoke()
    content()
    Spacer(modifier = Modifier.weight(1f))
    bottomContent?.invoke()
}