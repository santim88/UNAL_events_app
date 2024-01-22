package com.unalminas.eventsapp.presentation.myComposables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.unalminas.eventsapp.presentation.ui.theme.BlueGreen
import com.unalminas.eventsapp.presentation.ui.theme.LatoFont
import com.unalminas.eventsapp.presentation.ui.theme.Melon
import com.unalminas.eventsapp.presentation.ui.theme.OxfordBlue
import com.unalminas.eventsapp.presentation.ui.theme.Platinum

@Composable
fun GeneralDataField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable() (() -> Unit)?,
    singleLine: Boolean,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = label,
        singleLine = singleLine,

        textStyle = TextStyle(
            fontFamily = LatoFont
        ),
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