package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class ActionModeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actionmode)

        val listView = findViewById<ListView>(R.id.listview)
        val items = arrayOf("One", "Two", "Three", "Four")
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, items)
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL
        listView.setMultiChoiceModeListener(object : AbsListView.MultiChoiceModeListener {
            override fun onItemCheckedStateChanged(
                mode: ActionMode,
                position: Int,
                id: Long,
                checked: Boolean
            ) {
                mode.title = "${listView.checkedItemCount} selected"
            }

            override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
                mode.menuInflater.inflate(R.menu.menu_actionmode, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean = false

            override fun onDestroyActionMode(mode: ActionMode) {}

            override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                if (item.itemId == R.id.action_delete) {
                    Toast.makeText(this@ActionModeActivity, "删除选中项", Toast.LENGTH_SHORT).show()
                    mode.finish()
                    return true
                }
                return false
            }
        })
    }
}
