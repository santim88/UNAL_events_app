package com.unalminas.eventsapp.presentation.myComposables

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.unalminas.eventsapp.R
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
            time = "$hourOfDay:$minute"
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
                    IconButton(modifier = Modifier, onClick = {
                        myTimePickerDialog.show()
                    }) {
                        Image(
                            painterResource(R.drawable.baseline_access_time_24),
                            contentDescription = "edit event",
                            modifier = Modifier.size(26.dp)
                        )
                    }
                })
        }
    }
}