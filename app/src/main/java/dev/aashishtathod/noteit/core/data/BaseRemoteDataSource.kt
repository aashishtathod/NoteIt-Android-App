package dev.aashishtathod.noteit.core.data

import dev.aashishtathod.noteit.core.utils.Either
import javax.inject.Inject

open class BaseRemoteDataSource @Inject constructor() {
	
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Either<T> {
        return try {
            val apiResponse = apiCall.invoke()
			
            Either.Success(apiResponse)
        } catch (throwable: Throwable) {
            Either.Error(throwable.message.toString())
        }
    }
}
