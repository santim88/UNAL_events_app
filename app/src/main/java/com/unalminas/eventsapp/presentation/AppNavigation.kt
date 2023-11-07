package com.unalminas.eventsapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.unalminas.eventsapp.presentation.screens.EditEventScreen
import com.unalminas.eventsapp.presentation.screens.ScreenC
import com.unalminas.eventsapp.presentation.screens.ScreenEdit
import com.unalminas.eventsapp.presentation.screens.MainScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController, startDestination = "MainScreen"
    ) {
        composable("MainScreen") {
            MainScreen(navController)
        }

        composable("CreateEventScreen") {
            EditEventScreen(navController, isNewEvent = true)
        }

        composable("C") {
            ScreenC(navController)
        }

        composable("Edit/{id}", arguments = listOf(navArgument("id") { type = NavType.StringType })) { entry ->
            ScreenEdit(navController, id = entry.arguments?.getString("id"))
        }
    }
}
