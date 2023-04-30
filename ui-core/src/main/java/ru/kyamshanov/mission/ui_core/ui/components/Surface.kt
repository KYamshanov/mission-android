package ru.kyamshanov.mission.ui_core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Surface(
    modifier: Modifier = Modifier,
    topContent: @Composable (() -> Unit)? = null,
    bottomContent: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit),
) = Column {
    topContent?.invoke()
    Column(modifier = modifier) {
        content()
        Spacer(modifier = Modifier.weight(1f))
        Box(modifier = Modifier.navigationBarsPadding()) {
            bottomContent?.invoke()
        }
    }
}