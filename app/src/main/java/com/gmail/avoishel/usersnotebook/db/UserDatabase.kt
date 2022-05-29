package com.gmail.avoishel.usersnotebook.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gmail.avoishel.usersnotebook.models.UserModel
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@Database(
    entities = [UserModel::class],
    version = 1
)
abstract class UserDatabase  : RoomDatabase() {

    abstract fun getUserDao(): UserDao

//    companion object {
//        @Volatile
//        private var instance: UserDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: providerUserDatabase(context).also { instance = it }
//        }
//
//        private fun providerUserDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                UserDatabase::class.java,
//                "user_db.db"
//            ).build()
//    }
}