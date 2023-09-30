package com.example.pokedex.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pokedex.domain.Event

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
    onClick: () -> Unit = {}
) {
    ElevatedCard(
        Modifier
            .fillMaxWidth()
            .clickable { onClick() }
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
                    /*  fontWeight = FontWeight.Bold,*/
                    /*   fontSize = MaterialTheme.typography.headlineMedium*/
                )
                EditButtons(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                )
            }
            Column(Modifier.padding(horizontal = 10.dp)) {
                Text(
                    text = event.date,
                    color = Color.Black,
                    /*       fontWeight = FontWeight.Normal,*/
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = event.hour,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
