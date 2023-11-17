package com.unalminas.eventsapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.EventFieldEnum
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.ui.TopBar_Title
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FormEventScreen(
    navController: NavHostController,
    id: Int? = null,
    isNewEvent: Boolean,
    viewModel: FormEventViewModel = hiltViewModel()
) {
    val event by viewModel.eventState.collectAsState()

    LaunchedEffect(isNewEvent) {
        if (!isNewEvent) id?.let {
            viewModel.getEventById(it)
        }
    }

    Column {
        TopBar_Title(
            title = if (isNewEvent) R.string.name_event.toString() else R.string.edit_event.toString(),
            showBackButton = true,
            onBackButtonClick = {
                navController.navigate(Screen.MainScreen.route)
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                OutlinedTextField(
                    value = event.name,
                    onValueChange = { newName ->
                        viewModel.editEventField(EventFieldEnum.NAME, newName)
                    },
                    label = {
                        Text(
                            text = stringResource(
                                id = R.string.name_event
                            )
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = event.description,
                    onValueChange = { newDescription ->
                        viewModel.editEventField(EventFieldEnum.DESCRIPTION, newDescription)
                    },
                    label = {
                        Text(
                            text = stringResource(
                                id = R.string.description_event
                            )
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = event.date,
                    onValueChange = { newDate ->
                        viewModel.editEventField(EventFieldEnum.DATE, newDate)
                    },
                    label = {
                        Text(
                            text = stringResource(
                                id = R.string.date_event
                            )
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = event.hour,
                    onValueChange = { newHour ->
                        viewModel.editEventField(EventFieldEnum.HOUR, newHour)
                    },
                    label = {
                        Text(
                            text = stringResource(
                                id = R.string.hour_event
                            )
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = event.place,
                    onValueChange = { newPlace ->
                        viewModel.editEventField(EventFieldEnum.PLACE, newPlace)
                    },
                    label = {
                        Text(
                            text = stringResource(
                                id = R.string.place_event
                            )
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Button(
                    onClick = {
                        if (isNewEvent) {
                            viewModel.insertEvent(event)
                        } else {
                            viewModel.updateEvent(event)
                        }

                        CoroutineScope(Dispatchers.IO).launch {
                            withContext(Dispatchers.Main) {
                                navController.navigate("MainScreen") {
                                    popUpTo("MainScreen") { inclusive = true }
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                ) {
                    Text(
                        text = stringResource(
                            id = if (isNewEvent) R.string.create_event else R.string.save_event
                        )
                    )
                }
            }
        }
    }
}
