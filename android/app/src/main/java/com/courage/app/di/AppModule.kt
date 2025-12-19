package com.courage.app.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.room.Room
import com.courage.app.data.auth.AuthRepository
import com.courage.app.data.auth.FirebaseAuthRepository
import com.courage.app.data.decision.DecisionRepository
import com.courage.app.data.decision.RoomDecisionRepository
import com.courage.app.data.db.AppDatabase
import com.courage.app.data.onboarding.OnboardingRepository
import com.courage.app.data.onboarding.PreferencesOnboardingRepository
import com.courage.app.data.remote.FeedbackSessionRepository
import com.courage.app.data.remote.FirestoreFeedbackSessionRepository
import com.courage.app.data.remote.FirestoreUserProfileRemoteRepository
import com.courage.app.data.remote.UserProfileRemoteRepository
import com.courage.app.data.scenario.RoomScenarioRepository
import com.courage.app.data.scenario.ScenarioRepository
import com.courage.app.data.session.CourageRepository
import com.courage.app.data.session.PreferencesCourageRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("courage.preferences_pb") },
        )
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return FirebaseAuthRepository(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideOnboardingRepository(
        dataStore: DataStore<Preferences>,
    ): OnboardingRepository = PreferencesOnboardingRepository(dataStore)

    @Provides
    @Singleton
    fun provideCourageRepository(
        dataStore: DataStore<Preferences>,
    ): CourageRepository = PreferencesCourageRepository(dataStore)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "courage.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideScenarioRepository(
        @ApplicationContext context: Context,
        db: AppDatabase,
        onboardingRepository: OnboardingRepository,
    ): ScenarioRepository {
        return RoomScenarioRepository(
            context = context,
            scenarioDao = db.scenarioDao(),
            decisionDao = db.decisionDao(),
            onboardingRepository = onboardingRepository,
        )
    }

    @Provides
    @Singleton
    fun provideDecisionRepository(db: AppDatabase): DecisionRepository {
        return RoomDecisionRepository(db.decisionDao())
    }

    @Provides
    @Singleton
    fun provideUserProfileRemoteRepository(
        firestore: FirebaseFirestore,
    ): UserProfileRemoteRepository = FirestoreUserProfileRemoteRepository(firestore)

    @Provides
    @Singleton
    fun provideFeedbackSessionRepository(
        firestore: FirebaseFirestore,
    ): FeedbackSessionRepository = FirestoreFeedbackSessionRepository(firestore)
}


