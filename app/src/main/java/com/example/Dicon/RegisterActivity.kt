package com.example.Dicon

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity(){
    private var et_id: EditText? = null
    private var et_password: EditText? = null
    private var signUp: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        et_id = findViewById(R.id.idRegister)
        et_password = findViewById(R.id.passwordRegister)
        signUp = findViewById(R.id.signUpBtn)
        val auth: FirebaseAuth = FirebaseAuth.getInstance()


        signUp?.setOnClickListener{
            Log.e("asd", "Asd")
            val signUp_Id = et_id?.text.toString()
            val signUp_Password = et_id?.text.toString()
            auth.createUserWithEmailAndPassword(signUp_Id, signUp_Password)
                .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.d("SignUp", "Success")
                    val user = auth.currentUser
                }else{
                    Log.w("SignUp", "Failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
