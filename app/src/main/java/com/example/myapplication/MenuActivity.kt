package com.example.myapplication

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

class MenuActivity : Activity() {

    private lateinit var testText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        testText = findViewById(R.id.test_text)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_test, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.font_small -> testText.textSize = 10f
            R.id.font_medium -> testText.textSize = 16f
            R.id.font_large -> testText.textSize = 20f
            R.id.color_red -> testText.setTextColor(Color.RED)
            R.id.color_black -> testText.setTextColor(Color.BLACK)
            R.id.normal_item -> Toast.makeText(this, "普通菜单项被点击", Toast.LENGTH_SHORT).show()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
