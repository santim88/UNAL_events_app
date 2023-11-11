package com.unalminas.eventsapp.presentation.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.presentation.ui.FormEvent
import com.unalminas.eventsapp.presentation.ui.LoadingSpinner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditEventScreen(
    navController: NavHostController,
    id: String?,
    viewModel: EditEventViewModel = hiltViewModel(),
) {
    LaunchedEffect(true) {
        viewModel.getEventById(id?.toInt())
    }

    val eventState by viewModel.eventState.collectAsState()

    when (eventState) {
        null -> { LoadingSpinner()}
        else -> {
            FormEvent(navController = navController, eventState= eventState, isNewEvent = false)
        }
    }
}
