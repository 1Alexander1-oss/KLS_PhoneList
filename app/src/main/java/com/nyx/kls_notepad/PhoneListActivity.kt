package com.nyx.kls_notepad

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nyx.kls_notepad.database.DBHelper
import com.nyx.kls_notepad.repository.SqlRepository

class PhoneListActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var recycler: RecyclerView
    private lateinit var button: FloatingActionButton
    private lateinit var repository: SqlRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_list)

        recycler = findViewById(R.id.recycler)
        repository = SqlRepository(dbHalper = DBHelper(this))
        recycler.layoutManager = LinearLayoutManager(this)
        button = findViewById(R.id.save_button)
        button.setOnClickListener {
            val intent = Intent(this, CreatorActivity::class.java)
            startActivityForResult(intent, 0)
        }
        getNotes()
    }

    private fun getNotes() {
        val notes = repository.getAllNotes()
        val adapter = Adapter(notes, this)
        recycler.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getNotes()
    }

    override fun onItemClick(id: Int) {
        val intent = Intent(this, DeleteActivity::class.java)
        intent.putExtra("Key", id)
        startActivityForResult(intent, 0)
    }


}