package com.ejemplo.miapp

import android.app.Application
import com.ejemplo.miapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MiAplicacion : Application() {
    override fun onCreate() {
        super.onCreate()
        inicializarKoin()
        ConfiguracionRemota.inicializar()
    }

    private fun inicializarKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MiAplicacion)
            modules(listOf(
                moduloRed,
                moduloBaseDatos,
                moduloRepositorios,
                moduloViewModels
            ))
        }
    }
}