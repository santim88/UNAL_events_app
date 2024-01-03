package com.unalminas.eventsapp.presentation.myComposables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.presentation.screens.events.EventsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun InfoDialogContent(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit = {},
    onCancel: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth(1f),
        shape = RoundedCornerShape(17.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(red = 28, green = 27, blue = 31)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(1f),

            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = title),
                color = Color.White
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(13.dp)
            ) {
                Button(
                    onClick = onCancel
                ) {
                    Text(text = stringResource(id = R.string.cancel))
                }

                Button(
                    onClick = onDeleteClick
                ) {
                    Text(text = stringResource(id = R.string.delete))
                }
            }
        }
    }
}