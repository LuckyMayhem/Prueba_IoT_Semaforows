package com.example.prueba_iot_semaforo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Login : AppCompatActivity() {

    private lateinit var txtrut: EditText
    private lateinit var txtcon: EditText
    private lateinit var btnini: Button
    private lateinit var btncrear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtrut = findViewById(R.id.txtrut)
        txtcon = findViewById(R.id.txtcon)
        btnini = findViewById(R.id.btnini)
        btncrear = findViewById(R.id.btncrear)

        botonLogin()
        botonCrearCuenta()
        desactivarFlecha()

    } // Cierra el "onCreate".



    fun botonCrearCuenta() {
        btncrear.setOnClickListener {
            var intent = Intent(this@Login, Crear_cuenta::class.java)
            startActivity(intent)
        }
    } // Cierra la función "botonCrearCuenta".



    fun botonLogin() {
        btnini.setOnClickListener {
            if (txtrut.getText().toString().trim().isEmpty()
                || txtcon.getText().toString().trim().isEmpty()) {

                Toast.makeText(
                    this@Login,
                    "¡ Complete Los Campos Faltantes !",
                    Toast.LENGTH_LONG
                ).show()

            } else {

                var rutIngresado = txtrut.getText().toString().trim().toInt()
                var conIngresada = txtcon.getText().toString().trim()
                var estado = false

                val dbref = FirebaseDatabase.getInstance().getReference("Usuario")

                dbref.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {

                        for (x in snapshot.getChildren()) {

                            var rutDb = x.child("rut").getValue().toString().toInt()
                            var conDb = x.child("contraseña").getValue().toString()

                            if (rutIngresado == rutDb && conIngresada.equals(conDb)) {

                                estado = true
                                val nom = x.child("nombre").getValue().toString()
                                val ape = x.child("apellido").getValue().toString()
                                val cor = x.child("correo").getValue().toString()
                                val ocu = x.child("ocupacion").getValue().toString()

                                var usu = Usuario(rutDb, nom, ape, cor, ocu, conDb)
                                var intent = Intent(this@Login, Menu::class.java)
                                intent.putExtra("usu", usu)
                                startActivity(intent)

                            }
                        } // Cierra el ciclo for.

                        if (estado == false) {
                            txtrut.setText("")
                            txtcon.setText("")
                            ocultarTeclado()

                            Toast.makeText(
                                this@Login,
                                "¡ Error de Rut y/o Contraseña !",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } // Cierra el "onDataChange".

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

            } // Cierra el if/else inicial.

        }
    } // Cierra la función "botonLogin".



    fun desactivarFlecha() {
        onBackPressedDispatcher.addCallback(this) {

        }
    } // Cierra el "desactivarFlecha".



    private fun ocultarTeclado() {
        val view = currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    } // Cierra la función "ocultarTeclado".

} // Cierra la clase "Login".