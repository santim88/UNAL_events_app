package com.unalminas.eventsapp.presentation.myComposables

import android.app.TimePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.presentation.ui.theme.Beige
import com.unalminas.eventsapp.presentation.ui.theme.Buff
import com.unalminas.eventsapp.presentation.ui.theme.Cornsilk
import com.unalminas.eventsapp.presentation.ui.theme.FiraFont
import com.unalminas.eventsapp.presentation.ui.theme.TextBlack
import java.util.Calendar

@Composable
fun HourField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    var time by rememberSaveable {
        mutableStateOf("")
    }
    val hourOfDay: Int
    val minute: Int
    val myCalendar: Calendar = Calendar.getInstance()

    hourOfDay = myCalendar.get(Calendar.HOUR_OF_DAY)
    minute = myCalendar.get(Calendar.MINUTE)

    val myTimePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, hourOfDay: Int, minute: Int ->
            val formattedHour: String
            val amPm: String

            if (hourOfDay >= 12) {
                formattedHour = if (hourOfDay == 12) "12" else (hourOfDay - 12).toString().padStart(2, '0')
                amPm = "PM"
            } else {
                formattedHour = if (hourOfDay == 0) "12" else hourOfDay.toString().padStart(2, '0')
                amPm = "AM"
            }

            val formattedMinute = minute.toString().padStart(2, '0')
            val time = "$formattedHour:$formattedMinute $amPm"
            onValueChange(time)
        },
        hourOfDay,
        minute,
        false
    )

    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.align(Alignment.Center)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    fontFamily = FiraFont
                ),
                label = {
                    Text(
                        text = label,
                        fontFamily = FiraFont,
                    )
                },
                singleLine = true,
                trailingIcon = {
                    IconButton(
                        modifier = Modifier,
                        onClick = {
                            myTimePickerDialog.show()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AccessTime,
                            contentDescription = "edit event",
                            modifier = Modifier.size(26.dp)
                        )
                    }
                },
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
    }
}