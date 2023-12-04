package com.example.note.domain

import com.example.note.data.task_item
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class operation{
    val database:DatabaseReference = FirebaseDatabase.getInstance().getReference()
    fun add(x: task_item){
        database.child("task").child(x.title).setValue(x)
    }

    fun delete(){

    }
}