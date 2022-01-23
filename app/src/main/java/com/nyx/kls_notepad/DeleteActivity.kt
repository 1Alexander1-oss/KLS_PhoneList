package com.nyx.kls_notepad

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.nyx.kls_notepad.database.DBHelper
import com.nyx.kls_notepad.repository.SqlRepository

class DeleteActivity : AppCompatActivity() {
    private lateinit var models: TextView
    private lateinit var parameters: TextView
    private lateinit var price: TextView
    private lateinit var button: Button
    private lateinit var repository: SqlRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val id = intent.getIntExtra("Key", 0)

        repository = SqlRepository(dbHalper = DBHelper(this))
        models = findViewById(R.id.model)
        parameters = findViewById(R.id.parameters)
        price = findViewById(R.id.price)
        button = findViewById(R.id.delete_button)

        val note = repository.getNote(id = id)
        models.text = note?.models
        parameters.text = note?.parameters
        price.text = note?.price.toString()

        button.setOnClickListener {
            repository.deleteNote(id)
            onBackPressed()
        }
    }
}