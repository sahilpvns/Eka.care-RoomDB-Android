package com.sahilpvns.ekacare

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserInfoDao {
    @Insert
    suspend fun insert(user: UserInfo)

    @Query("SELECT * FROM user_info_table ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<UserInfo>>
}
