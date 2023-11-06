package com.unalminas.eventsapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.presentation.ScreenMainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun ScreenEdit(
    navController: NavHostController,
    id: String?,
    evenViewModel: ScreenMainViewModel = hiltViewModel(),
) {
    val eventState by evenViewModel.eventState.collectAsState()

    LaunchedEffect(Unit) {
        evenViewModel.getEventById(id?.toInt())//duda
    }

    UserFormEdit(navController, evenViewModel, eventState)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun UserFormEdit(
    navController: NavHostController,
    eventViewModel: ScreenMainViewModel = hiltViewModel(),
    event: Event?
) {

    val eventName = remember { mutableStateOf(event?.name ?: "") }
    val eventDescription = remember { mutableStateOf(event?.description ?: "") }
    val eventPlace = remember { mutableStateOf(event?.place ?: "") }
    val eventDate = remember { mutableStateOf(event?.date ?: "") }
    val eventHour = remember { mutableStateOf(event?.hour ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = {
            navController.navigate("A") {
                popUpTo("A") { inclusive = true }
            }
        }) {
            Text(text = "volver", fontSize = 14.sp)
        }

        Text(
            text = "Crear un nuevo evento",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = eventName.value,
            onValueChange = { newName -> eventName.value = newName },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = eventDescription.value,
            onValueChange = { newDescription -> eventDescription.value = newDescription },
            label = { Text("Descripción") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = eventDate.value,
            onValueChange = { newDate -> eventDate.value = newDate },
            label = { Text("Fecha") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = eventHour.value,
            onValueChange = { newHour -> eventHour.value = newHour },
            label = { Text("Horario") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = eventPlace.value,
            onValueChange = { newPlace -> eventPlace.value = newPlace },
            label = { Text("Lugar") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val updateCurrentEvent = Event(
                    name = eventName.value,
                    description = eventDescription.value,
                    place = eventPlace.value,
                    date = eventDate.value,
                    hour = eventHour.value
                )

                CoroutineScope(Dispatchers.IO).launch {
                    eventViewModel.updateEvent(updateCurrentEvent)
                    withContext(Dispatchers.Main) {
                        navController.navigate("A") {
                            popUpTo("A") { inclusive = true }
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Text("Save")
        }
    }
}
