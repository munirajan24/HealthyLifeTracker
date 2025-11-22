package com.dreavee.healthylifetracker.data.local.dao

import androidx.room.*
import com.dreavee.healthylifetracker.data.local.entity.UserSettings
import kotlinx.coroutines.flow.Flow

@Dao
interface UserSettingsDao {
    
    @Query("SELECT * FROM user_settings WHERE id = 1")
    fun getSettings(): Flow<UserSettings?>
    
    @Query("SELECT * FROM user_settings WHERE id = 1")
    suspend fun getSettingsOnce(): UserSettings?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSettings(settings: UserSettings)
    
    @Update
    suspend fun updateSettings(settings: UserSettings)
}
