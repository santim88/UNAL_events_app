package com.unalminas.eventsapp.presentation.myComposables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.unalminas.eventsapp.presentation.ui.theme.DesertSand
import com.unalminas.eventsapp.presentation.ui.theme.LatoFont
import com.unalminas.eventsapp.presentation.ui.theme.LavenderBlush
import com.unalminas.eventsapp.presentation.ui.theme.Melon
import com.unalminas.eventsapp.presentation.ui.theme.OxfordBlue

@Composable
fun DataField(
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
        shape = RoundedCornerShape(20.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = LavenderBlush,
            focusedContainerColor = LavenderBlush,
            errorContainerColor = DesertSand,
            unfocusedBorderColor = Melon,
            focusedBorderColor = Melon,
            unfocusedTextColor = OxfordBlue,
            focusedTextColor = OxfordBlue,
            errorTextColor = MaterialTheme.colorScheme.error,
            unfocusedLabelColor = OxfordBlue,
            focusedLabelColor = OxfordBlue,
            errorLabelColor = MaterialTheme.colorScheme.error,
            cursorColor = OxfordBlue
        )
    )
}