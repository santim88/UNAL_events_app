package com.example.pokedex.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedex.data.EventDataSourceImpl
import com.example.pokedex.presentation.screens.ScreenB
import com.example.pokedex.presentation.screens.ScreenC
import com.example.pokedex.presentation.screens.ScreenEdit
import com.example.pokedex.presentation.screens.ScreenMain

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
