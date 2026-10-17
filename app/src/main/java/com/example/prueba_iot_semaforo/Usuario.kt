package com.example.prueba_iot_semaforo

import java.io.Serializable

data class Usuario(
    val rut: Int,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val ocupacion: String,
    val contraseña: String
) : Serializable