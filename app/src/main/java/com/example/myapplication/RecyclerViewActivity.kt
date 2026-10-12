package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : Activity() {

    private val animals = mutableListOf(
        Animal(R.drawable.cat, "Cat"),
        Animal(R.drawable.dog, "Dog"),
        Animal(R.drawable.elephant, "Elephant"),
        Animal(R.drawable.lion, "Lion"),
        Animal(R.drawable.monkey, "Monkey"),
        Animal(R.drawable.tiger, "Tiger")
    )

    private lateinit var adapter: AnimalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        adapter = AnimalAdapter(animals) { name ->
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
        }
        recycler.adapter = adapter

        findViewById<Button>(R.id.btn_add).setOnClickListener {
            val newIndex = animals.size
            animals.add(Animal(R.drawable.tiger, "Tiger ${newIndex + 1}"))
            adapter.notifyItemInserted(newIndex)
            recycler.scrollToPosition(newIndex)
        }
    }

    data class Animal(val icon: Int, val name: String)

    class AnimalAdapter(
        private val data: List<Animal>,
        private val onClick: (String) -> Unit
    ) : RecyclerView.Adapter<AnimalAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val icon: ImageView = view.findViewById(R.id.icon)
            val name: TextView = view.findViewById(R.id.name)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val animal = data[position]
            holder.icon.setImageResource(animal.icon)
            holder.name.text = animal.name
            holder.itemView.setOnClickListener { onClick(animal.name) }
        }

        override fun getItemCount(): Int = data.size
    }
}
