package com.starsolns.pets.views.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.starsolns.pets.views.model.Pet
import retrofit2.http.GET

@Dao
interface PetDao {

    @Insert
    suspend fun insert(vararg pet: Pet) : List<Long>

    @Query("SELECT * FROM pets_table WHERE id LIKE :petId")
    suspend fun getPet(petId: Int): Pet

    @Query("DELETE FROM pets_table")
    suspend fun delete()
}