package com.example.habbittracker.data.remote

import com.example.habbittracker.data.local.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain) : Response{
        val token = runBlocking { tokenManager.getToken.first() }
        val originalRequest = chain.request()
        if(token == null){
            return  chain.proceed(originalRequest)
        }

        val newRequest = originalRequest.newBuilder().addHeader("Authorization", "Bearer $token").build()
        return chain.proceed(newRequest)
    }
}