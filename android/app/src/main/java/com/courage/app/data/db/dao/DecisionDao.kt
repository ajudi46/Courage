package com.courage.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.courage.app.data.db.entities.DecisionEntity

@Dao
interface DecisionDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: DecisionEntity): Long

    @Query("SELECT * FROM decisions ORDER BY createdAtEpochMs DESC LIMIT :limit")
    suspend fun getRecent(limit: Int): List<DecisionEntity>

    @Query(
        """
        SELECT * FROM decisions
        WHERE createdAtEpochMs >= :startInclusiveEpochMs AND createdAtEpochMs < :endExclusiveEpochMs
        ORDER BY createdAtEpochMs ASC
        """,
    )
    suspend fun getBetween(
        startInclusiveEpochMs: Long,
        endExclusiveEpochMs: Long,
    ): List<DecisionEntity>

    @Query("SELECT * FROM decisions WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): DecisionEntity?
}


