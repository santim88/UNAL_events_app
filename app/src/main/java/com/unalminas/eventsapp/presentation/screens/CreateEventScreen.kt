package com.unalminas.eventsapp.presentation.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.presentation.ui.FormEvent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEventScreen(
    navController: NavHostController,
) {

    val eventState = Event(name = "", description = "", date = "", hour = "", place = "")

    FormEvent(navController = navController, eventState= eventState, isNewEvent = true)
}
