package com.livin.starwars.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.livin.starwars.data.repository.FakePeopleSearchRepository
import com.livin.starwars.domain.entity.People
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PeopleSearchUseCaseTest {
    private lateinit var peopleSearchUseCase: PeopleSearchUseCase
    private lateinit var fakeRepository: FakePeopleSearchRepository

    @Before
    fun setUp() {
        fakeRepository = FakePeopleSearchRepository()
        peopleSearchUseCase = PeopleSearchUseCase(fakeRepository)

        val peopleToInsert = mutableListOf<People>()
        ('a'..'z').forEachIndexed { index, c ->
            peopleToInsert.add(
                People(
                    name = c.toString(),
                    peopleId = c.toString()
                )
            )
        }
        peopleToInsert.shuffle()
        runBlocking {
            peopleToInsert.forEach { fakeRepository.insertPeople(it) }
        }
    }

    @Test
    fun `match_total_count`() = runBlocking {
        val people = peopleSearchUseCase("a").first().data?: mutableListOf()
        assertThat(people.size).isEqualTo(1)
    }

    @Test
    fun `search_people by search_key_word`()  = runBlocking {
        val people = peopleSearchUseCase("a").first().data?: mutableListOf()
        assertThat(people[0].name).startsWith("a")
    }
}