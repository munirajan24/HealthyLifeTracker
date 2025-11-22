package com.dreavee.healthylifetracker.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dreavee.healthylifetracker.data.database.entities.WorkoutLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutLogDao {
    @Insert
    suspend fun insertWorkout(workout: WorkoutLogEntity)

    @Query("SELECT * FROM workout_logs WHERE startTime >= :start AND startTime <= :end ORDER BY startTime DESC")
    fun getWorkoutsForDay(start: Long, end: Long): Flow<List<WorkoutLogEntity>>

    @Query("SELECT * FROM workout_logs ORDER BY startTime DESC LIMIT 1")
    suspend fun getLastWorkout(): WorkoutLogEntity?
}
