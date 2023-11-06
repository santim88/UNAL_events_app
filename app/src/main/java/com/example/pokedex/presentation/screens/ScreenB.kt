package com.example.pokedex.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.pokedex.data.EventDataSource
import com.example.pokedex.domain.Event
import com.example.pokedex.presentation.ScreenMainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun ScreenB(
    navController: NavHostController,
    evenViewModel: ScreenMainViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        evenViewModel.getEventList()
    }


    UserForm(navController, evenViewModel)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun UserForm(
    navController: NavHostController,
    evenViewModel: ScreenMainViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }


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
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Fecha") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = time,
            onValueChange = { time = it },
            label = { Text("Horario") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Lugar") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val newEvent = Event(
                    name = name,
                    description = description,
                    place = location,
                    date = date,
                    hour = time
                )

                CoroutineScope(Dispatchers.IO).launch {
                    evenViewModel.insertEvent(newEvent)
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
