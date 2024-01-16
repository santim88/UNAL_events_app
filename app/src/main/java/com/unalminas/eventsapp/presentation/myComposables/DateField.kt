package com.unalminas.eventsapp.presentation.myComposables

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.unalminas.eventsapp.R

@Composable
fun DateField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
) {
    var fecha by rememberSaveable { mutableStateOf(value) }
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
            fecha = "${day.toString().padStart(2, '0')}-${(month + 1).toString().padStart(2, '0')}-$year"
            onValueChange(fecha)
        },
        year,
        month,
        day
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label
            )
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(
                modifier = Modifier,
                onClick = {
                    myDatePickerDialog.show()
                }
            ) {
                Image(
                    painterResource(R.drawable.baseline_calendar_today_24),
                    contentDescription = "edit event",
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    )
}