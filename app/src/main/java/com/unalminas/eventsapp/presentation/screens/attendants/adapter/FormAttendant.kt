package com.unalminas.eventsapp.presentation.screens.attendants.adapter

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
import com.unalminas.eventsapp.domain.AttendantFieldEnum
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.myComposables.GeneralDataField
import com.unalminas.eventsapp.presentation.screens.Attendants.AttendantScreenViewModel
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
fun FormAttendant(
    navController: NavHostController,
    id: Int? = null,
    eventId: Int? = null,
    isNewAttendant: Boolean,
    viewModel: AttendantScreenViewModel = hiltViewModel()
) {
    val attendant by viewModel.attendantState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(isNewAttendant) {
        if (!isNewAttendant) id?.let {
            viewModel.getAttendantById(id)
        } else {
            viewModel.editAttendantField(AttendantFieldEnum.EVENTID, eventId.toString())
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OxfordBlue)
    ) {
        TopBarTitle(
            title = stringResource(id = if (isNewAttendant) R.string.create_attendant else R.string.edit_attendant),
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
                value = attendant.name,
                onValueChange = { newName ->
                    viewModel.isValidName(newName)
                    viewModel.editAttendantField(AttendantFieldEnum.NAME, newName)
                },
                label = {
                    Text(
                        text = stringResource(
                            id = R.string.name_attendant
                        )
                    )
                },
                singleLine = true,
                isError = !viewModel.isValidNameState.collectAsState().value
            )
            GeneralDataField(
                modifier = Modifier.fillMaxWidth(),
                value = attendant.identification,
                onValueChange = { newIdentification ->
                    viewModel.isValidIdentification(newIdentification)
                    viewModel.editAttendantField(
                        AttendantFieldEnum.IDENTIFICATION,
                        newIdentification
                    )
                },
                label = {
                    Text(
                        text = stringResource(
                            id = R.string.identification_attendant
                        )
                    )
                },
                singleLine = true,
                isError = !viewModel.isValidIdentificationState.collectAsState().value
            )
            GeneralDataField(
                modifier = Modifier.fillMaxWidth(),
                value = attendant.email,
                onValueChange = { newEmail ->
                    viewModel.isValidEmail(newEmail)
                    viewModel.editAttendantField(AttendantFieldEnum.EMAIL, newEmail)
                },
                label = {
                    Text(
                        text = stringResource(
                            id = R.string.email_attendant
                        )
                    )
                },
                singleLine = true,
                isError = !viewModel.isValidEmailState.collectAsState().value
            )
            Button(
                onClick = {
                    if (viewModel.areAllValidFields(attendant)) {
                        if (isNewAttendant) {
                            viewModel.createAttendant(attendant)
                            CoroutineScope(Dispatchers.IO).launch {
                                withContext(Dispatchers.Main) {
                                    navController.popBackStack()
                                }
                            }
                        } else {
                            viewModel.updateAttendant(attendant)
                            CoroutineScope(Dispatchers.IO).launch {
                                withContext(Dispatchers.Main) {
                                    val screen =
                                        Screen.AttendantScreen(attendant.eventId.toString())
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
                        id = R.string.save_attendant
                    ),
                    fontFamily = NunitoFont,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
