package com.example.note.presentation.homePage

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.note.R
import com.example.note.data.EntertainmentItem
import com.example.note.data.item
import com.example.note.data.task_item
import com.example.note.ui.theme.Beige1
import com.example.note.ui.theme.ButtonBlue
import com.example.note.ui.theme.DeepBlue
import com.example.note.ui.theme.OrangeYellow2
import com.example.note.ui.theme.Purple40
import com.example.note.ui.theme.add_color
import com.example.note.ui.theme.bg_color
import com.example.note.ui.theme.fontText
import com.example.note.ui.theme.task_color
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
        mutableStateOf("You")
    }
    var chuc by remember {
        mutableStateOf("You")
    }
    val database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    database.child("user").addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            title = snapshot.child("name").getValue().toString()
            chuc = snapshot.child("chuc").getValue().toString()
            println(title)
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    })

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                    .background(bg_color)
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                Column {
                    greetingSection(title, chuc)
                    entertainment()
//                    LazyRow(modifier = Modifier.padding(horizontal = 5.dp)) {
//                        items(list_entertainment.size) {
//                            Box(
//                                modifier = Modifier
//                                    .padding(top = 50.dp)
//                                    .padding(horizontal = 5.dp)
//                                    .height(100.dp)
//                                    .width(80.dp)
//                                    .clip(RoundedCornerShape(10.dp))
//                                    .background(Beige1)
//                            ) {
//                                Text(
//                                    text = list_entertainment[it].title,
//                                    color = Color.White,
//                                    style = TextStyle(fontSize = 10.sp)
//                                )
//                            }
////                            Column {
////                                Box(
////                                    modifier = Modifier
////                                        .padding(top = 50.dp)
////                                        .padding(horizontal = 5.dp)
////                                        .height(100.dp)
////                                        .width(80.dp)
////                                        .clip(RoundedCornerShape(10.dp))
////                                        .background(Beige1)
////                                ) {
////
////                                }
////                                Text(
////                                    text = list_entertainment[it].title,
////                                    color = Color.White,
////                                    style = TextStyle(fontSize = 10.sp)
////                                )
////                            }
//                        }
//                    }
                }


            }
        },
        floatingActionButton = {
            Box(modifier = Modifier
                .clip(CircleShape)
                .background(add_color)
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
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
            )
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

@Composable
fun greetingSection(name: String, chuc: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .padding(horizontal = 5.dp)
    ) {
        Column {
            Text(
                text = "Hello $name,",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Text(text = chuc, color = fontText)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

