package com.ejemplo.miapp.red

interface ServicioApi {
    suspend fun iniciarSesion(correo: String, contraseña: String): Result<Boolean>
    suspend fun obtenerPerfilUsuario(id: String): Result<PerfilUsuario>
}

data class PerfilUsuario(
    val id: String,
    val nombre: String,
    val correo: String
)