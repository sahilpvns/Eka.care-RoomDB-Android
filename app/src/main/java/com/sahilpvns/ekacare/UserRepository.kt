package com.sahilpvns.ekacare

import androidx.lifecycle.LiveData

class UserRepository(private val userInfoDao: UserInfoDao) {
    val allUsers: LiveData<List<UserInfo>> = userInfoDao.getAllUsers()
    suspend fun insert(user: UserInfo) {
        userInfoDao.insert(user)
    }
}