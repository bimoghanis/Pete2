package com.pt2.leg5.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UlasanDao {
    @Insert
    fun insert(ulasan: UlasanEntity)

    @Query("SELECT * FROM ulasan ORDER BY id DESC")
    fun getLastBmi(): LiveData<List<UlasanEntity>>

    @Query("DELETE FROM ulasan")
    fun clearData()

    @Query("SELECT * FROM ulasan ORDER BY id DESC")
    fun getAllUlasan(): LiveData<List<UlasanEntity>>
}