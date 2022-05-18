package com.example.lastterm.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.lastterm.R
import com.example.lastterm.databinding.ActivityLoginBinding
import com.example.lastterm.testdemo.SubenbulenMainActivity
//import com.example.lastterm.testdemo.SubenbulenMainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var progress : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        auth = FirebaseAuth.getInstance()
        progress = ProgressDialog(this)
        progress.setTitle("Wait")
//        progress.setCanceledOnTouchOutside(false)

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            validateData()
        }
    }


    private var email = ""
    private var password = ""
    private fun validateData() {
        email = binding.etEmail.text.toString().trim()
        password = binding.etPassword.text.toString().trim()
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            binding.etEmail.requestFocus()
            return
        }else if(password.isEmpty()){
            Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_SHORT).show()
            binding.etPassword.requestFocus()
            return
        }
        else{
            loginUser()
        }
    }

    private fun loginUser() {
        progress.setMessage("Logging in...")
        progress.show()

        auth.signInWithEmailAndPassword(email , password)
            .addOnSuccessListener {
                progress.dismiss()
//                startActivity(Intent(this, QuizQuestionActivity::class.java))
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {
                progress.dismiss()
                Toast.makeText(this, "Login failed wrong username or password", Toast.LENGTH_SHORT).show()

            }
    }
}