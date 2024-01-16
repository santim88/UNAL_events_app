package com.unalminas.eventsapp.presentation.myComposables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.unalminas.eventsapp.presentation.ui.theme.Beige
import com.unalminas.eventsapp.presentation.ui.theme.Buff
import com.unalminas.eventsapp.presentation.ui.theme.Cornsilk
import com.unalminas.eventsapp.presentation.ui.theme.FiraFont
import com.unalminas.eventsapp.presentation.ui.theme.TextBlack

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
            fontFamily = FiraFont
        ),
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