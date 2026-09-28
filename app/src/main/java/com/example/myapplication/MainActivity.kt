package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExperimentMenu(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ExperimentMenu(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val items = listOf(
        "线性布局 LinearLayout" to LinearLayoutActivity::class.java,
        "表格布局 TableLayout" to TableLayoutActivity::class.java,
        "约束布局 ConstraintLayout 1" to ConstraintLayout1Activity::class.java,
        "约束布局 ConstraintLayout 2（太空）" to ConstraintLayout2Activity::class.java,
        "Compose 任务列表" to ComposeActivity::class.java
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "实验二：Android 界面布局", fontSize = 22.sp)
        items.forEach { (label, cls) ->
            Button(
                onClick = { context.startActivity(Intent(context, cls)) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = label)
            }
        }
    }
}
