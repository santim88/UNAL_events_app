package com.unalminas.eventsapp.presentation.screens.calendar

import android.view.LayoutInflater
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.Event

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
) {
    val events = listOf(
        Event(name = "evebt 1", date = "16:27"),
        Event(name = "evebt 2", date = "17:30"),
        Event(name = "evebt 3", date = "23:32"),
    )

    Column(
        modifier = modifier
    ) {
        CustomCalendarView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "6 March",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    )
                    Text(
                        text = "THURSDAY",
                        color = Color.White,
                        style = MaterialTheme.typography.titleSmall
                    )
                }

                IconButton(modifier = Modifier, onClick = {}) {
                    Image(
                        painterResource(R.drawable.baseline_post_add_24),
                        contentDescription = "edit event",
                        modifier = Modifier.size(37.dp)
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxSize()
        ) {
            items(events) { event ->
                EventItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    event = event
                )
            }
        }
    }
    /*       CalendarScreenContent()*/
}

@Preview
@Composable
fun CustomCalendarView(
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = {
            LayoutInflater
                .from(it)
                .inflate(R.layout.calendar_xml, null, false)
        }
    )
}

@Preview
@Composable
fun EventItem(
    modifier: Modifier = Modifier,
    event: Event = Event(name = "Event 1", date = "16:27")
) {
    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xC9C1D9))
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Text(
                    text = event.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = event.date,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}
