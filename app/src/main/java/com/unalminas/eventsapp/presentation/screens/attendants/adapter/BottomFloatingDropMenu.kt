package com.unalminas.eventsapp.presentation.screens.attendants.adapter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.ui.theme.BlueGreen
import com.unalminas.eventsapp.presentation.ui.theme.NunitoFont
import com.unalminas.eventsapp.presentation.ui.theme.OxfordBlue
import com.unalminas.eventsapp.presentation.ui.theme.Snow

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
            containerColor = BlueGreen,
            contentColor = Snow
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
                    text = { Text(text = "$item", fontFamily = NunitoFont) },
                    onClick = {
                        onMenuExpandedChanged(false)

                        if (item == menuItems[0]) {
                            val screen = Screen.CreateAttendantScreen(eventId.toString())
                            navController.navigate(screen.createRoute())
                        }

                        if (item == menuItems[1]) {
                            val screen = Screen.CreateAttendantPdf417(eventId.toString())
                            navController.navigate(screen.createRoute())
                        }
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = OxfordBlue
                    )
                )
            }
        }
    }
}
