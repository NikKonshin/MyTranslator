package com.nikitakonshin.model.room

import androidx.room.*

@Dao
interface HistoryDao {

    @Query("SELECT * FROM HistoryEntity")
    suspend fun getAll(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE word LIKE :word ")
    suspend fun getDataByWord(word: String): HistoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: HistoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<HistoryEntity>)

    @Delete
    suspend fun delete(entity: HistoryEntity)
}