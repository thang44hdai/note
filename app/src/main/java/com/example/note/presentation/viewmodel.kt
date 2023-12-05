package com.example.note.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.note.data.task_item
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class viewmodel : ViewModel() {
    val database: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    val items: MutableLiveData<MutableList<task_item>> = MutableLiveData()
    val dataList: MutableLiveData<MutableList<task_item>>
        get() {
            return items
        }
    fun get_data(): MutableLiveData<MutableList<task_item>> {
        database.child("task").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val date = postSnapshot.child("date").getValue<String>()
                    val description = postSnapshot.child("date").getValue<String>()
                    val time = postSnapshot.child("date").getValue<String>()
                    val title = postSnapshot.child("date").getValue<String>()
                    if (date != null && description != null && time != null && title != null) {
                        val item = task_item(date, description, time, title)
                        Log.i("item quet duoc", item.toString())
                        items.value?.add(item)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("Get Firebase fail", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
        return items
    }
}