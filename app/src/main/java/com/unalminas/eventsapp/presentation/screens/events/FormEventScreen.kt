package com.unalminas.eventsapp.presentation.screens.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.EventFieldEnum
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.myComposables.DataField
import com.unalminas.eventsapp.presentation.myComposables.DateField
import com.unalminas.eventsapp.presentation.myComposables.HourField
import com.unalminas.eventsapp.presentation.ui.TopBarTitle
import com.unalminas.eventsapp.presentation.ui.theme.Buff
import com.unalminas.eventsapp.presentation.ui.theme.FiraFont
import com.unalminas.eventsapp.presentation.ui.theme.PapayaWhip
import com.unalminas.eventsapp.presentation.ui.theme.TeaGreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun FormEventScreen(
    navController: NavHostController,
    id: Int? = null,
    isNewEvent: Boolean,
    dateEvent: String? = null,
    viewModel: FormEventViewModel = hiltViewModel()
) {
    val event by viewModel.eventState.collectAsState()
    if(dateEvent != null){
        event.date = dateEvent
    }

    LaunchedEffect(isNewEvent) {
        if (!isNewEvent) id?.let {
            viewModel.getEventById(it)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TeaGreen)
    ) {
        TopBarTitle(
            title = stringResource(id = if (isNewEvent) R.string.create_event else R.string.edit_event),
            showBackButton = true,
            onBackButtonClick = {
                navController.popBackStack()
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                DataField(
                    modifier = Modifier.fillMaxWidth(),
                    value = event.name,
                    onValueChange = { newName ->
                        viewModel.editEventField(EventFieldEnum.NAME, newName)
                    },
                    label = {
                        Text(
                            text = stringResource(
                                id = R.string.name_event
                            ),
                            fontFamily = FiraFont
                        )
                    },
                    singleLine = true,
                )
            }
            item {
                DataField(
                    modifier = Modifier.fillMaxWidth(),
                    value = event.description,
                    onValueChange = { newDescription ->
                        viewModel.editEventField(EventFieldEnum.DESCRIPTION, newDescription)
                    },
                    label = {
                        Text(
                            text = stringResource(
                                id = R.string.description_event
                            ),
                            fontFamily = FiraFont
                        )
                    },
                    singleLine = true,
                )
            }
            item {
                DateField(
                    valueDate = event.date,
                    onValueChange = { newDate ->
                        viewModel.editEventField(EventFieldEnum.DATE, newDate)
                    },
                    label = stringResource(
                        id = R.string.date_event
                    )
                )
            }
            item {
                HourField(
                    value = event.hour,
                    onValueChange = { newHour ->
                        viewModel.editEventField(EventFieldEnum.HOUR, newHour)
                    },
                    stringResource(
                        id = R.string.hour_event
                    )
                )
            }
            item {
                DataField(
                    modifier = Modifier.fillMaxWidth(),
                    value = event.place,
                    onValueChange = { newPlace ->
                        viewModel.editEventField(EventFieldEnum.PLACE, newPlace)
                    },
                    label = {
                        Text(
                            text = stringResource(
                                id = R.string.place_event
                            ),
                            fontFamily = FiraFont
                        )
                    },
                    singleLine = true,
                )
            }
            item {
                Button(
                    modifier = Modifier
                        .weight(0.33f)
                        .height(50.dp),
                    onClick = {
                        if (isNewEvent) {
                            viewModel.insertEvent(event)
                        } else {
                            viewModel.updateEvent(event)
                        }
                        CoroutineScope(Dispatchers.IO).launch {
                            withContext(Dispatchers.Main) {
                                navController.navigate(Screen.HomeScreen.EventsRoute.route)
                            }
                        }
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Buff,
                        contentColor = PapayaWhip
                    )
                ) {
                    Text(
                        text = stringResource(
                            id = if (isNewEvent) R.string.create_event else R.string.save_event
                        ),
                        fontFamily = FiraFont,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}
