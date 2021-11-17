package com.example.composeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Space
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        binding.composeView.setContent{
            MaterialTheme {
                Column {
                    header(userName = "From Compose")
                    appendItemButton {
                        viewModel.appendList()
                    }
                    body(viewModel)
                }
            }
        }

        setContentView(binding.root)
    }
}

@Composable
fun header(userName : String){
    Text("Hello $userName")
}

@Composable
fun appendItemButton(onClick : () -> Unit){
    Button(onClick){
        Text("Append list")
    }
}

@Composable
fun informationCard(info : String){
    Card(elevation = 5.dp, backgroundColor = Color.Gray, modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()){
        Text(info, modifier = Modifier.padding(5.dp))
    }
}

@Composable
fun body(viewModel: MainViewModel){
    val data by viewModel.progressList.observeAsState()

    if(data.isNullOrEmpty()){
        informationCard("No Items in the list")
    }
    else{
        var index = 0
        data?.onEach {
            index += 1
            informationCard(index.toString())
        }
    }


}