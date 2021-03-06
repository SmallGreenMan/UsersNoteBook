package com.gmail.avoishel.usersnotebook.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gmail.avoishel.usersnotebook.models.UserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserModel): Long

    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<UserModel>>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun findUserById(userId: Int): LiveData<List<UserModel>>

    @Delete
    suspend fun deleteUser(user: UserModel)
}