package com.unalminas.eventsapp.presentation.myComposables

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.unalminas.eventsapp.presentation.screens.events.FormEventViewModel
import com.unalminas.eventsapp.presentation.ui.theme.BlueGreen
import com.unalminas.eventsapp.presentation.ui.theme.Melon
import com.unalminas.eventsapp.presentation.ui.theme.NunitoFont
import com.unalminas.eventsapp.presentation.ui.theme.OxfordBlue
import com.unalminas.eventsapp.presentation.ui.theme.Platinum


@Composable
fun DateField(
    valueDate: String,
    onValueChange: (String) -> Unit,
    label: String,
    viewModel: FormEventViewModel = hiltViewModel()
) {
    var date by rememberSaveable { mutableStateOf(valueDate) }
    val year: Int
    val month: Int
    val day: Int
    val myCalendar: Calendar = Calendar.getInstance()

    year = myCalendar.get(Calendar.YEAR)
    month = myCalendar.get(Calendar.MONTH)
    day = myCalendar.get(Calendar.DAY_OF_MONTH)

    val myDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year: Int, month: Int, day: Int ->
            val formattedMonth = (month + 1).toString().padStart(2, '0')
            val formattedDay = day.toString().padStart(2, '0')
            date = "$formattedDay-$formattedMonth-$year"
            viewModel.isValidDate(date)
            onValueChange(date)
        },
        year,
        month,
        day
    )
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = valueDate,
        onValueChange = {
            date = it
            viewModel.isValidDate(date)
            onValueChange(date)
        },
        textStyle = TextStyle(
            fontFamily = NunitoFont
        ),
        label = {
            Text(
                text = label,
                fontFamily = NunitoFont,
            )
        },
        trailingIcon = {
            IconButton(
                modifier = Modifier,
                onClick = {
                    myDatePickerDialog.show()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.CalendarToday,
                    contentDescription = "edit event",
                    modifier = Modifier.size(26.dp),
                    tint = OxfordBlue
                )
            }
        },
        isError = !viewModel.isValidDateState.collectAsState().value,
        singleLine = true,
        shape = RoundedCornerShape(30),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Platinum,
            focusedContainerColor = Platinum,
            errorContainerColor = Melon,
            unfocusedBorderColor = BlueGreen,
            focusedBorderColor = BlueGreen,
            unfocusedTextColor = OxfordBlue,
            focusedTextColor = OxfordBlue,
            errorTextColor = MaterialTheme.colorScheme.error,
            unfocusedLabelColor = BlueGreen,
            focusedLabelColor = BlueGreen,
            errorLabelColor = MaterialTheme.colorScheme.error,
            cursorColor = OxfordBlue
        )
    )
}
