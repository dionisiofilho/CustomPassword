package com.dionisiofilho.passwordview

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var password: PasswordViewCustom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.btn_send)
        password = findViewById(R.id.pvc_password)
        password.onFinishPassword {
            attemptPassword()
        }


        btn.setOnClickListener {
            attemptPassword()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        password.error()
    }

    private fun attemptPassword() {

        password.cleanError()

        when {
            password.getTextPassowrd().isNullOrEmpty() -> {
                password.error()
            }

            password.getTextPassowrd().length < password.getQtdPassword() -> {
                password.error()
            }

            else -> {
                Toast.makeText(this, "Password OK", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
