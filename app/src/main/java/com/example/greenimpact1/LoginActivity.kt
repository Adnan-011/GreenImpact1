package com.example.greenimpact1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity: AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

     login_button_login.setOnClickListener {
         performLogin()
     }



        back_to_register_textview.setOnClickListener {
            finish()
        }




    }
      private fun performLogin(){
          val email = email_edittext_login.text.toString()
          val password = password_edittext_login.text.toString()

          if (email.isEmpty() || password.isEmpty()){
              Toast.makeText(this, "please enter text in email/pw", Toast.LENGTH_SHORT).show()
              return
          }

          Log.d("Login", "Attempt login with email/pw: $email/***")



          FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
              .addOnCompleteListener {
                  if (!it.isSuccessful) return@addOnCompleteListener
                  // else if successful
                  Log.d("Login", "Successfully login user with uid: ${it.result?.user?.uid}")

              }
              .addOnFailureListener{
                  Log.d("Main", "Fail to login user: ${it.message}")}
      }
}