package com.unalminas.eventsapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.Assistant
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun AssistantTable(
    eventListState: List<Assistant>
) {
    /*   val eventListState = listOf<Assistant>(
           Assistant(name = "santiago", identification = "20300303", email = "santiag@gmail.com"),
           Assistant(name = "santiago", identification = "20300303", email = "santiag@gmail.com"),
           Assistant(name = "santiago", identification = "20300303", email = "santiag@gmail.com"),
           Assistant(name = "sofia", identification = "20300303", email = "arleth@gmail.com"),
           Assistant(name = "sofia", identification = "20300303", email = "arleth@gmail.com"),
           Assistant(name = "sofia", identification = "20300303", email = "arleth@gmail.com"),
       )*/
    var expanded by remember { mutableStateOf(false) }
    LazyColumn(
        modifier = Modifier.clip(RoundedCornerShape(8.dp))
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray)
                    .padding(12.dp),
                //* .height(50.dp),*//*
                horizontalArrangement = Arrangement.SpaceBetween
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
                Spacer(modifier = Modifier.weight(0.2f))
            }
        }

        itemsIndexed(items = eventListState) { index, item ->

            Row(
                modifier = Modifier
                    .padding(11.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "${index}.${item.name ?: ""}", modifier = Modifier.weight(1f))
                Text(text = item.identification ?: "", modifier = Modifier.weight(1f))
                Text(text = item.email ?: "", modifier = Modifier.weight(1f))
                IconButton(modifier = Modifier.weight(0.2f), onClick = { expanded = true }) {
                    Image(
                        painterResource(R.drawable.baseline_edit_24),
                        contentDescription = "like",
                        modifier = Modifier
                            .size(20.dp)
                    )
                }

            }
        }
        item {
            TaskMenu(
                expanded = expanded,
                onEditClick = { /* Handle Edit Click */ },
                onDeleteClick = { /* Handle Delete Click */ },
                onDismiss = { expanded = false }
            )
        }
    }
}

@Composable
fun TaskMenu(
    expanded: Boolean,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onDismiss: () -> Unit
) {

    val options = listOf( // (2)
        "edit"
        /*        "delete"*/
    )

    DropdownMenu( // (3)
        expanded = expanded,
        onDismissRequest = onDismiss
    ) {
        options.forEach { option ->
            DropdownMenuItem( // (4)
                text = { Text(text = option) },
                onClick = {
                    when (option) {
                        "edit" -> onEditClick()
                        "delete" -> onDeleteClick()
                    }
                    onDismiss()
                }
            )
        }
    }
}
