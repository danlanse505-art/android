package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast

class ListViewActivity : Activity() {

    private val animals = listOf(
        mapOf("icon" to R.drawable.cat, "name" to "Cat"),
        mapOf("icon" to R.drawable.dog, "name" to "Dog"),
        mapOf("icon" to R.drawable.elephant, "name" to "Elephant"),
        mapOf("icon" to R.drawable.lion, "name" to "Lion"),
        mapOf("icon" to R.drawable.monkey, "name" to "Monkey"),
        mapOf("icon" to R.drawable.tiger, "name" to "Tiger")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)

        if (Build.VERSION.SDK_INT >= 33) {
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 100)
        }

        val listView = findViewById<ListView>(R.id.listview)
        val adapter = SimpleAdapter(
            this,
            animals,
            R.layout.item_animal,
            arrayOf("icon", "name"),
            intArrayOf(R.id.icon, R.id.name)
        )
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->
            val name = animals[position]["name"] as String
            Toast.makeText(this, "选中：$name", Toast.LENGTH_SHORT).show()
            sendNotification(name)
        }
    }

    private fun sendNotification(name: String) {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "animal"
        if (Build.VERSION.SDK_INT >= 26) {
            manager.createNotificationChannel(
                NotificationChannel(channelId, "动物通知", NotificationManager.IMPORTANCE_DEFAULT)
            )
        }
        val builder = if (Build.VERSION.SDK_INT >= 26) {
            Notification.Builder(this, channelId)
        } else {
            @Suppress("DEPRECATION")
            Notification.Builder(this)
        }
        builder.setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setContentTitle(name)
            .setContentText("你点击了 $name")
            .setAutoCancel(true)
        manager.notify(1, builder.build())
    }
}
