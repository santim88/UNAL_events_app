package com.unalminas.eventsapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun TopBar_Title(
    modifier: Modifier = Modifier,
    title: String = "Empty Title",
    showBackButton: Boolean = true,
    backButtonColor: Color = Color.Black,
    onBackButtonClick: () -> Unit = {}
) {
    Box(
        modifier = modifier.background(Color.Transparent),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .statusBarsPadding(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            if (showBackButton) Icon(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable(onClick = onBackButtonClick),
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = backButtonColor
            )
            Text(
                text = title,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 20.dp, top = 5.dp, bottom = 5.dp)
                    .weight(1f),
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
