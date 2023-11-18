package com.unalminas.eventsapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.unalminas.eventsapp.presentation.screens.AssistantScreen
import com.unalminas.eventsapp.presentation.screens.FormAssistant
import com.unalminas.eventsapp.presentation.screens.MainScreen
import com.unalminas.eventsapp.presentation.screens.FormEventScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController, startDestination = Screen.MainScreen.route
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen(navController)
        }

        composable(Screen.CreateEventScreen.route) {
            FormEventScreen(navController = navController, isNewEvent = true)
        }

        composable(Screen.AssistantForm.route) {
            FormAssistant(navController = navController, isNewAssistant = true)
        }

        composable(Screen.AssistantScreen.route) {
            AssistantScreen(navController)
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

        /*composable(
            Screen.EditAssistantScreen("{id}").route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("id")?.toInt()
            FormAssistant(
                navController = navController,
                id = id,
                isNewAssistant = false
            )
        }*/
    }
}
