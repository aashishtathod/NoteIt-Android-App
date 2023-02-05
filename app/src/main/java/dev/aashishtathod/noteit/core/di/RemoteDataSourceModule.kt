package dev.aashishtathod.noteit.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.aashishtathod.noteit.data.source.remote.api.AuthService
import dev.aashishtathod.noteit.data.source.remote.dataSource.UserRemoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {
	
    @Provides
    @Singleton
    fun userRemoteDataSource(
        authService: AuthService,
        @ApplicationContext context: Context
    ): UserRemoteDataSource = UserRemoteDataSource(authService)
}
