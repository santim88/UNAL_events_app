package com.unalminas.eventsapp.presentation

sealed class Screen(val route: String) {
    object MainScreen : Screen("MainScreen")
    object CreateEventScreen : Screen("CreateEventScreen")
    data class AssistantScreen(val id: String) : Screen("AssistantScreen/{id}") {
        fun createRoute() = "AssistantScreen/$id"
    }

    data class CreateAssistantScreen(val eventId: String) : Screen("AssistantCreate/{eventId}") {
        fun createRoute() = "AssistantCreate/$eventId"
    }

    data class EditEventScreen(val id: String) : Screen("Edit/{id}") {
        fun createRoute() = "Edit/$id"
    }

    data class EditAssistantScreen(val id: String) : Screen("AssistantEdit/{id}") {
        fun createRoute() = "AssistantEdit/$id"
    }
}
