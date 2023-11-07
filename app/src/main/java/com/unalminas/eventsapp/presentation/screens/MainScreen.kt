package com.unalminas.eventsapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.presentation.ui.CardEvent

@Composable
fun MainScreen(
    navController: NavHostController,
    eventViewModel: MainViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        eventViewModel.getEventList()
    }

    MainActivityContent(navController, eventViewModel)
}

@Composable
fun MainActivityContent(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val eventListState by viewModel.eventListState.collectAsState(emptyList())

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(vertical = 10.dp)
        ) {
            items(items = eventListState) { event ->
                CardEvent(
                    event = event,
                    changeScreen = {
                        navController.navigate("C")
                    }, editEvent = {
                        navController.navigate("Edit/${event.id}")
                    }, deleteEvent = {
                        viewModel.deleteEvent(event)
                    }
                )
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            onClick = {
                navController.navigate("CreateEventScreen") {
                    popUpTo("CreateEventScreen") { inclusive = true }
                }
            },
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
}
