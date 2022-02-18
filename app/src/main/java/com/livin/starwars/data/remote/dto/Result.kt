package com.livin.starwars.data.remote.dto

import com.livin.starwars.common.getIdFromUrl
import com.livin.starwars.data.local.entity.PeopleEntity

data class Result(
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
) {
    fun toPeopleEntity(): PeopleEntity {
        return PeopleEntity(
            name = name,
            peopleId = url.getIdFromUrl()
        )
    }
}