package com.unalminas.eventsapp.presentation.screens.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
import com.unalminas.eventsapp.presentation.ui.theme.NunitoFont
import com.unalminas.eventsapp.presentation.ui.theme.OxfordBlue
import com.unalminas.eventsapp.presentation.ui.theme.Snow
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun EventsScreens(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    eventViewModel: EventsViewModel = hiltViewModel(),
) {

    val eventListState by eventViewModel.eventListState.collectAsState(emptyList())
    var dialogState by rememberSaveable { mutableStateOf(false) }
    var currentEvent by remember { mutableStateOf(Event()) }

    LaunchedEffect(Unit) {
        eventViewModel.getEventList()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Snow)
    ) {
        Row {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .padding(start = 38.dp, top = 16.dp, bottom = 16.dp),
                text = stringResource(R.string.events),
                fontFamily = NunitoFont,
                fontWeight = FontWeight.Black,
                color = OxfordBlue,
                style = MaterialTheme.typography.titleLarge,
            )
        }
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
                val screen = Screen.AttendantScreen(event.id.toString())
                navController.navigate(screen.createRoute())
            }
        )
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
                            eventViewModel.deleteEventById(nonNullId)
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
    eventListState: List<Event>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(OxfordBlue, RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
            .padding(top = 32.dp)
            .clip(shape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10)),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        items(items = eventListState) { event ->
            val delete = SwipeAction(
                onSwipe = { onSwipe(event) },
                icon = {
                    Icon(
                        modifier = Modifier.padding(16.dp),
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                background = Color.Transparent
            )
            SwipeableActionsBox(
                swipeThreshold = 85.dp,
                endActions = listOf(delete),
                backgroundUntilSwipeThreshold = Color.Transparent
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
