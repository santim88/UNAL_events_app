package com.unalminas.eventsapp.presentation.screens.assistants.adapter

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.presentation.Screen

@Composable
fun BottomFloatingDropMenu(
    modifier: Modifier,
    isMenuExpanded: Boolean,
    onMenuExpandedChanged: (Boolean) -> Unit,
    menuItems: List<String>,
    navController: NavHostController,
    eventId: Int?
) {

    Box(
        modifier = modifier
    ) {
        FloatingActionButton(
            onClick = {
                onMenuExpandedChanged(!isMenuExpanded)
            },
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }

        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { onMenuExpandedChanged(false) },
            modifier = Modifier
                .padding(16.dp)
        ) {
            menuItems.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = "$item") },
                    onClick = {
                        onMenuExpandedChanged(false)

                        if (item == menuItems[0]) {
                            val screen = Screen.CreateAssistantScreen(eventId.toString())
                            navController.navigate(screen.createRoute())
                        }

                        if (item == menuItems[1]) {
                            val screen = Screen.CreateAssistantPdf417(eventId.toString())
                            navController.navigate(screen.createRoute())
                        }
                    }
                )
            }
        }
    }
}