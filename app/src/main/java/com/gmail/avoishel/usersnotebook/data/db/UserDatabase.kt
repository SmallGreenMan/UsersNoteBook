package com.gmail.avoishel.usersnotebook.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.avoishel.usersnotebook.di.ApplicationScope
import com.gmail.avoishel.usersnotebook.models.UserModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider


@Database(
    entities = [UserModel::class],
    version = 3
)
abstract class UserDatabase  : RoomDatabase() {

    abstract fun getUserDao(): UserDao

//    class Callback @Inject constructor(
//        private val database: Provider<UserDatabase>,
//        @ApplicationScope private val applicationScope: CoroutineScope
//    ) : RoomDatabase.Callback()
}