package com.example.Dicon

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.Dicon.R

class RegisterActivity : AppCompatActivity() {
    private var et_id: EditText? = null
    private var et_password: EditText? = null
    private var register: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        et_id = findViewById(R.id.idRegister)
        et_password = findViewById(R.id.passwordRegister)
        register = findViewById(R.id.registerBtn)

        register!!.setOnClickListener { }
    }
}
