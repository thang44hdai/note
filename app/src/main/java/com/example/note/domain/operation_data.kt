package com.example.note.domain

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.note.data.task_item
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class operation{
    val database:DatabaseReference = FirebaseDatabase.getInstance().getReference()
    fun add(x: task_item){
        database.child("task").child(x.title).setValue(x)
    }

    fun delete(x: task_item){
        database.child("task").child(x.title).removeValue()
    }

}