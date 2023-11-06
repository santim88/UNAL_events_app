package com.unalminas.eventsapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.Event

@Preview
@Composable
fun CardEvent(
    modifier: Modifier = Modifier,
    event: Event = Event(
        id = 100,
        name = "Event 1",
        description = "Event description",
        place = "M2",
        date = "03/02/2023",
        hour = "12:40"
    ),
    changeScreen: () -> Unit = {},
    deleteEvent: () -> Unit = {},
    editEvent: () -> Unit = {}
) {
    ElevatedCard(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Column(Modifier.fillMaxWidth()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterStart),
                    text = "${event.name} | ${event.place}",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall,
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
                ) {
                    IconButton(modifier = Modifier, onClick = editEvent) {
                        Image(
                            painterResource(R.drawable.baseline_edit_24),
                            contentDescription = "like",
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    IconButton(modifier = Modifier, onClick = deleteEvent) {
                        Image(
                            painterResource(R.drawable.baseline_delete_24),
                            contentDescription = "like",
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
            }
            Column(Modifier.padding(horizontal = 10.dp)) {
                Text(
                    text = event.description,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = event.date,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = event.hour,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Button(
                onClick = changeScreen,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
            ) {
                Text(text = "TOMAR ASISTENCIA", fontSize = 8.sp)
            }
        }
    }
}
