package com.livin.starwars.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.livin.starwars.data.local.entity.PeopleEntity

@Dao
interface PeopleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(info: List<PeopleEntity>)


    @Query("DELETE FROM peopleentity WHERE name IN(:letters)")
    suspend fun deletePeople(letters: List<String>)


    @Query("SELECT * FROM peopleentity WHERE name LIKE '%' || :key || '%'")
    suspend fun searchPeople(key: String): List<PeopleEntity>
}