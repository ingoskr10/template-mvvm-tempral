package com.ejemplo.miapp.datos.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey val id: String,
    val correo: String,
    val nombre: String
)