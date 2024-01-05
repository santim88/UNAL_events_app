package com.unalminas.eventsapp.presentation.screens.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.myComposables.InfoDialogContent
import com.unalminas.eventsapp.presentation.myComposables.ScaffoldBarUse
import com.unalminas.eventsapp.presentation.screens.events.adapter.CardEvent
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun EventsScreens(
    navController: NavHostController,
    eventViewModel: EventsViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        eventViewModel.getEventList()
    }

    EventScreenContent(navController, eventViewModel)
}

@Composable
fun EventScreenContent(
    navController: NavHostController,
    viewModel: EventsViewModel = hiltViewModel()
) {

    val eventListState by viewModel.eventListState.collectAsState(emptyList())

    ScaffoldBarUse(navController = navController, isEventList = true, allowsItemBar = 1) {
        EventScreenContentSimplify(navController, eventListState, paddingValues = it)
    }
}


@Composable
fun EventScreenContentSimplify(
    navController: NavHostController,
    eventListState: List<Event>,
    viewModel: EventsViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
) {
    var dialogState by rememberSaveable { mutableStateOf(false) }
    var currentEvent = Event()

    Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
        EventsList(
            onSwipe = { event ->
                currentEvent = event
                dialogState = true
            },
            onEdit = { event ->
                val screen = Screen.EditEventScreen(event.id.toString())
                navController.navigate(screen.createRoute())
            },
            eventListState = eventListState,
            onEventClicked = { event ->
                val screen = Screen.AssistantScreen(event.id.toString())
                navController.navigate(screen.createRoute())
            }
        )
    }
    if (dialogState) {
        InfoDialogContent(
            R.string.message_delete_event,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(15.dp),
            onDeleteClick = { viewModel.deleteEventById(currentEvent.id) },
            onCancel = { dialogState = false }
        )
    }
}

@Composable
fun EventsList(
    onSwipe: (Event) -> Unit,
    onEdit: (Event) -> Unit,
    onEventClicked: (Event) -> Unit,
    eventListState: List<Event>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        items(items = eventListState) { event ->
            val delete = SwipeAction(
                onSwipe = { onSwipe(event) },
                icon = {
                    Icon(
                        modifier = Modifier.padding(16.dp),
                        painter = painterResource(id = R.drawable.baseline_delete_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                background = Color.LightGray
            )

            SwipeableActionsBox(
                swipeThreshold = 85.dp,
                endActions = listOf(delete)
            ) {
                CardEvent(
                    event = event,
                    changeScreen = { onEventClicked(event) },
                    editEvent = { onEdit(event) }
                )
            }
        }
    }
}


