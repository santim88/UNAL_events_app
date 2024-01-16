package com.unalminas.eventsapp.presentation.screens.calendar

import android.widget.CalendarView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CoPresent
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.presentation.Screen
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val selectDate by viewModel.formattedDataState.collectAsState()
    val selectDateOnlyDay by viewModel.formattedDateStateOnlyDay.collectAsState()
    val currentDate by viewModel.currentDateState.collectAsState()
    val eventList by viewModel.eventListWithAssistantCountState.collectAsState()

    LaunchedEffect(currentDate) {
        viewModel.getEventsWithAssistantCount(currentDate)
    }

    LazyColumn(
        modifier = modifier
    ) {
        item {
            CustomCalendarView()
        }

        item {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
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
                            text = selectDate,
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge,
                            fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                        )
                        Text(
                            text = selectDateOnlyDay,
                            color = Color.White,
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = currentDate,
                            color = Color.White,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                    IconButton(
                        modifier = Modifier,
                        onClick = {
                            val screen = Screen.CreateEventScreenWithDate(currentDate)
                            navController.navigate(screen.createRoute())
                        }) {
                        Image(
                            painterResource(R.drawable.baseline_post_add_24),
                            contentDescription = "edit event",
                            modifier = Modifier.size(37.dp)
                        )
                    }
                }
            }
        }

        items(eventList) { event ->
            EventItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                event = event,
                navController = navController
            )
        }
    }
}


@Composable
fun CustomCalendarView(
    viewModel: CalendarViewModel = hiltViewModel(),
) {
    val calendarView = Calendar.getInstance()
    var selectedDate by remember { mutableStateOf(calendarView) }

    AndroidView(
        factory = { context ->
            CalendarView(context).apply {
                setOnDateChangeListener { _, year, month, dayOfMonth ->
                    selectedDate.set(year, month, dayOfMonth)

                    val formattedDate = formatDate(dayOfMonth, month, year)
                    val formattedDateOnlyDay = formatDateOnlyDay(dayOfMonth, month, year)

                    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                    val dateString = dateFormat.format(selectedDate.time)

                    viewModel.setDateSelected(formattedDate)
                    viewModel.setDateSelectedOnlyDay(formattedDateOnlyDay)
                    viewModel.setCurrentDate(dateString)

                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventItem(
    event: Event,
    modifier: Modifier = Modifier,
    navController: NavController,
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.event_description_format,
                            event.name
                        ),
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        text = stringResource(id = R.string.event_date_format, event.date),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        text = stringResource(id = R.string.event_hour_format, event.hour),
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                BadgedBox(
                    badge = {
                        Badge {
                            Text(
                                text =
                                event.assistantCount.toString(),
                            )
                        }
                    },
                    modifier = Modifier.padding(horizontal = 6.dp)
                ) {
                    IconButton(modifier = Modifier, onClick = {
                        val screen = Screen.AssistantScreen(event.id.toString())
                        navController.navigate(screen.createRoute())
                    }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Filled.CoPresent,
                            contentDescription = "bottomNavigationItem.title"
                        )
                    }
                }
            }
        }
    }
}

fun formatDate(day: Int, month: Int, year: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)

    val format = SimpleDateFormat("dd 'de' MMMM", Locale("es", "ES"))
    return format.format(calendar.time)
}

fun formatDateOnlyDay(day: Int, month: Int, year: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)

    val format = SimpleDateFormat("EEEE", Locale("es", "ES"))
    return format.format(calendar.time)
}