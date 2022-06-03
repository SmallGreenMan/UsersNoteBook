package com.gmail.avoishel.usersnotebook.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }
//
//    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
//    @Provides
//    fun provideUserDatabase(
//        @ApplicationContext app: Context
//        ) = Room.databaseBuilder(
//            app,
//            UserDatabase::class.java,
//            "user_db.db "
//        ).build() // The reason we can construct a database for the repo
//
//    @Singleton
//    @Provides
//    fun getUserDao(db: UserDatabase) = db.getUserDao()



//    @Provides
//    @Singleton
//    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
//        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    }
//
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(client: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideNewsApi(retrofit: Retrofit): NewsApi {
//        return retrofit.create(NewsApi::class.java)
//    }
}


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope