package com.courage.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.courage.app.data.db.entities.ScenarioEntity

@Dao
interface ScenarioDao {
    @Query("SELECT COUNT(*) FROM scenarios")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<ScenarioEntity>)

    @Query(
        """
        SELECT * FROM scenarios
        WHERE theme != :excludeTheme
        ORDER BY RANDOM()
        LIMIT 1
        """,
    )
    suspend fun getRandomExcludingTheme(excludeTheme: String): ScenarioEntity?

    @Query(
        """
        SELECT * FROM scenarios
        WHERE context = :context AND theme != :excludeTheme
        ORDER BY RANDOM()
        LIMIT 1
        """,
    )
    suspend fun getRandomByContextExcludingTheme(context: String, excludeTheme: String): ScenarioEntity?

    @Query(
        """
        SELECT * FROM scenarios
        WHERE context = :context
        ORDER BY RANDOM()
        LIMIT 1
        """,
    )
    suspend fun getRandomByContext(context: String): ScenarioEntity?

    @Query("SELECT * FROM scenarios ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomAny(): ScenarioEntity?

    @Query("SELECT * FROM scenarios WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): ScenarioEntity?
}


