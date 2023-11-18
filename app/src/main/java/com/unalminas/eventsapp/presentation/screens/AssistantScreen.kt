package com.unalminas.eventsapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.ui.AssistantTable
import com.unalminas.eventsapp.presentation.ui.IndicatorEventBox
import com.unalminas.eventsapp.presentation.ui.TopBar_Title

@Composable
fun AssistantScreen(
    navController: NavHostController,
    viewModel: AssistantScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getAssistantList()
    }
    val eventListState by viewModel.assistantListState.collectAsState(emptyList())

    Box(
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar_Title(
                title = "Lista de asistentes",
                showBackButton = true,
                onBackButtonClick = {
                    navController.navigate(Screen.MainScreen.route)
                }
            )
            IndicatorEventBox()
            Spacer(modifier = Modifier.height(45.dp))
            Text(
                text = "Asistentes: 45",
                style = TextStyle(fontSize = 16.sp, color = Color.Gray)
            )
            Spacer(modifier = Modifier.height(45.dp))

            AssistantTable(eventListState)

            Box(
            ) {
                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(20.dp),
                    onClick = {
                        navController.navigate(Screen.AssistantForm.route)
                    },
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }
        }
    }
}
