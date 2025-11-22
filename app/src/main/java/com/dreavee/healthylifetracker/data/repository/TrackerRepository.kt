package com.dreavee.healthylifetracker.data.repository

import com.dreavee.healthylifetracker.data.database.dao.MealDao
import com.dreavee.healthylifetracker.data.database.dao.WaterLogDao
import com.dreavee.healthylifetracker.data.database.dao.WorkoutLogDao
import com.dreavee.healthylifetracker.data.database.entities.MealEntity
import com.dreavee.healthylifetracker.data.database.entities.WaterLogEntity
import com.dreavee.healthylifetracker.data.database.entities.WorkoutLogEntity
import kotlinx.coroutines.flow.Flow


class TrackerRepository(
    private val mealDao: MealDao,
    private val waterLogDao: WaterLogDao,
    private val workoutLogDao: WorkoutLogDao
) {
    // Meals
    suspend fun logMeal(meal: MealEntity) = mealDao.insertMeal(meal)
    fun getMealsForDay(start: Long, end: Long): Flow<List<MealEntity>> = mealDao.getMealsForDay(start, end)
    suspend fun getLastMeal(): MealEntity? = mealDao.getLastMeal()

    // Water
    suspend fun logWater(log: WaterLogEntity) = waterLogDao.insertWaterLog(log)
    fun getWaterLogsForDay(start: Long, end: Long): Flow<List<WaterLogEntity>> = waterLogDao.getWaterLogsForDay(start, end)
    fun getTotalWaterForDay(start: Long, end: Long): Flow<Int?> = waterLogDao.getTotalWaterForDay(start, end)
    suspend fun getLastWaterLog(): WaterLogEntity? = waterLogDao.getLastWaterLog()

    // Workout
    suspend fun logWorkout(workout: WorkoutLogEntity) = workoutLogDao.insertWorkout(workout)
    fun getWorkoutsForDay(start: Long, end: Long): Flow<List<WorkoutLogEntity>> = workoutLogDao.getWorkoutsForDay(start, end)
    suspend fun getLastWorkout(): WorkoutLogEntity? = workoutLogDao.getLastWorkout()
}
