package ru.kyamshanov.mission.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.kyamshanov.mission.navigation_core.common.ComposableScreen
import ru.kyamshanov.mission.navigation_core.common.DestinationScreen
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)
        val screensProvider = ComposableScreensProvider()
        setContent {
            MissionTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    MissionNavigationHost(navController = navController, screensProvider = screensProvider)
                }
            }
        }
    }
}

@Composable
internal fun MissionNavigationHost(
    navController: NavHostController, screensProvider: ScreensProvider
) {
    val routeComposablePairs = screensProvider.supply().filterIsInstance<ComposableScreen>().map { screen ->
        val route = (screen as? DestinationScreen)?.destination ?: screen::class.qualifiedName.orEmpty()
        route to screen.composableSupplier
    }
    NavHost(
        navController = navController, startDestination = routeComposablePairs[0].first
    ) {
        routeComposablePairs.forEach { (route, composable) ->
            composable(route) { composable.invoke() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MissionTheme {}
}