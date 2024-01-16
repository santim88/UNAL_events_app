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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.presentation.ui.theme.Beige
import com.unalminas.eventsapp.presentation.ui.theme.Buff
import com.unalminas.eventsapp.presentation.ui.theme.Cornsilk
import com.unalminas.eventsapp.presentation.ui.theme.FiraFont
import com.unalminas.eventsapp.presentation.ui.theme.TextBlack

@Composable
fun DateField(
    valueDate: String,
    onValueChange: (String) -> Unit,
    label: String,
) {
    var date by rememberSaveable { mutableStateOf(valueDate) }
    var isValidDate by remember { mutableStateOf(true) }
    val dateRegex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d\\d$".toRegex()
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
            date = "$formattedDay/$formattedMonth/$year"
            isValidDate = date.matches(dateRegex)
            onValueChange(date)
        },
        year,
        month,
        day
    )
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = date,
        onValueChange = {
            date = it
            isValidDate = date.matches(dateRegex)
            onValueChange(date)
        },
        textStyle = TextStyle(
            fontFamily = FiraFont
        ),
        label = {
            Text(
                text = label,
                fontFamily = FiraFont,
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.dd_mm_yyyy),
                fontFamily = FiraFont,
                fontStyle = FontStyle.Italic
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
                    modifier = Modifier.size(26.dp)
                )
            }
        },
        isError = !isValidDate,
        singleLine = true,
        shape = RoundedCornerShape(20.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Beige,
            focusedContainerColor = Beige,
            errorContainerColor = Buff,
            unfocusedBorderColor = Cornsilk,
            focusedBorderColor = Cornsilk,
            unfocusedTextColor = TextBlack,
            focusedTextColor = TextBlack,
            errorTextColor = MaterialTheme.colorScheme.error,
            unfocusedLabelColor = Buff,
            focusedLabelColor = Buff,
            errorLabelColor = MaterialTheme.colorScheme.error,
            cursorColor = TextBlack
        )
    )
}
