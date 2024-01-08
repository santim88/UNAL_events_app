package com.unalminas.eventsapp.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.presentation.screens.events.EventsScreens

@Composable
fun MainScreen(
    navController: NavHostController,
) {
    EventsScreens(navController)
}
