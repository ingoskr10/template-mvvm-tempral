package com.ejemplo.miapp.presentacion.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import com.ejemplo.miapp.datos.repositorios.UsuarioRepositorio
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class InicioSesionViewModel(
    private val usuarioRepositorio: UsuarioRepositorio
) : ViewModel(), KoinComponent {
    
    private val _estadoInicioSesion = MutableLiveData<Boolean>()
    val estadoInicioSesion: LiveData<Boolean> = _estadoInicioSesion

    val usuarios = usuarioRepositorio.obtenerTodosLosUsuarios().asLiveData()

    fun iniciarSesion(correo: String, contraseña: String) {
        viewModelScope.launch {
            usuarioRepositorio.iniciarSesion(correo, contraseña)
                .onSuccess { exito -> 
                    _estadoInicioSesion.value = exito
                    if (exito) {
                        usuarioRepositorio.guardarUsuario(
                            correo = correo,
                            nombre = "Usuario ${System.currentTimeMillis()}"
                        )
                    }
                }
                .onFailure { /* Manejar error */ }
        }
    }
}