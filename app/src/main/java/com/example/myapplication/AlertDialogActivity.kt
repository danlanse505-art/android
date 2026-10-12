package com.example.myapplication

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AlertDialogActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alertdialog)

        findViewById<Button>(R.id.btn_show_dialog).setOnClickListener {
            showLoginDialog()
        }
    }

    private fun showLoginDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_login, null)
        val dialog = AlertDialog.Builder(this)
            .setView(view)
            .create()

        val username = view.findViewById<EditText>(R.id.username)
        view.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }
        view.findViewById<Button>(R.id.btn_sign).setOnClickListener {
            val u = username.text.toString().ifEmpty { "(空)" }
            Toast.makeText(this, "Username: $u", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        dialog.show()
    }
}
