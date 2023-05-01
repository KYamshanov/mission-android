package ru.kyamshanov.mission.android

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.navigation_core.api.di.NavigationComponent
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent
import ru.kyamshanov.mission.session_front.api.session.UnidentifiedSession
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            window.statusBarColor = Color.Companion.Transparent.toArgb()
            MissionTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MissionTheme.colors.background) {
                    val navController = rememberNavController()
                    val screensProvider = ComposableScreensProvider()
                    Di.getInternalComponent<NavigationComponent, ru.kyamshanov.mission.navigation_core.impl.di.ModuleComponent>()
                        .navigatorControllerHolder.navigator = navController
                    MissionNavigationHost(navController = navController, screensProvider = screensProvider)
                }
            }
        }
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    val v = requireNotNull(Di.getComponent<SessionFrontComponent>()).sessionInfo.session
                    return if (v == UnidentifiedSession) false
                    else {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    }
                }
            }
        )
    }
}

@Composable
internal fun MissionNavigationHost(
    navController: NavHostController, screensProvider: ScreensProvider,
) {
    val navigationRouteFactory = NavigationRouteFactory()
    val screens = screensProvider.supply().toList()
    NavHost(
        navController = navController,
        startDestination = requireNotNull((screens.first { it is DestinationScreen } as? DestinationScreen)?.destination) { "Start destination has not founded" }
    ) {
        with(navigationRouteFactory) {
            screens.forEach { screen ->
                createComposable(screen)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MissionTheme {}
}