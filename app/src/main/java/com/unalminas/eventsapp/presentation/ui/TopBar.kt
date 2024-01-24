package com.unalminas.eventsapp.presentation.ui

import androidx.compose.foundation.background
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.unalminas.eventsapp.presentation.ui.theme.NunitoFont
import com.unalminas.eventsapp.presentation.ui.theme.Snow

@Composable
fun TopBarTitle(
    modifier: Modifier = Modifier,
    title: String,
    showBackButton: Boolean = true,
    backButtonColor: Color = Snow,
    onBackButtonClick: () -> Unit,
) {
    Box(
        modifier = modifier.background(Color.Transparent),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .statusBarsPadding(),
            horizontalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            if (showBackButton) IconButton(
                modifier = Modifier
                    .padding(10.dp),
                onClick = onBackButtonClick
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = backButtonColor
                )
            }
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 0.dp, top = 5.dp, bottom = 5.dp)
                    .weight(1f),
                text = title,
                fontFamily = NunitoFont,
                fontWeight = FontWeight.Black,
                color = backButtonColor,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
