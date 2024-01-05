package com.unalminas.eventsapp.presentation.screens.calendar

import android.content.Context
import android.view.LayoutInflater
import android.widget.CalendarView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.myComposables.ScaffoldBarUse
import com.unalminas.eventsapp.presentation.screens.events.EventScreenContentSimplify
import com.unalminas.eventsapp.presentation.ui.TopBar_Title

@Composable
fun CalendarScreen(
    navController: NavHostController
) {
    ScaffoldBarUse(navController = navController, isEventList = true, allowsItemBar = 0) {
        CalendarScreenContent(it)
 /*       CalendarScreenContent()*/
    }
}

@Composable
fun CalendarScreenContent(
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    val events = listOf<Event>(
        Event(name = "evebt 1", date = "16:27"),
        Event(name = "evebt 2", date = "17:30"),
        Event(name = "evebt 3", date = "23:32"),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        CustomCalendarView(context)

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Box(
                modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
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
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(events) { event ->
                EventItem(event)
            }
        }
    }
}

@Composable
fun CustomCalendarView(
    context: Context
) {
    AndroidView(
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.calendar_xml, null, false)
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun EventItem(event: Event) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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