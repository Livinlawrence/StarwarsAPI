package com.livin.starwars.presentation.people_details

import com.livin.starwars.domain.entity.PeopleDetails

data class PeopleDetailsState(
    val peopleDetails: PeopleDetails? = null,
    val isLoading: Boolean = false
)
