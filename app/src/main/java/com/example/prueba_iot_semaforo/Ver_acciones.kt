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

class Ver_acciones : AppCompatActivity() {

    private lateinit var tvusuacciones: TextView
    private lateinit var btnvolacc: Button

    // Objeto donde se recibirá el objeto "usu".
    private var usu: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_acciones)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvusuacciones = findViewById(R.id.tvusuacciones)
        btnvolacc = findViewById(R.id.btnvolacc)

        // Se recibe el objeto enviado desde la actividad anterior.
        usu = intent.getSerializableExtra("usu", Usuario::class.java)

        // Se muestra el dato solo si NO es NULL
        tvusuacciones.setText("Bienvenido Usuario : " + usu?.nombre + " " + usu?.apellido)

        volver()
        desactivarFlecha()

    } // Cierra el "onCreate".



    fun volver() {
        btnvolacc.setOnClickListener {
            var intent = Intent(this@Ver_acciones, Menu::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }
    } // Cierra la función "volver".



    fun desactivarFlecha() {
        onBackPressedDispatcher.addCallback(this) {

        }
    } // Cierra el "desactivarFlecha".

} // Cierra la clase "Ver_acciones".