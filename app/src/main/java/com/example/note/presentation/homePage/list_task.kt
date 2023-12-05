package com.example.note.presentation.homePage

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.note.data.task_item
import com.example.note.domain.operation
import com.example.note.presentation.viewmodel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlin.math.log

@Composable
fun list_task() {
    val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference()

    val items = remember {
        mutableStateListOf<task_item>()
    }
    databaseReference.child("task").addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            items.clear()
            for (postSnapshot in dataSnapshot.children) {
                val date = postSnapshot.child("date").getValue<String>()
                val description = postSnapshot.child("description").getValue<String>()
                val time = postSnapshot.child("time").getValue<String>()
                val title = postSnapshot.child("title").getValue<String>()
                if (date != null && description != null && time != null && title != null) {
                    val item = task_item(date, description, time, title)
                    Log.i("Item quet duoc", item.toString())
                    items.add(item)
                }
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.w("Get Firebase fail", "loadPost:onCancelled", databaseError.toException())
        }
    })
    LazyColumn() {
        items(items) {
            task(it)
        }
    }
}
