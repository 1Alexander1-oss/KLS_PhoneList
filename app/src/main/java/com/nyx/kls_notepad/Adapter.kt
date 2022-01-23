package com.nyx.kls_notepad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nyx.kls_notepad.entity.Note

class Adapter(
    private val notes: List<Note>,
    private val listener: PhoneListActivity,
) : RecyclerView.Adapter<NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.note_item,
                parent,
                false,
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.setContent(note)
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}


class NoteViewHolder(
    itemView: View,
    private val listener: PhoneListActivity,
) : RecyclerView.ViewHolder(itemView) {
    fun setContent(note: Note) {
        itemView.setOnClickListener {
            listener.onItemClick(note.id)
        }
        val models = itemView.findViewById<TextView>(R.id.models)
        val parameters = itemView.findViewById<TextView>(R.id.parameters)
        val price = itemView.findViewById<TextView>(R.id.price)
        models.text = "Model: ${note.models}"
        parameters.text = "Parameters: ${note.parameters}"
        price.text = "Price: ${note.price}"

    }
}
