package dev.aashishtathod.noteit.data.repositoryImpl

import dev.aashishtathod.noteit.core.utils.Either
import dev.aashishtathod.noteit.data.source.remote.dataSource.UserRemoteDataSource
import dev.aashishtathod.noteit.data.source.remote.request.AuthRequest
import dev.aashishtathod.noteit.domain.model.AuthCredential
import dev.aashishtathod.noteit.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
	
    override suspend fun register(username: String, password: String): Either<AuthCredential> {
        TODO("Not yet implemented")
    }
	
    override suspend fun login(
        username: String,
        password: String
    ): Either<AuthCredential> {
        val result = userRemoteDataSource.login(AuthRequest(username, password))
		
        return when (result) {
            is Either.Success -> Either.Success(AuthCredential(result.data.token))
            is Either.Error -> Either.Error(result.message)
        }
    }
}
