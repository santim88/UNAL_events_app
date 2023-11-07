package com.unalminas.eventsapp.presentation.screens

import android.util.Log
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Remove this component
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenEdit(
    navController: NavHostController,
    id: String?,
    viewModel: EditEventViewModel = hiltViewModel(),
) {
    val eventState by viewModel.eventState.collectAsState()

    LaunchedEffect(Unit) {
        Log.i("ScreenEdit", "id: $id")
        viewModel.getEventById(id?.toInt()) //duda
    }

    val eventName = remember { mutableStateOf(eventState?.name ?: "") }
    val eventDescription = remember { mutableStateOf(eventState?.description ?: "") }
    val eventPlace = remember { mutableStateOf(eventState?.place ?: "") }
    val eventDate = remember { mutableStateOf(eventState?.date ?: "") }
    val eventHour = remember { mutableStateOf(eventState?.hour ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = {
            navController.navigate("MainScreen") {
                popUpTo("MainScreen") { inclusive = true }
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
                    viewModel.updateEvent(updateCurrentEvent)
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
            Text("Save")
        }
    }
}
