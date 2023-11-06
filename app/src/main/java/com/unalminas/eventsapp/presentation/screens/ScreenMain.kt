package com.unalminas.eventsapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.data.EventDataSource
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.presentation.ScreenMainViewModel
import com.unalminas.eventsapp.presentation.ui.CardEvent

@Composable
fun ScreenMain(
    eventDataSource: EventDataSource,
    navController: NavHostController,
    evenViewModel: ScreenMainViewModel = hiltViewModel()
) {
    val eventListState by evenViewModel.eventListState.collectAsState()

    LaunchedEffect(Unit) {
        evenViewModel.getEventList()
    }

    MainActivityContent(eventListState, eventDataSource, navController, evenViewModel)
}

@Composable
fun MainActivityContent(
    data: List<Event>,
    eventDataSource: EventDataSource,
    navController: NavHostController,
    evenViewModel: ScreenMainViewModel = hiltViewModel()
) {
    val eventListState by evenViewModel.eventListState.collectAsState(emptyList())

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(items = eventListState) { event ->
                CardEvent(
                    event = event,
                    changeScreen = {
                        navController.navigate("C")
                    }, editEvent = {
                        navController.navigate("Edit/${event.id}")
                    }, deleteEvent = {
                        evenViewModel.deleteEvent(event)
                    })
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            onClick = {
                navController.navigate("B") {
                    popUpTo("B") { inclusive = true }
                }
            },
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
}