package com.example.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.R
import com.example.pokedex.data.EventDataSource
import com.example.pokedex.data.EventDataSourceImpl
import com.example.pokedex.domain.Event


class MainActivity : ComponentActivity() {

    private val evenViewModel by viewModels<EventViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        evenViewModel.initDependencies(this)
        setContent {
            LaunchedEffect(Unit) {
                evenViewModel.getEventList()
            }

            val eventDataSource = EventDataSourceImpl(applicationContext)
            val eventListState by evenViewModel.eventListState.collectAsState()
            val navController = rememberNavController()
            Nav(eventListState, eventDataSource, navController)
            /*            MainActivityContent(eventListState, eventDataSource)*/
        }
    }
}

//////////////////////////////PersonalCode///////////////////////////////////////////////////////

@Composable
fun ScreenB(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Screen B click to pass C", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(45.dp))
        Button(onClick = {
            navController.navigate("A") {
                popUpTo("A") { inclusive = true }
            }
        }) {
            Text(text = "Go to screen C", fontSize = 40.sp)
        }

    }
}

/*@Composable
fun ScreenC(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Screen C click to pass C", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(45.dp))
        Button(onClick = {
            navController.navigate("A") {
                popUpTo("A") { inclusive = true }
            }
        }) {
            Text(text = "Go to screen C", fontSize = 40.sp)
        }

    }
}*/

/*@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    val sampleEvents = listOf(
        Event(
            id = 1,
            name = "Sample Event 1",
            description = "Description 1",
            place = "Place 1",
            date = "Date 1",
            hour = "Hour 1"
        ),
        Event(
            id = 2,
            name = "Sample Event 2",
            description = "Description 2",
            place = "Place 2",
            date = "Date 2",
            hour = "Hour 2"
        )
    )
    MainActivityContent(sampleEvents, EventDataSource)
}*/
@Composable
fun Nav(
    data: List<Event>,
    eventDataSource: EventDataSource,
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = "A") {
        composable("A") {
            ScreenMain(data, eventDataSource, navController)
        }
        composable("B") {
            ScreenB(navController)
        }
        composable("C") {
            ScreenC(navController)
        }
    }
}

@Composable
fun ScreenMain(
    data: List<Event>,
    eventDataSource: EventDataSource,
    navController: NavHostController
) {

    MainActivityContent(data, eventDataSource, navController)
}

@Composable
fun MainActivityContent(
    data: List<Event>,
    eventDataSource: EventDataSource,
    navController: NavHostController
) {
    /*    val eventRepository = EventRepository()*/
    /*    val getAllData = eventRepository.getAllData()*/
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(items = data) { event ->
                CardEvent(event = event, onClick = {
                    navController.navigate("C")
                })
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            onClick = {
                navController.navigate("B") {
                    popUpTo("B") { inclusive = true }
                }
            },
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
//        Button(
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.BottomEnd),
//            onClick = {
//                val event = Event(
//                    id = 100,
//                    name = "Event 1",
//                    description = "Event description",
//                    place = "M2",
//                    date = "03/02/2023",
//                    hour = "12:40"
//                )
//                eventDataSource.saveEvent(event)
//            }) {
//            Text("Save Event")
//        }
    }
}

@Composable
fun SaveEventButton(eventDataSource: EventDataSource) {
    Button(onClick = {
        val event = Event(
            id = 100,
            name = "Event 1",
            description = "Event description",
            place = "M2",
            date = "03/02/2023",
            hour = "12:40"
        )
        eventDataSource.saveEvent(event)
    }) {
        Text("Save Event")
    }
}

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
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
//                horizontalArrangement = Arrangement.End,
                ) {
                    IconButton(modifier = Modifier, onClick = {}) {
                        Image(
                            painterResource(R.drawable.baseline_edit_24),
                            contentDescription = "like",
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    IconButton(modifier = Modifier, onClick = { }) {
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
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
            ) {
                Text(text = "TOMAR ASISTENCIA", fontSize = 8.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenC(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Screen C") },
                actions = {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(onClick = {
                            navController.navigate("A") {
                                popUpTo("A") { inclusive = true }
                            }
                        }) {
                            Text(text = "volver", fontSize = 14.sp)
                        }
                        Text(
                            text = "Lista de Asistentes",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )

                        Spacer(modifier = Modifier.width(75.dp))
                    }
                }
            )
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.LightGray)
                        .padding(16.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Evento A",
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "Fecha: 16/09/2022",
                            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                        )
                        Text(
                            text = "Hora: 2-4 PM",
                            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(45.dp))
                Text(
                    text = "Asistentes: 45",
                    style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                )

                Spacer(modifier = Modifier.height(45.dp))

                LazyColumn(
                    modifier= Modifier.clip(RoundedCornerShape(8.dp))
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.LightGray)
                                .padding(12.dp),
                            /* .height(50.dp),*/
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = "Name",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "Identifier",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "Email",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    val list = listOf(
                        mapOf(
                            "name" to "John Doe",
                            "identifier" to "1234",
                            "email" to "johndoe@example.com"
                        ),
                        mapOf(
                            "name" to "Jane Doe",
                            "identifier" to "5678",
                            "email" to "janedoe@example.com"
                        ),
                        mapOf(
                            "name" to "Jane Doe",
                            "identifier" to "5678",
                            "email" to "janedoe@example.com"
                        ),
                        mapOf(
                            "name" to "Jane Doe",
                            "identifier" to "5678",
                            "email" to "janedoe@example.com"
                        ),
                        mapOf(
                            "name" to "Jane Doe",
                            "identifier" to "5678",
                            "email" to "janedoe@example.com"
                        ),
                        mapOf(
                            "name" to "John Doe",
                            "identifier" to "1234",
                            "email" to "johndoe@example.com"
                        ),
                        mapOf(
                            "name" to "Jane Doe",
                            "identifier" to "5678",
                            "email" to "janedoe@example.com"
                        ),
                        mapOf(
                            "name" to "Jane Doe",
                            "identifier" to "5678",
                            "email" to "janedoe@example.com"
                        ),
                        mapOf(
                            "name" to "Jane Doe",
                            "identifier" to "5678",
                            "email" to "janedoe@example.com"
                        ),
                        mapOf(
                            "name" to "Jane Doe",
                            "identifier" to "5678",
                            "email" to "janedoe@example.com"
                        )
                    )
                    items(list) { item ->
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(text = item["name"] ?: "", modifier = Modifier.weight(1f))
                            Text(text = item["identifier"] ?: "", modifier = Modifier.weight(1f))
                            Text(text = item["email"] ?: "", modifier = Modifier.weight(1f))
                            IconButton(modifier = Modifier, onClick = {}) {
                                Image(
                                    painterResource(R.drawable.baseline_edit_24),
                                    contentDescription = "like",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp),
                onClick = {
                    navController.navigate("B") {
                        popUpTo("B") { inclusive = true }
                    }
                },
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    }
}

