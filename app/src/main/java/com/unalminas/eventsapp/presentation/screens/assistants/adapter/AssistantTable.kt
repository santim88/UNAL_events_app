package com.unalminas.eventsapp.presentation.screens.assistants.adapter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.Assistant
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.myComposables.InfoDialogContent
import com.unalminas.eventsapp.presentation.screens.assistants.AssistantScreenViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun AssistantTable(
    eventListState: List<Assistant> = listOf(),
    navController: NavController,
    assistantViewModel: AssistantScreenViewModel = hiltViewModel()
) {

    var dialogState by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "#")
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                text = stringResource(id = R.string.name_assistant),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.identification_assistant),
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.email_assistant),
                fontWeight = FontWeight.Bold
            )
        }

    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        var currentAssistant = Assistant()
        itemsIndexed(items = eventListState) { index, item ->

            val delete = SwipeAction(
                onSwipe = {
                    currentAssistant = item
                    dialogState = true
                },
                icon = {
                    Icon(
                        modifier = Modifier.padding(16.dp),
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                background = Color.LightGray
            )
            if (dialogState) {
                Dialog(
                    onDismissRequest = { dialogState = false },
                    content = {
                        InfoDialogContent(
                            R.string.message_delete_assistant,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            onDeleteClick = {
                                currentAssistant.id?.let { nonNullId ->
                                    assistantViewModel.deleteAssistantById(nonNullId)
                                }
                                dialogState = false
                            },
                            onCancel = { dialogState = false }
                        )
                    },
                    properties = DialogProperties(
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true
                    )
                )
            }
            SwipeableActionsBox(
                swipeThreshold = 50.dp,
                endActions = listOf(delete)
            ) {
                CardAssistant(modifier = Modifier.clickable {
                    item.id?.let { nonNullId ->
                        val screen = Screen.EditAssistantScreen(nonNullId.toString())
                        navController.navigate(screen.createRoute())
                    }
                }, index + 1, item)
            }
        }
    }
}