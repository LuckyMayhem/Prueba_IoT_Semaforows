package com.example.prueba_iot_semaforo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class Crear_cuenta : AppCompatActivity() {

    private lateinit var txtrut: EditText
    private lateinit var txtnom: EditText
    private lateinit var txtape: EditText
    private lateinit var txtema: EditText
    private lateinit var spncargo: Spinner
    private lateinit var txtcon: EditText
    private lateinit var txtcon2: EditText
    private lateinit var btnregistrar: Button
    private lateinit var btnvolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_cuenta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtrut = findViewById(R.id.txtrut_reg)
        txtnom = findViewById(R.id.txtnom_reg)
        txtape = findViewById(R.id.txtape_reg)
        txtema = findViewById(R.id.txtema_reg)
        spncargo = findViewById(R.id.spncargo_reg)
        txtcon = findViewById(R.id.txtcon_reg)
        txtcon2 = findViewById(R.id.txtcon2_reg)
        btnregistrar = findViewById(R.id.btcrear)
        btnvolver = findViewById(R.id.btvolver)

        botonRegistrar()
        botonVolver()
        desactivarFlecha()

    } // Cierra el "onCreate".



    fun botonVolver() {
        btnvolver.setOnClickListener {
            var intent = Intent(this@Crear_cuenta, Login::class.java)
            startActivity(intent)
        }
    } // Cierra la función "botonVolver".



    fun botonRegistrar() {
        btnregistrar.setOnClickListener {
            if (txtrut.getText().toString().trim().isEmpty()
                || txtnom.getText().toString().trim().isEmpty()
                || txtape.getText().toString().trim().isEmpty()
                || txtema.getText().toString().trim().isEmpty()
                || txtcon.getText().toString().trim().isEmpty()
                || txtcon2.getText().toString().trim().isEmpty()) {

                Toast.makeText(
                    this@Crear_cuenta,
                    "¡ Complete Los Campos Faltantes !",
                    Toast.LENGTH_LONG
                ).show()

            } else {

                var rut = txtrut.getText().toString().trim().toInt()
                var nom = txtnom.getText().toString().trim()
                var ape = txtape.getText().toString().trim()
                var ema = txtema.getText().toString().trim()
                var ocu = spncargo.selectedItem.toString()
                var c1 = txtcon.getText().toString().trim()
                var c2 = txtcon2.getText().toString().trim()

                if (c1.equals(c2) == false) {
                    Toast.makeText(
                        this@Crear_cuenta,
                        "¡ Las contraseñas no coinciden !",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                    val dbref = FirebaseDatabase.getInstance().getReference("Usuario")
                    var nuevoUsuario = Usuario(rut, nom, ape, ema, ocu, c1)

                    dbref.child(rut.toString()).setValue(nuevoUsuario).addOnCompleteListener {
                        ocultarTeclado()
                        Toast.makeText(
                            this@Crear_cuenta,
                            "¡ Usuario Registrado con Éxito !",
                            Toast.LENGTH_SHORT
                        ).show()

                        var intent = Intent(this@Crear_cuenta, Login::class.java)
                        startActivity(intent)
                    }

                } // Cierra if/else contraseñas.

            } // Cierra if/else campos vacíos.

        }
    } // Cierra la función "botonRegistrar".



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

} // Cierra la clase "Crear_cuenta".