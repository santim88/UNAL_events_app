package com.unalminas.eventsapp.presentation.screens.assistants.adapter

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.unalminas.eventsapp.domain.AssistantFieldEnum
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.myComposables.GeneralDataField
import com.unalminas.eventsapp.presentation.screens.assistants.AssistantScreenViewModel
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
fun FormAssistant(
    navController: NavHostController,
    id: Int? = null,
    eventId: Int? = null,
    isNewAssistant: Boolean,
    viewModel: AssistantScreenViewModel = hiltViewModel()
) {
    val assistant by viewModel.assistantState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(isNewAssistant) {
        if (!isNewAssistant) id?.let {
            viewModel.getAssistantById(id)
        } else {
            viewModel.editAssistantField(AssistantFieldEnum.EVENTID, eventId.toString())
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OxfordBlue)
    ) {
        TopBarTitle(
            title = stringResource(id = if (isNewAssistant) R.string.create_assistant else R.string.edit_assistant),
            showBackButton = true,
            backButtonColor = Snow,
            onBackButtonClick = {
                navController.popBackStack()
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GeneralDataField(
                modifier = Modifier.fillMaxWidth(),
                value = assistant.name,
                onValueChange = { newName ->
                    viewModel.isValidName(newName)
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
                isError = !viewModel.isValidNameState.collectAsState().value
            )
            GeneralDataField(
                modifier = Modifier.fillMaxWidth(),
                value = assistant.identification,
                onValueChange = { newIdentification ->
                    viewModel.isValidIdentification(newIdentification)
                    viewModel.editAssistantField(
                        AssistantFieldEnum.IDENTIFICATION,
                        newIdentification
                    )
                },
                label = {
                    Text(
                        text = stringResource(
                            id = R.string.identification_assistant
                        )
                    )
                },
                singleLine = true,
                isError = !viewModel.isValidIdentificationState.collectAsState().value
            )
            GeneralDataField(
                modifier = Modifier.fillMaxWidth(),
                value = assistant.email,
                onValueChange = { newEmail ->
                    viewModel.isValidEmail(newEmail)
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
                isError = !viewModel.isValidEmailState.collectAsState().value
            )
            Button(
                onClick = {
                    if (viewModel.areAllValidFields(assistant)) {
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
                                    val screen =
                                        Screen.AssistantScreen(assistant.eventId.toString())
                                    navController.navigate(screen.createRoute())
                                }
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
                ),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            ) {
                Text(
                    text = stringResource(
                        id = R.string.save_assistant
                    ),
                    fontFamily = NunitoFont,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}