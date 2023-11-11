package com.unalminas.eventsapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.unalminas.eventsapp.presentation.screens.CreateEventScreen
import com.unalminas.eventsapp.presentation.screens.EditEventScreen
import com.unalminas.eventsapp.presentation.screens.ScreenC
import com.unalminas.eventsapp.presentation.screens.MainScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController, startDestination = "MainScreen"
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen(navController)
        }

        composable(Screen.CreateEventScreen.route) {
            CreateEventScreen(navController)
        }

        composable(Screen.ScreenC.route) {
            ScreenC(navController)
        }

        composable(
            Screen.EditEventScreen("{id}").route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            EditEventScreen(navController, id = entry.arguments?.getString("id"))
        }
    }
}
