package com.example.cineplus.repository

import android.content.Context
import com.example.cineplus.data.remote.ApiService
import com.example.cineplus.data.remote.RetrofitClient
import com.example.cineplus.data.remote.dto.UserDto

/**
 * Repository: Abstrae la fuente de datos
 * El ViewModel NO sabe si los datos vienen de API, base de datos local, etc.
 */
class UserRepository(context: Context) {

    // Crear la instancia del API Service (pasando el contexto)
    private val apiService: ApiService = RetrofitClient
        .create(context)
        .create(ApiService::class.java)

    /**
     * Obtiene un usuario de la API
     *
     * Usa Result<T> para manejar éxito/error de forma elegante
     */
    suspend fun fetchUser(id: Int = 1): Result<Unit> {
        return try {
            // Llamar a la API (esto puede tardar varios segundos)
            val user = apiService.getUser(id)

            // Retornar éxito
            Result.success(user)

        } catch (e: Exception) {
            // Si algo falla (sin internet, timeout, etc.)
            Result.failure(e)
        }
    }
}