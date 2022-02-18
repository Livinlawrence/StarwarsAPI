package com.livin.starwars.domain.repository

import com.livin.starwars.common.Resource
import com.livin.starwars.domain.entity.PeopleDetails
import kotlinx.coroutines.flow.Flow

interface PeopleDetailsRepository {

    fun getPeopleDetails(peopleId: String): Flow<Resource<PeopleDetails>>

}