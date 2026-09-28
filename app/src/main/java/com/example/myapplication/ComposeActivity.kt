package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Task(val name: String, val isDone: Boolean)

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TaskListScreen()
            }
        }
    }
}

@Composable
fun TaskListScreen() {
    val tasks = remember {
        mutableStateListOf(
            Task("学习 Compose", true),
            Task("学习 XML 布局", false),
            Task("完成实验报告", false)
        )
    }
    val doneCount = tasks.count { it.isDone }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "课程学习任务", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "完成 $doneCount / ${tasks.size}", fontSize = 16.sp)

        LazyColumn(modifier = Modifier.weight(1f).padding(top = 8.dp)) {
            itemsIndexed(tasks) { index, task ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = task.isDone,
                        onCheckedChange = { checked ->
                            tasks[index] = task.copy(isDone = checked)
                        }
                    )
                    Text(
                        text = task.name,
                        fontSize = 18.sp,
                        textDecoration = if (task.isDone) TextDecoration.LineThrough else null
                    )
                }
            }
        }

        Button(onClick = { tasks.add(Task("复习 LazyColumn", false)) }) {
            Text(text = "添加任务")
        }
    }
}
