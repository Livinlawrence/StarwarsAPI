package com.livin.starwars.di

import com.livin.starwars.data.local.StarWarsAppDatabase
import com.livin.starwars.data.remote.StarWarsApi
import com.livin.starwars.data.repository.PeopleDetailsRepositoryImpl
import com.livin.starwars.data.repository.PeopleSearchRepositoryImpl
import com.livin.starwars.domain.repository.PeopleDetailsRepository
import com.livin.starwars.domain.repository.PeopleSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun providePeopleSearchRepository(
        db: StarWarsAppDatabase,
        api: StarWarsApi
    ): PeopleSearchRepository {
        return PeopleSearchRepositoryImpl(api, db.peopleDao)
    }

    @Provides
    @Singleton
    fun providePeopleDetailsRepository(
        api: StarWarsApi
    ): PeopleDetailsRepository {
        return PeopleDetailsRepositoryImpl(api)
    }
}