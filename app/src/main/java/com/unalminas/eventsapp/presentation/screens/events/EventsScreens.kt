package com.unalminas.eventsapp.presentation.screens.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.myComposables.InfoDialogContent
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

    var dialogState by rememberSaveable { mutableStateOf(false) }

    var currentEvent = Event() //remember

    Box(modifier = Modifier.fillMaxSize()) {
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
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            onClick = {
                navController.navigate(Screen.CreateEventScreen.route)
            },
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
    if (dialogState) {
        Dialog(
            onDismissRequest = { dialogState = false },
            content = {
                InfoDialogContent(
                    title = R.string.message_delete_event,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onDeleteClick = {
                        currentEvent.id.let { nonNullId ->
                            viewModel.deleteEventById(nonNullId)
                        }
                        dialogState = false
                    },
                    onCancel = { dialogState = false }
                )
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
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


