package com.ejemplo.miapp.presentacion.actividades

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ejemplo.miapp.databinding.ActivityInicioSesionBinding
import com.ejemplo.miapp.presentacion.adaptadores.UsuarioAdapter
import com.ejemplo.miapp.presentacion.viewmodels.InicioSesionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class InicioSesionActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityInicioSesionBinding
    private val viewModel: InicioSesionViewModel by viewModel()
    private val usuarioAdapter = UsuarioAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarRecyclerView()
        configurarObservadores()
        configurarEventos()
    }

    private fun configurarRecyclerView() {
        binding.rvUsuarios.apply {
            layoutManager = LinearLayoutManager(this@InicioSesionActivity)
            adapter = usuarioAdapter
        }
    }

    private fun configurarObservadores() {
        viewModel.estadoInicioSesion.observe(this) { exitoso ->
            if (exitoso) {
                // Aquí podrías mostrar un mensaje de éxito
            }
        }

        viewModel.usuarios.observe(this) { usuarios ->
            usuarioAdapter.submitList(usuarios)
        }
    }

    private fun configurarEventos() {
        binding.btnIniciarSesion.setOnClickListener {
            val correo = binding.etCorreo.text.toString()
            val contraseña = binding.etContraseña.text.toString()
            viewModel.iniciarSesion(correo, contraseña)
        }
    }
}