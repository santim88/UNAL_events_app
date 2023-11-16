package com.unalminas.eventsapp.presentation

sealed class Screen(val route: String) {
    object MainScreen : Screen("MainScreen")
    object CreateEventScreen : Screen("CreateEventScreen")
    object ScreenC : Screen("C")
    data class EditEventScreen(val id: String) : Screen("Edit/{id}") {
         fun createRoute() = "Edit/$id"
    }
}
