package com.unalminas.eventsapp.presentation.screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.ui.TopBar_Title

@Composable
fun SettingsScreen(
    modifier : Modifier = Modifier,
    navController: NavHostController,
) {
    Box(
        modifier = modifier
    ) {
        TopBar_Title(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.settings),
            showBackButton = true,
            onBackButtonClick = {
                navController.navigate(Screen.HomeScreen.EventsRoute.route)
            }
        )
    }
}
