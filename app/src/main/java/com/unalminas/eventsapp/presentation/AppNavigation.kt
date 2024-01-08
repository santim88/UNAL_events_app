package com.unalminas.eventsapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.unalminas.eventsapp.presentation.myComposables.ScaffoldBarUse
import com.unalminas.eventsapp.presentation.screens.assistants.AssistantScreen
import com.unalminas.eventsapp.presentation.screens.assistants.adapter.FormAssistant
import com.unalminas.eventsapp.presentation.screens.calendar.CalendarScreen
import com.unalminas.eventsapp.presentation.screens.camera.CameraXGuideTheme
import com.unalminas.eventsapp.presentation.screens.events.FormEventScreen
import com.unalminas.eventsapp.presentation.screens.main.MainScreen
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
                navController.navigate(Screen.HomeScreen.MainScreen.route)
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
            val subSection = it.arguments?.getString("sub_section")

            val subRoute = subSection?.let {
                Screen.HomeScreen.TemplateRoute.route.replace("{sub_section}", subSection)
            } ?: Screen.HomeScreen.MainScreen.route

            HomeMainScreen(subRoute)
        }
    }
}

@Composable
fun HomeMainScreen(
    subSection: String = Screen.HomeScreen.MainScreen.route
) {
    val navBottomController = rememberNavController()

    LaunchedEffect(subSection) {
        delay(100)
        navBottomController.navigate(subSection)
    }

    ScaffoldBarUse(
        navController = navBottomController,
        allowsItemBar = 1,
        onNavSectionSelected = { index, bottomNavigationItem ->
            navBottomController.navigate(bottomNavigationItem.route)
        }
    ) {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navBottomController,
            startDestination = Screen.HomeScreen.MainScreen.route
        ) {
            composable(Screen.HomeScreen.MainScreen.route) {
                MainScreen(navBottomController)
            }

            composable(Screen.HomeScreen.SettingsScreen.route) {
                SettingsScreen(navBottomController)
            }

            composable(Screen.HomeScreen.CalendarScreen.route) {
                CalendarScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
        }
    }
}
