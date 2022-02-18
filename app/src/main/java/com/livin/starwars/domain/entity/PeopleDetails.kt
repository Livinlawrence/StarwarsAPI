package com.livin.starwars.domain.entity

data class PeopleDetails(
    val name: String,
    val height: String,
    val birthYear: String,
    val homeworld: String,
    val films:List<Film>,
    val languages:List<String>
) {
}