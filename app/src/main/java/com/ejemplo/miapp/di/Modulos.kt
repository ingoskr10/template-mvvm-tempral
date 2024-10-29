package com.ejemplo.miapp.di

import android.content.Context
import androidx.room.Room
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.ejemplo.miapp.datos.basedatos.BaseDatosApp
import com.ejemplo.miapp.datos.repositorios.UsuarioRepositorio
import com.ejemplo.miapp.presentacion.viewmodels.InicioSesionViewModel
import com.ejemplo.miapp.red.ServicioApi
import com.ejemplo.miapp.red.ServicioApiImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// Módulo de red
val moduloRed = module {
    single { proveerVolleyQueue(androidContext()) }
    single<ServicioApi> { ServicioApiImpl(get()) }
}

// Módulo de base de datos
val moduloBaseDatos = module {
    single { proveerBaseDatos(androidContext()) }
    single { get<BaseDatosApp>().usuarioDao() }
}

// Módulo de repositorios
val moduloRepositorios = module {
    single { UsuarioRepositorio(get(), get(), get()) }
}

// Módulo de ViewModels
val moduloViewModels = module {
    viewModel { InicioSesionViewModel(get()) }
}

private fun proveerVolleyQueue(context: Context): RequestQueue = 
    Volley.newRequestQueue(context)

private fun proveerBaseDatos(context: Context): BaseDatosApp =
    Room.databaseBuilder(
        context,
        BaseDatosApp::class.java,
        "miapp-db"
    ).build()