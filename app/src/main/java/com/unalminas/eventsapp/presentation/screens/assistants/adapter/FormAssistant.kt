package com.unalminas.eventsapp.presentation.screens.assistants.adapter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
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
import com.unalminas.eventsapp.domain.AssistantFieldEnum
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.screens.assistants.AssistantScreenViewModel
import com.unalminas.eventsapp.presentation.ui.TopBar_Title
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun FormAssistant(
    navController: NavHostController,
    id: Int? = null,
    eventId: Int? = null,
    isNewAssistant: Boolean,
    viewModel: AssistantScreenViewModel = hiltViewModel()
) {
    val assistant by viewModel.assistantState.collectAsState()

    LaunchedEffect(isNewAssistant) {
        if (!isNewAssistant) id?.let {
            viewModel.getAssistantById(id)
        }else{
            viewModel.editAssistantField(AssistantFieldEnum.EVENTID, eventId.toString())
        }
    }

    Column {
        TopBar_Title(
            title = stringResource(id = if (isNewAssistant) R.string.create_assistant else R.string.edit_assistant),
            showBackButton = true,
            onBackButtonClick = {
                navController.popBackStack()
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
                    value = assistant.name,
                    onValueChange = { newName ->
                        viewModel.editAssistantField(AssistantFieldEnum.NAME, newName)
                    },
                    label = {
                        Text(
                            text = stringResource(
                                id = R.string.name_assistant
                            )
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = assistant.identification,
                    onValueChange = { newIdentification ->
                        viewModel.editAssistantField(AssistantFieldEnum.IDENTIFICATION, newIdentification)
                    },
                    label = {
                        Text(
                            text = stringResource(
                                id = R.string.identification_assistant
                            )
                        )
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = assistant.email,
                    onValueChange = { newEmail ->
                        viewModel.editAssistantField(AssistantFieldEnum.EMAIL, newEmail)
                    },
                    label = {
                        Text(  
                            text = stringResource(
                                id = R.string.email_assistant
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
                        if (isNewAssistant) {
                            viewModel.createAssistant(assistant)
                            CoroutineScope(Dispatchers.IO).launch {
                                withContext(Dispatchers.Main) {
                                    navController.popBackStack()
                                }
                            }
                        } else {
                            viewModel.updateAssistant(assistant)
                            CoroutineScope(Dispatchers.IO).launch {
                                withContext(Dispatchers.Main) {
                                    val screen = Screen.AssistantScreen(assistant.eventId.toString())
                                    navController.navigate(screen.createRoute())
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
                            id = R.string.save_assistant
                        )
                    )
                }
            }
        }
    }
}