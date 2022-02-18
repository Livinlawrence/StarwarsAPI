package com.livin.starwars.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.livin.starwars.data.local.entity.PeopleEntity

@Database(entities = [PeopleEntity::class], version = 1)
abstract class StarWarsAppDatabase : RoomDatabase() {
    abstract val peopleDao: PeopleDao
}