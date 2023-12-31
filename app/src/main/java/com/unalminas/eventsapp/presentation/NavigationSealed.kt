package com.unalminas.eventsapp.presentation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("SplashScreen")
    object CreateEventScreen : Screen("CreateEventScreen")
    sealed class HomeScreen(subRoute: String) : Screen("HomeScreen/${subRoute}") {
        object TemplateRoute : HomeScreen("{sub_section}")
        object MainScreen : HomeScreen("MainScreen")
        object SettingsScreen : HomeScreen("SettingsScreen")
        object CalendarScreen : HomeScreen("CalendarScreen")
    }
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
    data class CreateAssistantCameraScreen(val id: String) : Screen("AssistantCamera/{id}") {
        fun createRoute() = "AssistantCamera/$id"
    }
    data class CreateAssistantPdf417(val id: String) : Screen("AssistantPdf417/{id}") {
        fun createRoute() = "AssistantPdf417/$id"
    }
}
