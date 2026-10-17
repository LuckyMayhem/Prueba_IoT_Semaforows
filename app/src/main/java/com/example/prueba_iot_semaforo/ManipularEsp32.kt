package com.example.prueba_iot_semaforo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class ManipularEsp32 : AppCompatActivity() {

    private lateinit var tvusumanipular: TextView
    private lateinit var btnon: Button
    private lateinit var btnoff: Button
    private lateinit var btnvolman: Button

    // Objeto donde se recibirá el objeto "usu".
    private var usu: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_manipular_esp32)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvusumanipular = findViewById(R.id.tvusumanipular)
        btnon = findViewById(R.id.btnon)
        btnoff = findViewById(R.id.btnoff)
        btnvolman = findViewById(R.id.btnvolman)

        // Se recibe el objeto enviado desde la actividad anterior.
        usu = intent.getSerializableExtra("usu", Usuario::class.java)

        // Solo si no es NULL
        tvusumanipular.setText("Bienvenido Usuario : " + usu?.nombre + " " + usu?.apellido)

        botonOn()
        botonOff()
        volver()
        desactivarFlecha()

    } // Cierra el "onCreate".



    fun botonOn() {
        btnon.setOnClickListener {
            val dbref = FirebaseDatabase.getInstance().getReference("Circuito")
            dbref.child("estado").setValue("ACTIVADO")
            dbref.child("ultimo_usuario").setValue(usu?.rut.toString())
            Toast.makeText(this@ManipularEsp32, "Circuito Activado", Toast.LENGTH_SHORT).show()
        }
    } // Cierra la función "botonOn".



    fun botonOff() {
        btnoff.setOnClickListener {
            val dbref = FirebaseDatabase.getInstance().getReference("Circuito")
            dbref.child("estado").setValue("DESACTIVADO")
            dbref.child("ultimo_usuario").setValue(usu?.rut.toString())
            Toast.makeText(this@ManipularEsp32, "Circuito Desactivado", Toast.LENGTH_SHORT).show()
        }
    } // Cierra la función "botonOff".



    fun volver() {
        btnvolman.setOnClickListener {
            var intent = Intent(this@ManipularEsp32, Menu::class.java)
            intent.putExtra("usu", usu)
            startActivity(intent)
        }
    } // Cierra la función "volver".



    fun desactivarFlecha() {
        onBackPressedDispatcher.addCallback(this) {

        }
    } // Cierra el "desactivarFlecha".

} // Cierra la clase "ManipularEsp32".