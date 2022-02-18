package com.livin.starwars.presentation.people_search

import com.livin.starwars.domain.entity.People

data class PeopleSearchState(
     val data: List<People> = emptyList(),
    val isLoading: Boolean = false
)
