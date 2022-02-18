package com.livin.starwars.utils

sealed class Screen(val route: String) {
    object PeopleSearchScreen : Screen("people_search_screen")
    object PeopleDetailsScreen : Screen("people_details_screen")
}
