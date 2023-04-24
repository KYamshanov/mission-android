package ru.kyamshanov.mission.ui_core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun Surface(
    modifier: Modifier = Modifier,
    topContent: @Composable (() -> Unit)? = null,
    bottomContent: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit),
) = Column {
    topContent?.invoke()

  /*  when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> if(topContent==null ) WindowInsets.statusBars.asPaddingValues() else PaddingValues.Absolute()
        else -> WindowInsets.systemBars.asPaddingValues()
    }
*/
    Column(modifier = modifier.systemBarsPadding()) {
        content()
        Spacer(modifier = Modifier.weight(1f))
        bottomContent?.invoke()
    }
}