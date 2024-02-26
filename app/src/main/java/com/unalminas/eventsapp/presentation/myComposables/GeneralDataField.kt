package com.unalminas.eventsapp.presentation.myComposables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.unalminas.eventsapp.presentation.screens.events.FormEventViewModel
import com.unalminas.eventsapp.presentation.ui.theme.BlueGreen
import com.unalminas.eventsapp.presentation.ui.theme.NunitoFont
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
    isError: Boolean,
) {
    var string by rememberSaveable {
        mutableStateOf(value)
    }
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = label,
        singleLine = singleLine,

        textStyle = TextStyle(
            fontFamily = NunitoFont
        ),
        isError = isError,
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