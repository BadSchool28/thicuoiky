package com.example.lastterm.testdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lastterm.R
import com.example.lastterm.databinding.ActivityMainBinding
import com.example.lastterm.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class SubenbulenMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var  adapter: BulenAdapter
    private var quizList = mutableListOf<Quiz>()
    lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        populate()
        setUpView()
    }
    private fun populate(){
        quizList.add(Quiz("12-10-2002","Subenbulen"))
        quizList.add(Quiz("12-102-2002","Subenb2ulen"))
    }
    private fun setUpView() {
        setUpFireStore()
        setUpRecyclerView()
    }

    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collections = firestore.collection("quizes")
        collections.addSnapshotListener { value, error ->
            if(value  == null || error != null){
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
                return@addSnapshotListener
            }
            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
        }
    }

    private fun setUpRecyclerView() {
        adapter = BulenAdapter(this , quizList)
        binding.quizRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.quizRecyclerView.adapter = adapter
    }


}