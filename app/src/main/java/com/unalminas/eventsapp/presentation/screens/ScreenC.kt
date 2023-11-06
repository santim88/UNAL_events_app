package com.unalminas.eventsapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.R

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
