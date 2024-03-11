package com.unalminas.eventsapp.presentation.screens.events

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.EventFieldEnum
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.myComposables.DateField
import com.unalminas.eventsapp.presentation.myComposables.GeneralDataField
import com.unalminas.eventsapp.presentation.myComposables.HourField
import com.unalminas.eventsapp.presentation.ui.TopBarTitle
import com.unalminas.eventsapp.presentation.ui.theme.BlueGreen
import com.unalminas.eventsapp.presentation.ui.theme.NunitoFont
import com.unalminas.eventsapp.presentation.ui.theme.OxfordBlue
import com.unalminas.eventsapp.presentation.ui.theme.Snow
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
    viewModel: FormEventViewModel = hiltViewModel(),
) {
    val event by viewModel.eventState.collectAsState()
    if (dateEvent != null) {
        event.date = dateEvent
    }

    val context = LocalContext.current

    LaunchedEffect(isNewEvent) {
        if (!isNewEvent) id?.let {
            viewModel.getEventById(it)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OxfordBlue)
    ) {
        TopBarTitle(
            title = stringResource(id = if (isNewEvent) R.string.create_event else R.string.edit_event),
            showBackButton = true,
            backButtonColor = Snow,
            onBackButtonClick = {
                navController.navigate(Screen.HomeScreen.EventsRoute.route)
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
                GeneralDataField(
                    modifier = Modifier.fillMaxWidth(),
                    value = event.name,
                    onValueChange = { newName ->
                        viewModel.isValidName(newName)
                        viewModel.editEventField(EventFieldEnum.NAME, newName)
                    },
                    label = {
                        Text(
                            text = stringResource(
                                id = R.string.name_event
                            ),
                            fontFamily = NunitoFont
                        )
                    },
                    singleLine = true,
                    isError = !viewModel.isValidNameState.collectAsState().value
                )
            }
            item {
                GeneralDataField(
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
                            fontFamily = NunitoFont
                        )
                    },
                    singleLine = true,
                    isError = false
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
                    hour = event.hour,
                    onValueChange = { newHour ->
                        viewModel.editEventField(EventFieldEnum.HOUR, newHour)
                    },
                    stringResource(
                        id = R.string.hour_event
                    )
                )
            }
            item {
                GeneralDataField(
                    modifier = Modifier.fillMaxWidth(),
                    value = event.place,
                    onValueChange = { newPlace ->
                        viewModel.isValidPlace(newPlace)
                        viewModel.editEventField(EventFieldEnum.PLACE, newPlace)
                    },
                    label = {
                        Text(
                            text = stringResource(
                                id = R.string.place_event
                            ),
                            fontFamily = NunitoFont
                        )
                    },
                    singleLine = true,
                    isError = !viewModel.isValidPlaceState.collectAsState().value
                )
            }
            item {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.6f),
                    onClick = {
                        if (viewModel.areAllValidFields(event)) {
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
                        } else {
                            val text = context.getString(R.string.complete_all_required_fields)
                            Toast.makeText(
                                context,
                                text,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BlueGreen,
                        contentColor = Snow
                    )
                ) {
                    Text(
                        text = stringResource(
                            id = if (isNewEvent) R.string.create_event else R.string.save_event
                        ),
                        fontFamily = NunitoFont,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}
