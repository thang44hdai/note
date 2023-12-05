package com.example.note.presentation.homePage

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.note.R
import com.example.note.data.item
import com.example.note.ui.theme.OrangeYellow2
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun home(
    nav: NavController
) {
    var title by remember {
        mutableStateOf("")
    }
    val database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    database.child("user").addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            title = snapshot.child("name").getValue().toString()
            println(title)
        }
        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    })
    Scaffold(
        topBar = {
            Box(modifier = Modifier.padding(bottom = 10.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer(alpha = 0.5f),
                    contentScale = ContentScale.FillWidth
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 5.dp)
                ) {
                    Text(
                        text = title,
                        style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
                        color = Color.Black
                    )
                }
            }
        },
        floatingActionButton = {
            Box(modifier = Modifier
                .clip(CircleShape)
                .background(OrangeYellow2)
                .clickable {
                    nav.navigate("add_note")
                }
            )
            {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = null,
                    modifier = Modifier.padding(15.dp)
                )
            }
        },
        bottomBar = {
            BottomMenu(
                items = listOf(
                    item("Home", R.drawable.ic_home),
                    item("Mine", R.drawable.ic_profile),
                ),
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            list_task()
        }
    }
}

