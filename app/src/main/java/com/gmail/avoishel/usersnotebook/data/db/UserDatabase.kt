package com.gmail.avoishel.usersnotebook.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.avoishel.usersnotebook.di.ApplicationScope
import com.gmail.avoishel.usersnotebook.data.models.UserModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider


@Database(
    entities = [UserModel::class],
    version = 1
)
abstract class UserDatabase  : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    class Callback @Inject constructor(
        private val database: Provider<UserDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()

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