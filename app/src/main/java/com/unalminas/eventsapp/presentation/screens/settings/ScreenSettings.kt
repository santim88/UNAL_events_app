package com.unalminas.eventsapp.presentation.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.myComposables.ScaffoldBarUse
import com.unalminas.eventsapp.presentation.ui.TopBar_Title

@Composable
fun SettingsScreen(
    navController: NavHostController,
) {
    ScaffoldBarUse(navController = navController, allowsItemBar = 2) {
        TopBar_Title(
            title = stringResource(id = R.string.settings),
            showBackButton = true,
            onBackButtonClick = {
                navController.navigate(Screen.MainScreen.route)
            }
        )
    }
}