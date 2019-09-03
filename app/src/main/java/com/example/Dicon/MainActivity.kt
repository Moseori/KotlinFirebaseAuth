package com.example.Dicon

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {
    private var registerBtn: Button?= null
    private var loginBtn: Button?= null
    private var auth: FirebaseAuth?= null
    private var googleSignInClient: GoogleSignInClient?= null
    private var googleLogin: SignInButton?= null
    private var googleLoginBtn:SignInButton?=null
    var RC_SIGN_IN = 9001

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                auth!!.signInWithCredential(credential)
                    .addOnCompleteListener(this){
                        if (it.isSuccessful){
                            val user = auth?.currentUser
                            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            catch (e: ApiException){

            }

        }//
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        googleLoginBtn = findViewById(R.id.googleLoginBtn)
        registerBtn = findViewById(R.id.registerBtn)
        loginBtn = findViewById(R.id.loginBtn)

        googleLogin?.setOnClickListener {
            Log.e("asd", "Asd")
            val signInIntent = googleSignInClient?.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        googleLoginBtn?.setOnClickListener {
            Log.e("asd", "Asd")
            val signInIntent = googleSignInClient?.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        //회원가입 버튼을 누를시
        registerBtn?.setOnClickListener {
            Log.e("asd", "Asd")
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)




    }



}
