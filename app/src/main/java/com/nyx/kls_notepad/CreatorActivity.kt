package com.nyx.kls_notepad

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.nyx.kls_notepad.R
import com.nyx.kls_notepad.database.DBHelper
import com.nyx.kls_notepad.entity.Note
import com.nyx.kls_notepad.repository.SqlRepository

class CreatorActivity : AppCompatActivity() {
    private lateinit var models: EditText
    private lateinit var parameters: EditText
    private lateinit var price: EditText
    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator)

        val repository = SqlRepository(dbHalper = DBHelper(this))

        models = findViewById(R.id.models)
        parameters = findViewById(R.id.parameters)
        price = findViewById(R.id.price)
        button = findViewById(R.id.save_button)
        button.setOnClickListener {
            val models = this.models.text.toString()
            val parameters = this.parameters.text.toString()
            val price = this.price.text.toString().toDouble()
            val note = Note(0,models,parameters, price)
            repository.addNote(note)
            onBackPressed()
        }
    }
}