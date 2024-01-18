package com.unalminas.eventsapp.presentation.screens.events.adapter

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.presentation.ui.theme.LatoFont
import com.unalminas.eventsapp.presentation.ui.theme.Melon
import com.unalminas.eventsapp.presentation.ui.theme.OxfordBlue
import com.unalminas.eventsapp.presentation.ui.theme.PrussianBlue
import com.unalminas.eventsapp.presentation.ui.theme.Snow

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun CardEvent(
    modifier: Modifier = Modifier.padding(vertical = 2.dp),
    event: Event = Event(
        id = 100,
        name = "Event 1",
        description = "Event description",
        place = "M2",
        date = "03/02/2023",
        hour = "12:40"
    ),
    changeScreen: () -> Unit = {},
    editEvent: () -> Unit = {},
) {

    var expanded by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(15))
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.elevatedCardColors(
            contentColor = Snow
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(10.dp)
                        .basicMarquee(),
                    text = "${event.name} | ${event.place}",
                    fontFamily = LatoFont,
                    fontWeight = FontWeight.Bold,
                    color = OxfordBlue,
                    style = MaterialTheme.typography.headlineSmall,
                )
                IconButton(modifier = Modifier, onClick = editEvent) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "edit event",
                        tint = PrussianBlue
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 14.dp)
                    .padding(bottom = 12.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    modifier = modifier,
                    text = stringResource(
                        id = R.string.event_description_format,
                        event.description
                    ),
                    color = OxfordBlue,
                    fontFamily = LatoFont,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    modifier = modifier,
                    text = stringResource(
                        id = R.string.event_date_format,
                        event.date
                    ),
                    color = OxfordBlue,
                    fontFamily = LatoFont,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    modifier = modifier,
                    text = stringResource(
                        id = R.string.event_hour_format,
                        event.hour
                    ),
                    color = OxfordBlue,
                    fontFamily = LatoFont,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            AnimatedVisibility(visible = expanded) {
                Button(
                    onClick = changeScreen,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 0.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Snow,
                        containerColor = PrussianBlue
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.take_asistence),
                        fontFamily = LatoFont,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

