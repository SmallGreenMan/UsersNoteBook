package com.gmail.avoishel.usersnotebook.di

import android.app.Application
import androidx.room.Room
import com.gmail.avoishel.usersnotebook.data.db.UserDao
import com.gmail.avoishel.usersnotebook.data.db.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
//    fun provideDatabase(application: Application, callback: UserDatabase.Callback): UserDatabase {
    fun provideDatabase(application: Application, callback: UserDatabase.Callback): UserDatabase {
        return Room.databaseBuilder(
                application,
                UserDatabase::class.java,
                "user_db"
            )
            .addCallback(callback)
            .build()
//        Room.databaseBuilder(application, ArticleDatabase::class.java, "news_database")
//            .fallbackToDestructiveMigration()
//            .addCallback(callback)
//            .build()
    }

    @Provides
    fun provideUserDao(db: UserDatabase): UserDao {
        return db.getUserDao()
    }

//    @Database(
//        entities = [UserModel::class],
//        version = 1
//    )
//    abstract class UserDatabase  : RoomDatabase() {
//
//        abstract fun getUserDao(): UserDao
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