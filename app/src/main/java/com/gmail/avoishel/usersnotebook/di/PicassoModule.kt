package com.gmail.avoishel.usersnotebook.di

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PicassoModule {
    @Provides
    fun providesPicasso(): Picasso {
        return Picasso.get()
    }
}