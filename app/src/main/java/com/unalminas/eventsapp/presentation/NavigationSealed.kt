package com.unalminas.eventsapp.presentation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("SplashScreen")
    object CreateEventScreen : Screen("CreateEventScreen")
    data class CreateEventScreenWithDate(val date: String) :
        Screen("CreateEventScreenWithDate/{date}") {
        fun createRoute() = "CreateEventScreenWithDate/$date"
    }

    sealed class HomeScreen(subRoute: String) : Screen("HomeScreen/${subRoute}") {
        object TemplateRoute : HomeScreen("{sub_section}")
        object EventsRoute : HomeScreen("MainScreen")
        object SettingsScreen : HomeScreen("SettingsScreen")
        object CalendarScreen : HomeScreen("CalendarScreen")
    }

    data class AttendantScreen(val id: String) : Screen("AttendantScreen/{id}") {
        fun createRoute() = "AttendantScreen/$id"
    }

    data class CreateAttendantScreen(val eventId: String) : Screen("AttendantCreate/{eventId}") {
        fun createRoute() = "AttendantCreate/$eventId"
    }

    data class EditEventScreen(val id: String) : Screen("Edit/{id}") {
        fun createRoute() = "Edit/$id"
    }

    data class EditAttendantScreen(val id: String) : Screen("AttendantEdit/{id}") {
        fun createRoute() = "AttendantEdit/$id"
    }

    data class CreateAttendantCameraScreen(val id: String) : Screen("AttendantCamera/{id}") {
        fun createRoute() = "AttendantCamera/$id"
    }

    data class CreateAttendantPdf417(val id: String) : Screen("AttendantPdf417/{id}") {
        fun createRoute() = "AttendantPdf417/$id"
    }
}
