package com.unalminas.eventsapp.presentation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.unalminas.eventsapp.presentation.myComposables.ScaffoldMainScreen
import com.unalminas.eventsapp.presentation.screens.Attendants.AttendantScreen
import com.unalminas.eventsapp.presentation.screens.attendants.adapter.FormAttendant
import com.unalminas.eventsapp.presentation.screens.calendar.CalendarScreen
import com.unalminas.eventsapp.presentation.screens.camera.CameraXGuideTheme
import com.unalminas.eventsapp.presentation.screens.events.EventsScreens
import com.unalminas.eventsapp.presentation.screens.events.FormEventScreen
import com.unalminas.eventsapp.presentation.screens.scanPdf417.MainScreenPdf417
import com.unalminas.eventsapp.presentation.screens.settings.SettingsScreen
import com.unalminas.eventsapp.presentation.screens.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {

        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
//            LaunchedEffect(Unit) {
//                delay(100)
//                navController.navigate(Screen.HomeScreen.EventsRoute.route)
//            }
        }

        composable(
            Screen.CreateEventScreen.route,
            enterTransition = {
                slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight }, animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight }, animationSpec = tween(500)
                )
            },

            ) {
            FormEventScreen(navController = navController, isNewEvent = true)
        }

        composable(Screen.CreateEventScreenWithDate("{date}").route, enterTransition = {
            slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight }, animationSpec = tween(500)
            )
        }, exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight }, animationSpec = tween(500)
            )
        },

            arguments = listOf(navArgument("date") { type = NavType.StringType })
        ) { entry ->
            val date = entry.arguments?.getString("date")
            FormEventScreen(
                navController = navController, isNewEvent = true, dateEvent = date
            )
        }

        composable(Screen.CreateAttendantScreen("{eventId}").route, enterTransition = {
            slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight }, animationSpec = tween(500)
            )
        }, exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight }, animationSpec = tween(500)
            )
        }, arguments = listOf(navArgument("eventId") { type = NavType.StringType })
        ) { entry ->
            val eventId = entry.arguments?.getString("eventId")?.toInt()
            FormAttendant(
                navController = navController, id = null, isNewAttendant = true, eventId = eventId
            )
        }

        composable(
            Screen.AttendantScreen("{id}").route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("id")?.toInt()
            AttendantScreen(
                navController = navController, id = id
            )
        }

        composable(Screen.EditEventScreen("{id}").route, enterTransition = {
            slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight }, animationSpec = tween(500)
            )
        }, exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight }, animationSpec = tween(500)
            )
        }, arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("id")?.toInt()
            FormEventScreen(
                navController = navController, id = id, isNewEvent = false
            )
        }

        composable(Screen.EditAttendantScreen("{id}").route, enterTransition = {
            slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight }, animationSpec = tween(500)
            )
        }, exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight }, animationSpec = tween(500)
            )
        }, arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("id")?.toInt()
            FormAttendant(
                navController = navController, id = id, isNewAttendant = false, eventId = null
            )
        }

        composable(
            Screen.CreateAttendantCameraScreen("{id}").route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("id")?.toInt() ?: -1
            CameraXGuideTheme(
                navController = navController,
                eventId = id,
            )
        }

        composable(
            Screen.CreateAttendantPdf417("{id}").route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { entry ->
            val id = entry.arguments?.getString("id")?.toInt()
            MainScreenPdf417(
                navController = navController, eventId = id
            )
        }

        composable(
            Screen.HomeScreen.TemplateRoute.route,
            arguments = listOf(navArgument("sub_section") { type = NavType.StringType })
        ) {
            val bottomNavController = rememberNavController()
            HomeMainScreen(
                navController, bottomNavController
            )
        }
    }
}

@Composable
fun HomeMainScreen(
    navController: NavHostController, navBottomController: NavHostController
) {
    var showFloatingButton by remember { mutableStateOf(false) }

    ScaffoldMainScreen(navController = navController,
        allowsItemBar = 1,
        showFloatingButton = showFloatingButton,
        onNavSectionSelected = { _, bottomNavigationItem ->
            navBottomController.navigate(bottomNavigationItem.route)
        }) { paddingValues ->
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navBottomController,
            startDestination = Screen.HomeScreen.EventsRoute.route
        ) {
            composable(Screen.HomeScreen.EventsRoute.route) {
                showFloatingButton = true
                EventsScreens(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    navController = navController
                )
            }

            composable(Screen.HomeScreen.SettingsScreen.route) {
                showFloatingButton = false
                SettingsScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    navController = navController
                )
            }

            composable(Screen.HomeScreen.CalendarScreen.route) {
                showFloatingButton = false
                CalendarScreen(
                    navController = navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }
        }
    }
}
