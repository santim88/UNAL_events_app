package com.unalminas.eventsapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.unalminas.eventsapp.data.EventDataSourceImpl
import com.unalminas.eventsapp.presentation.screens.ScreenB
import com.unalminas.eventsapp.presentation.screens.ScreenC
import com.unalminas.eventsapp.presentation.screens.ScreenEdit
import com.unalminas.eventsapp.presentation.screens.ScreenMain

@Composable
fun Nav() {
    val context = LocalContext.current
    val eventDataSource = EventDataSourceImpl(context)
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController, startDestination = "A"
    ) {
        composable("A") {
            ScreenMain(eventDataSource, navController)
        }
        composable("B") {
            ScreenB(navController)
        }
        composable("C") {
            ScreenC(navController)
        }

        composable("Edit/{id}", arguments = listOf(navArgument("id") { type = NavType.StringType })) { entry ->
            ScreenEdit(navController, id = entry.arguments?.getString("id"))
        }

    }
}
