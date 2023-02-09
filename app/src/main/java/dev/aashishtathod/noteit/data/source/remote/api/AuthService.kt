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
    @GET("9c60a059-92f3-4eaa-b4fd-efa7963ee35b")
    suspend fun login(/*@Body authRequest: AuthRequest*/): AuthResponse
}
