package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "实验二：Android 界面布局", fontSize = 22.sp)
        MenuButton("线性布局 LinearLayout", LinearLayoutActivity::class.java, context)
        MenuButton("表格布局 TableLayout", TableLayoutActivity::class.java, context)
        MenuButton("约束布局 ConstraintLayout 1", ConstraintLayout1Activity::class.java, context)
        MenuButton("约束布局 ConstraintLayout 2（太空）", ConstraintLayout2Activity::class.java, context)
        MenuButton("Compose 任务列表", ComposeActivity::class.java, context)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "实验三：Android 界面组件", fontSize = 22.sp)
        MenuButton("1. ListView（SimpleAdapter）", ListViewActivity::class.java, context)
        MenuButton("2. 自定义 AlertDialog", AlertDialogActivity::class.java, context)
        MenuButton("3. XML 定义菜单", MenuActivity::class.java, context)
        MenuButton("4. ActionMode 上下文菜单", ActionModeActivity::class.java, context)
        MenuButton("5. RecyclerView 列表", RecyclerViewActivity::class.java, context)
    }
}

@Composable
private fun MenuButton(label: String, cls: Class<*>, context: Context) {
    Button(
        onClick = { context.startActivity(Intent(context, cls)) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = label)
    }
}
