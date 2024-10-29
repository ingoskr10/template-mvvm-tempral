package com.ejemplo.miapp.datos.basedatos

import androidx.room.*
import com.ejemplo.miapp.datos.entidades.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuarios")
    fun obtenerTodos(): Flow<List<Usuario>>

    @Query("SELECT * FROM usuarios WHERE id = :id")
    suspend fun obtenerPorId(id: String): Usuario?

    @Query("SELECT * FROM usuarios WHERE correo = :correo LIMIT 1")
    suspend fun obtenerPorCorreo(correo: String): Usuario?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(usuario: Usuario)

    @Update
    suspend fun actualizar(usuario: Usuario)

    @Delete
    suspend fun eliminar(usuario: Usuario)

    @Query("DELETE FROM usuarios")
    suspend fun eliminarTodos()
}