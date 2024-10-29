package com.ejemplo.miapp.datos.basedatos

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ejemplo.miapp.datos.entidades.Usuario

@Database(entities = [Usuario::class], version = 1)
abstract class BaseDatosApp : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
}