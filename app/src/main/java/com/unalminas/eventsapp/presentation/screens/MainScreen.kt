package com.unalminas.eventsapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.ui.CardEvent
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

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
                val dialogState: MutableState<Boolean> = remember {
                    mutableStateOf(false)
                }
                val delete = SwipeAction(
                    onSwipe = {
                        dialogState.value = true
                    },
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
                if (dialogState.value) {
                    Dialog(
                        onDismissRequest = { dialogState.value = false },
                        content = {
                            InfoDialogContent(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                dialogState = dialogState
                            )
                        },
                        properties = DialogProperties(
                            dismissOnBackPress = false,
                            dismissOnClickOutside = true
                        )
                    )
                }

                SwipeableActionsBox(
                    swipeThreshold = 100.dp,
                    endActions = listOf(delete)
                ) {
                    CardEvent(
                        event = event,
                        changeScreen = {
                            navController.navigate(Screen.AssistantScreen.route)
                        }, editEvent = {
                            event.id?.let { nonNullId ->
                                val screen = Screen.EditEventScreen(nonNullId.toString())
                                navController.navigate(screen.createRoute())
                            }
                        }, deleteEvent = {
                            event.id?.let { nonNullId ->
                                viewModel.deleteEventById(nonNullId)
                            }
                        }
                    )
                }
            }
        }
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
}

@Preview
@Composable
fun InfoDialogContent(
    modifier: Modifier = Modifier,
    title: String = "Title",
    dialogState: MutableState<Boolean> = remember { mutableStateOf(true) },
    successButtonText: String = "Success",
    content: @Composable () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth(1f),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(red = 28, green = 27, blue = 31)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box() {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart),
                    text = "Deseas borrar el evento:",
                    color = Color.White
                )
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black.copy(alpha = 0.4f))
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { }
                    ) {
                        Text("Delete")
                    }

                    Button(
                        onClick = { }
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}