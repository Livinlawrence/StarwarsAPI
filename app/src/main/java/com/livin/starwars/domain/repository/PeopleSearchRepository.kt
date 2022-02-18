package com.livin.starwars.domain.repository

import com.livin.starwars.common.Resource
import com.livin.starwars.domain.entity.People
import kotlinx.coroutines.flow.Flow

interface PeopleSearchRepository {

    fun peopleSearch(word: String): Flow<Resource<List<People>>>

}