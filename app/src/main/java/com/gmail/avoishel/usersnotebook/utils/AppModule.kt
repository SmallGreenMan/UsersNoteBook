package com.gmail.avoishel.usersnotebook.utils

import android.content.Context
import androidx.room.Room
import com.gmail.avoishel.usersnotebook.db.UserDao
import com.gmail.avoishel.usersnotebook.db.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideUserDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        UserDatabase::class.java,
        "user_db.db "
    ).build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun getUserDao(db: UserDatabase) = db.getUserDao()
}