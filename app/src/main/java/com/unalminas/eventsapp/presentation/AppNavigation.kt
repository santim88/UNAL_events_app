package com.unalminas.eventsapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.unalminas.eventsapp.presentation.myComposables.ScaffoldMainScreen
import com.unalminas.eventsapp.presentation.screens.assistants.AssistantScreen
import com.unalminas.eventsapp.presentation.screens.assistants.adapter.FormAssistant
import com.unalminas.eventsapp.presentation.screens.calendar.CalendarScreen
import com.unalminas.eventsapp.presentation.screens.camera.CameraXGuideTheme
import com.unalminas.eventsapp.presentation.screens.events.EventsScreens
import com.unalminas.eventsapp.presentation.screens.events.FormEventScreen
import com.unalminas.eventsapp.presentation.screens.scanPdf417.MainScreenPdf417
import com.unalminas.eventsapp.presentation.screens.settings.SettingsScreen
import kotlinx.coroutines.delay

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {

        composable(Screen.SplashScreen.route) {
            LaunchedEffect(Unit) {
                delay(100)
                navController.navigate(Screen.HomeScreen.EventsRoute.route)
            }
        }

        composable(Screen.CreateEventScreen.route) {
            FormEventScreen(navController = navController, isNewEvent = true)
        }

        composable(
            Screen.CreateAssistantScreen("{eventId}").route,
            arguments = listOf(navArgument("eventId") { type = NavType.StringType })
        ) { entry ->
            val eventId = entry.arguments?.getString("eventId")?.toInt()
            FormAssistant(
                navController = navController,
                id = null,
                isNewAssistant = true,
                eventId = eventId
            )
        }

        composable(
            Screen.AssistantScreen("{id}").route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("id")?.toInt()
            AssistantScreen(
                navController = navController,
                id = id
            )
        }

        composable(
            Screen.EditEventScreen("{id}").route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("id")?.toInt()
            FormEventScreen(
                navController = navController,
                id = id,
                isNewEvent = false
            )
        }

        composable(
            Screen.EditAssistantScreen("{id}").route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("id")?.toInt()
            FormAssistant(
                navController = navController,
                id = id,
                isNewAssistant = false,
                eventId = null
            )
        }

        composable(
            Screen.CreateAssistantCameraScreen("{id}").route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("id")?.toInt() ?: -1
            CameraXGuideTheme(
                navController = navController,
                eventId = id
            )
        }

        composable(
            Screen.CreateAssistantPdf417("{id}").route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("id")?.toInt()
            MainScreenPdf417(
                navController = navController,
                eventId = id
            )
        }

        composable(
            Screen.HomeScreen.TemplateRoute.route,
            arguments = listOf(navArgument("sub_section") { type = NavType.StringType })
        ) {
            val bottomNavController = rememberNavController()
            HomeMainScreen(
                navController,
                bottomNavController
            )
        }
    }
}

@Composable
fun HomeMainScreen(
    navController: NavHostController,
    navBottomController: NavHostController
) {
    var showFloatingButton by remember { mutableStateOf(false) }

    ScaffoldMainScreen(
        navController = navController,
        allowsItemBar = 1,
        showFloatingButton = showFloatingButton,
        onNavSectionSelected = { index, bottomNavigationItem ->
            navBottomController.navigate(bottomNavigationItem.route)
        }
    ) {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navBottomController,
            startDestination = Screen.HomeScreen.EventsRoute.route
        ) {
            composable(Screen.HomeScreen.EventsRoute.route) {
                showFloatingButton = true
                EventsScreens(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController
                )
            }

            composable(Screen.HomeScreen.SettingsScreen.route) {
                showFloatingButton = false
                SettingsScreen(
                    modifier = Modifier
                        .fillMaxSize(),
                    navController = navController
                )
            }

            composable(Screen.HomeScreen.CalendarScreen.route) {
                showFloatingButton = false
                CalendarScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
