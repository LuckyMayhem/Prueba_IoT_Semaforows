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

class Ver_perfil : AppCompatActivity() {

    private lateinit var tvusuperfil: TextView
    private lateinit var tvrutper: TextView
    private lateinit var tvnomper: TextView
    private lateinit var tvapeper: TextView
    private lateinit var tvemaper: TextView
    private lateinit var tvocuper: TextView
    private lateinit var tvconper: TextView
    private lateinit var btnvolper: Button

    // Objeto donde se recibirá el objeto "usu".
    private var usu: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ver_perfil)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvusuperfil = findViewById(R.id.tvusuperfil)
        tvrutper = findViewById(R.id.tvrutper)
        tvnomper = findViewById(R.id.tvnomper)
        tvapeper = findViewById(R.id.tvapeper)
        tvemaper = findViewById(R.id.tvemaper)
        tvocuper = findViewById(R.id.tvocuper)
        tvconper = findViewById(R.id.tvconper)
        btnvolper = findViewById(R.id.btnvolper)

        // Se recibe el objeto enviado desde la actividad anterior.
        usu = intent.getSerializableExtra("usu", Usuario::class.java)

        // Se muestra el dato solo si NO es NULL
        tvusuperfil.setText("Bienvenido Usuario : " + usu?.nombre + " " + usu?.apellido)
        tvrutper.setText(usu?.rut.toString())
        tvnomper.setText(usu?.nombre)
        tvapeper.setText(usu?.apellido)
        tvemaper.setText(usu?.correo)
        tvocuper.setText(usu?.ocupacion)
        tvconper.setText(usu?.contraseña)

        volver()
        desactivarFlecha()

    } // Cierra el "onCreate".



    fun volver() {
        btnvolper.setOnClickListener {
            var intent = Intent(this@Ver_perfil, Menu::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }
    } // Cierra la función "volver".



    fun desactivarFlecha() {
        onBackPressedDispatcher.addCallback(this) {

        }
    } // Cierra el "desactivarFlecha".

} // Cierra la clase "Ver_perfil".