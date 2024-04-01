package com.vlad.bikegarage.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.vlad.bikegarage.bikes.data.local.dao.BikeDao
import com.vlad.bikegarage.bikes.data.local.db.BikesDatabase
import com.vlad.bikegarage.bikes.data.repo.BikesRepositoryImpl
import com.vlad.bikegarage.bikes.domain.BikeRepository
import com.vlad.bikegarage.bikes.domain.use_case.ValidateBikeName
import com.vlad.bikegarage.rides.data.local.dao.RideDao
import com.vlad.bikegarage.rides.data.repository.RidesRepositoryImpl
import com.vlad.bikegarage.rides.domain.RideRepository
import com.vlad.bikegarage.settings.data.UserPreferences
import com.vlad.bikegarage.settings.domain.Preferences
import com.vlad.bikegarage.settings.domain.use_vase.FilterOutDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        app: Application
    ): SharedPreferences {
        return app.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences {
        return UserPreferences(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideFilterDigitsUseCase(): FilterOutDigits {
        return FilterOutDigits()
    }

    @Provides
    @Singleton
    fun provideValidateBikeName(): ValidateBikeName {
        return ValidateBikeName()
    }



    @Provides
    @Singleton
    fun provideBikesDatabase(@ApplicationContext context: Context): BikesDatabase {
        return Room.databaseBuilder(
            context,
            BikesDatabase::class.java,
            "bikes_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBikeRepository(bikeDao: BikeDao): BikeRepository {
        return BikesRepositoryImpl(bikeDao)
    }

    @Provides
    @Singleton
    fun provideRideRepository(rideDao: RideDao): RideRepository {
        return RidesRepositoryImpl(rideDao)
    }

    @Provides
    @Singleton
    fun provideRidesDao(database: BikesDatabase): RideDao {
        return database.rideDao
    }

    @Provides
    @Singleton
    fun provideBikesDao(database: BikesDatabase): BikeDao {
        return database.bikeDao
    }
}