package com.livin.starwars.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.livin.starwars.domain.entity.People

@Entity
data class PeopleEntity(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val peopleId: String
) {

    fun toPeople(): People {
        return People(
            name = name,
            peopleId = peopleId
        )
    }
}