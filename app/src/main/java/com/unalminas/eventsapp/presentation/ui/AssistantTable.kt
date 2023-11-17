package com.unalminas.eventsapp.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.unalminas.eventsapp.domain.Assistant
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext

@Composable
fun AssistantTable(
    eventListState: List<Assistant>
) {
    val eventListState = listOf<Assistant>(
        Assistant(name = "santiago", identification = "20300303", email = "santiag@gmail.com"),
        Assistant(name = "santiago", identification = "20300303", email = "santiag@gmail.com"),
        Assistant(name = "santiago", identification = "20300303", email = "santiag@gmail.com"),
        Assistant(name = "sofia", identification = "20300303", email = "arleth@gmail.com"),
        Assistant(name = "sofia", identification = "20300303", email = "arleth@gmail.com"),
        Assistant(name = "sofia", identification = "20300303", email = "arleth@gmail.com"),
    )
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
            val context = LocalContext.current
            var expanded by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier
                    .padding(11.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "${index}.${item.name ?: ""}", modifier = Modifier.weight(1f))
                Text(text = item.identification ?: "", modifier = Modifier.weight(1f))
                Text(text = item.email ?: "", modifier = Modifier.weight(1f))
            }
        }
    }

    @Composable
    fun Demo_DropDownMenu() {
        val context = LocalContext.current
        var expanded by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxWidth(0.2f)
                .wrapContentSize(Alignment.TopEnd)
        ) {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More"
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Edit") },
                    onClick = { Toast.makeText(context, "Load", Toast.LENGTH_SHORT).show() }
                )
                DropdownMenuItem(
                    text = { Text("Delete") },
                    onClick = { Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show() }
                )
            }
        }
    }
}