package com.example.prueba_iot_semaforo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Estadisticas : AppCompatActivity() {

    private lateinit var tvusuestadisticas: TextView
    private lateinit var btnvolest: Button

    // Objeto donde se recibirá el objeto "usu".
    private var usu: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_estadisticas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvusuestadisticas = findViewById(R.id.tvusuestadisticas)
        btnvolest = findViewById(R.id.btnvolest)

        // Se recibe el objeto enviado desde la actividad anterior.
        usu = intent.getSerializableExtra("usu") as? Usuario

        // Se muestra el dato solo si NO es NULL
        tvusuestadisticas.setText("Bienvenido Usuario : " + usu?.nombre + " " + usu?.apellido)

        volver()
        desactivarFlecha()

    } // Cierra el "onCreate".



    fun volver() {
        btnvolest.setOnClickListener {
            var intent = Intent(this@Estadisticas, Menu::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }
    } // Cierra la función "volver".



    fun desactivarFlecha() {
        onBackPressedDispatcher.addCallback(this) {

        }
    } // Cierra el "desactivarFlecha".

} // Cierra la clase "Estadisticas".