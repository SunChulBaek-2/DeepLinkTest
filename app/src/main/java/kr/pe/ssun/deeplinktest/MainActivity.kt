package kr.pe.ssun.deeplinktest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kr.pe.ssun.deeplinktest.ui.theme.DeepLinkTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeepLinkTestTheme {
                Scaffold(topBar = { TopAppBar() {
                    Text(text = "딥링크 테스트")
                }}) { paddingValues ->
                    MainContent(paddingValues) { url ->
                        Toast.makeText(this, url, Toast.LENGTH_SHORT).show()
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun MainContent(paddingValues: PaddingValues, onClick: (String)->Unit) {
    Column(modifier = Modifier.padding(paddingValues)) {
        val scheme = rememberSaveable { mutableStateOf("fanpa") }
        Row1("scheme", scheme)
        val host = rememberSaveable { mutableStateOf("open") }
        Row1("host", host)
        val path = rememberSaveable { mutableStateOf("") }
        Row1("path", path)

        val query1 = rememberSaveable { mutableStateOf("url") }
        val param1 = rememberSaveable { mutableStateOf("https://www.naver.com") }
        Row2(query1, param1)
        
        val query2 = rememberSaveable { mutableStateOf("title") }
        val param2 = rememberSaveable { mutableStateOf("네이버") }
        Row2(query2, param2)

        // 동작
        val url = makeUrl(scheme.value, host.value, path.value, query1.value, param1.value, query2.value, param2.value)
        Text(text = url)
        Button(onClick = { onClick.invoke(url) }) {
            Text(
                text = "열기",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

fun makeUrl(scheme: String, host: String?, path: String?, query1: String?, param1: String?, query2: String?, param2: String?): String {
    return "$scheme://${host ?: ""}${if(path.isNullOrEmpty()) "" else "/"}${path ?: ""}${if (query1.isNullOrEmpty()) "" else "?"}$query1=$param1${if (query2.isNullOrEmpty()) "" else "&"}$query2=$param2"
}

@Composable
fun Row1(label: String, state: MutableState<String>) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("$label : ")
        TextField(value = state.value, onValueChange = {
            state.value = it
        })
    }
}

@Composable
fun Row2(state1: MutableState<String>, state2: MutableState<String>) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        TextField(value = state1.value, label = { Text("query") }, onValueChange = {
            state1.value = it
        }, modifier = Modifier.width(80.dp))
        Text(":")
        TextField(value = state2.value, label = { Text("param")}, onValueChange = {
            state2.value = it
        })
    }
}