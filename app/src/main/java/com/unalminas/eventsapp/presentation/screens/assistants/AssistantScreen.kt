package com.unalminas.eventsapp.presentation.screens.assistants

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.presentation.screens.assistants.adapter.AssistantTable
import com.unalminas.eventsapp.presentation.screens.assistants.adapter.BottomFloatingDropMenu
import com.unalminas.eventsapp.presentation.screens.assistants.adapter.IndicatorEventBox
import com.unalminas.eventsapp.presentation.ui.TopBarTitle
import com.unalminas.eventsapp.presentation.ui.theme.LatoFont
import com.unalminas.eventsapp.presentation.ui.theme.OxfordBlue
import com.unalminas.eventsapp.presentation.ui.theme.Snow

@Composable
fun AssistantScreen(
    navController: NavHostController,
    viewModel: AssistantScreenViewModel = hiltViewModel(),
    id: Int? = null,
) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    val menuItems = listOf(
        stringResource(id = R.string.enter_assistant),
        stringResource(id = R.string.register_qr),
    )

    LaunchedEffect(Unit) {
        id?.let {
            viewModel.getAssistantListByEvent(id)
        }
        id?.let {
            viewModel.getEventById(id)
        }
    }

    val eventCurrent by viewModel.eventCurrentState.collectAsState(Event())
    val assistantListState by viewModel.assistantListState.collectAsState(emptyList())
    val itemCount = assistantListState.size

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OxfordBlue)
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBarTitle(
                title = stringResource(id = R.string.list_assistants),
                showBackButton = true,
                backButtonColor = Snow,
                onBackButtonClick = {
                    navController.popBackStack()
                }
            )
            IndicatorEventBox(
                navController = navController,
                event = eventCurrent
            )
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = stringResource(id = R.string.dialog_state_format, itemCount),
                fontFamily = LatoFont,
                fontWeight = FontWeight.Black,
                style = TextStyle(fontSize = 16.sp, color = Snow)
            )
            AssistantTable(assistantListState, navController)
        }
        BottomFloatingDropMenu(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomEnd),
            isMenuExpanded = isMenuExpanded,
            onMenuExpandedChanged = { isMenuExpanded = it },
            menuItems = menuItems,
            navController = navController,
            eventId = id
        )
    }
}
