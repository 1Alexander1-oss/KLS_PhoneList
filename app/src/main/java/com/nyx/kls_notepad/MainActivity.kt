package com.nyx.kls_notepad

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button // объявление переменной "button". button - обьект. переменная "button" : имеет тип "Button"
    // private lateinit (var) - определение переменной имеющей возможность менять значение

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button) // присваивание переменной "button"
        button.setOnClickListener { // установка клик листенера на кнопку
            val intent = Intent(this, PhoneListActivity::class.java)
            startActivity(intent)
        }
    }

}