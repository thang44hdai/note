package com.example.note.presentation.homePage

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.note.data.EntertainmentItem
import com.example.note.ui.theme.Beige1
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

@Composable
fun entertainment() {
    val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference()

    val items = remember {
        mutableStateListOf<EntertainmentItem>()
    }

    val context = LocalContext.current
    databaseReference.child("entertainment").addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            items.clear()
            for (postSnapshot in dataSnapshot.children) {
                val image = postSnapshot.child("image").getValue<String>()
                val link = postSnapshot.child("link").getValue<String>()
                val title = postSnapshot.child("title").getValue<String>()
                if (image != null && link != null && title != null) {
                    val item = EntertainmentItem(link = link, title = title, image = image)
                    Log.i("Entertainment", item.toString())
                    items.add(item)
                }
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.w("Get Firebase fail", "loadPost:onCancelled", databaseError.toException())
        }
    })

    LazyRow(modifier = Modifier.padding(horizontal = 5.dp)) {
        items(items) {
            val intent = remember {
                Intent(Intent.ACTION_VIEW, Uri.parse(it.link))
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .padding(horizontal = 5.dp)
                        .height(100.dp)
                        .width(60.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Beige1)
                        .clickable {
                            context.startActivity(intent)
                        }
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(it.image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(
                    text = it.title,
                    color = Color.White,
                    style = TextStyle(fontSize = 10.sp)
                )
            }

        }
    }

}
