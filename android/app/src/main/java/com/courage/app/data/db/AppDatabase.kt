package com.courage.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.courage.app.data.db.dao.DecisionDao
import com.courage.app.data.db.dao.ScenarioDao
import com.courage.app.data.db.entities.DecisionEntity
import com.courage.app.data.db.entities.ScenarioEntity

@Database(
    entities = [
        ScenarioEntity::class,
        DecisionEntity::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scenarioDao(): ScenarioDao
    abstract fun decisionDao(): DecisionDao
}


