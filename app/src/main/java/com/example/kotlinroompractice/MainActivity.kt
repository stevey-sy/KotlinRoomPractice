package com.example.kotlinroompractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "todo-db"
        ).allowMainThreadQueries().build()
        val resultText = findViewById<TextView>(R.id.result_text)
        resultText.text = db.todoDao().getAll().toString()

        val todoEdit = findViewById<EditText>(R.id.todo_edit)

        val addButton = findViewById<Button>(R.id.add_button)
        addButton.setOnClickListener {
            db.todoDao().insert(Todo(todoEdit.getText().toString()))
            resultText.text = db.todoDao().getAll().toString()
        }
    }
}