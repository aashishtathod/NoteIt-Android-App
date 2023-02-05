package dev.aashishtathod.noteit.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.aashishtathod.noteit.data.repositoryImpl.UserRepositoryImpl
import dev.aashishtathod.noteit.data.source.remote.dataSource.UserRemoteDataSource
import dev.aashishtathod.noteit.domain.repository.UserRepository

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
	
    @Provides
    fun provideLoginRepository(
        remoteDataSource: UserRemoteDataSource
    ): UserRepository = UserRepositoryImpl(remoteDataSource)
}
