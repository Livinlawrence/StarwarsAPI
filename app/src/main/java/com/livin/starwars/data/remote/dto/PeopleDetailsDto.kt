package com.livin.starwars.data.remote.dto

import com.livin.starwars.domain.entity.Film
import com.livin.starwars.domain.entity.PeopleDetails

data class PeopleDetailsDto(
    val birth_year: String,
    val created: String,
    val edited: String,
    val eye_color: String,
    val films: List<String>,
    val gender: String,
    val hair_color: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    val name: String,
    val skin_color: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
    val vehicles: List<String>
){

    fun toPeopleDetails(homeworld: String, films: MutableList<Film>, languages: MutableList<String>): PeopleDetails{
        return PeopleDetails(
            name = name,
            height = height,
            birthYear = birth_year,
            homeworld = homeworld,
            films = films,
            languages = languages
        )
    }
}