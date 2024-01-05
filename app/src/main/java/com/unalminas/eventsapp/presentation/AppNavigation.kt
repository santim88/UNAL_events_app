package com.unalminas.eventsapp.presentation

import android.content.Intent
import android.view.LayoutInflater
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.unalminas.eventsapp.presentation.screens.camera.CameraXGuideTheme
import com.unalminas.eventsapp.presentation.screens.assistants.AssistantScreen
import com.unalminas.eventsapp.presentation.screens.assistants.adapter.FormAssistant
import com.unalminas.eventsapp.presentation.screens.calendar.CalendarScreen
import com.unalminas.eventsapp.presentation.screens.main.MainScreen
import com.unalminas.eventsapp.presentation.screens.events.FormEventScreen
import com.unalminas.eventsapp.presentation.screens.scanPdf417.MainScreenPdf417
import com.unalminas.eventsapp.presentation.screens.settings.SettingsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen(navController)
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
                eventId =  eventId
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
                eventId =  null
            )
        }

        composable(
            Screen.CreateAssistantCameraScreen("{id}").route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("id")?.toInt() ?: -1
            CameraXGuideTheme(
                navController = navController,
                eventId =  id
            )
        }

        composable(
            Screen.CreateAssistantPdf417("{id}").route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("id")?.toInt()
            MainScreenPdf417(
                navController = navController,
                eventId =  id
            )
        }

        composable(Screen.SettingsScreen.route) {
            SettingsScreen(navController)
        }

      /*  composable("calendar") {
            LaunchedEffect(Unit) {
                val intent = Intent(context, CalendarActivity::class.java)
                context.startActivity(intent)
            }
        }*/
        composable("calendar") {
            CalendarScreen(navController)
        }
    }
}


