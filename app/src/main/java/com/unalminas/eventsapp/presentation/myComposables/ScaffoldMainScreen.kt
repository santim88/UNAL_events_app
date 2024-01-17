@file:OptIn(ExperimentalMaterial3Api::class)

package com.unalminas.eventsapp.presentation.myComposables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.ListAlt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.ui.theme.LatoFont
import com.unalminas.eventsapp.presentation.ui.theme.LavenderBlush
import com.unalminas.eventsapp.presentation.ui.theme.Melon
import com.unalminas.eventsapp.presentation.ui.theme.OxfordBlue
import com.unalminas.eventsapp.presentation.ui.theme.PrussianBlue

data class BottomNavigationItem(
    val title: String,
    val route: String,
    val selectedIcon: androidx.compose.ui.graphics.vector.ImageVector,
    val unselectedIcon: androidx.compose.ui.graphics.vector.ImageVector,
    val hasNews: Boolean,
    val bagCount: Int? = null,
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScaffoldMainScreen(
    navController: NavHostController,
    showFloatingButton: Boolean = false,
    allowsItemBar: Int,
    onNavSectionSelected: (Int, BottomNavigationItem) -> Unit = { _, _ -> },
    content: @Composable (PaddingValues) -> Unit,
) {

    val navItemList = listOf(
        BottomNavigationItem(
            title = "Calendar",
            selectedIcon = Icons.Filled.DateRange,
            unselectedIcon = Icons.Outlined.DateRange,
            route = Screen.HomeScreen.CalendarScreen.route,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "List",
            selectedIcon = Icons.Filled.ListAlt,
            route = Screen.HomeScreen.EventsRoute.route,
            unselectedIcon = Icons.Outlined.ListAlt,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Settings",
            route = Screen.HomeScreen.SettingsScreen.route,
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNews = true,
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(allowsItemBar)
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .background(
                        Melon,
                        RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                    )
                    .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
            ) {
                navItemList.forEachIndexed { index, bottomNavigationItem ->
                    NavItem(
                        selectedItemIndex = selectedItemIndex,
                        index = index,
                        bottomNavigationItem = bottomNavigationItem
                    ) {
                        onNavSectionSelected(index, bottomNavigationItem)
                        selectedItemIndex = index
                    }
                }
            }
        },
        floatingActionButton = {
            if (showFloatingButton) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.CreateEventScreen.route)
                    },
                    containerColor = PrussianBlue
                ) {
                    Icon(Icons.Filled.Add, "Add Event", tint = LavenderBlush)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        content(it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.NavItem(
    selectedItemIndex: Int,
    index: Int,
    bottomNavigationItem: BottomNavigationItem,
    onItemClick: () -> Unit = {},
) {
    NavigationBarItem(
        selected = selectedItemIndex == index,
        onClick = onItemClick,
        label = {
            Text(
                text = bottomNavigationItem.title,
                fontFamily = LatoFont,
                fontWeight = FontWeight.Bold,
                color = OxfordBlue
            )
        },
        colors = NavigationBarItemDefaults.colors(
// Add after
        ),
        icon = {
            BadgedBox(
                badge = {
                    if (bottomNavigationItem.bagCount != null) {
                        Badge {
                            Text(
                                text = bottomNavigationItem.bagCount.toString(),
                                fontFamily = LatoFont
                            )
                        }
                    } else if (bottomNavigationItem.hasNews) {
                        Badge()
                    }
                }
            ) {
                Icon(
                    imageVector = if (index == selectedItemIndex) {
                        bottomNavigationItem.selectedIcon
                    } else bottomNavigationItem.unselectedIcon,
                    contentDescription = bottomNavigationItem.title
                )
            }
        }
    )
}
