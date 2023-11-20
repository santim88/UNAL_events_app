package com.unalminas.eventsapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.ui.AssistantTable
import com.unalminas.eventsapp.presentation.ui.IndicatorEventBox
import com.unalminas.eventsapp.presentation.ui.TopBar_Title

@Composable
fun AssistantScreen(
    navController: NavHostController,
    viewModel: AssistantScreenViewModel = hiltViewModel(),
    id: Int? = null
) {
    LaunchedEffect(Unit) {
        viewModel.getAssistantList()
        id?.let {
            viewModel.getEventById(id)
        }
    }

    val eventCurrent by viewModel.eventCurrentState.collectAsState(Event())
    val assistantListState by viewModel.assistantListState.collectAsState(emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar_Title(
                title = stringResource(id = R.string.list_assistants),
                showBackButton = true,
                onBackButtonClick = {
                    navController.navigate(Screen.MainScreen.route)
                }
            )
            IndicatorEventBox(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.LightGray)
                    .fillMaxWidth(),
                event = eventCurrent
            )
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = "Asistentes: 45", // TODO
                style = TextStyle(fontSize = 16.sp, color = Color.Gray)
            )
            AssistantTable(assistantListState, navController)
        }
        FloatingActionButton(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomEnd),
            onClick = {
                navController.navigate(Screen.AssistantForm.route)
            },
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
}
