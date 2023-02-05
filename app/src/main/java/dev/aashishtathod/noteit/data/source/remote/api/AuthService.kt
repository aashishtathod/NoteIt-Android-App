package dev.aashishtathod.noteit.data.source.remote.api

import dev.aashishtathod.noteit.data.source.remote.dto.AuthResponse
import dev.aashishtathod.noteit.data.source.remote.request.AuthRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
	
    @POST("/auth/register")
    suspend fun register(@Body authRequest: AuthRequest): AuthResponse
	
//    @POST("/auth/login")
    @GET("/aceceab2-9095-4f6b-aef9-ae42f1cf4f8b")
    suspend fun login(/*@Body authRequest: AuthRequest*/): AuthResponse
}
