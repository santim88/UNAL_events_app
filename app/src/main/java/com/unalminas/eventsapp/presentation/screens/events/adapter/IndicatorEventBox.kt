package com.unalminas.eventsapp.presentation.screens.events.adapter


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.Event

@Composable
fun IndicatorEventBox(
    modifier: Modifier = Modifier,
    event: Event
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            text = stringResource(id = R.string.event_name_format, event.name),
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.event_date_format, event.date),
            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            text = stringResource(id = R.string.event_place_format, event.place),
            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
        )
    }
}