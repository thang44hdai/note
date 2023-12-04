package com.example.note.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.note.data.task_item

class viewmodel : ViewModel() {
    val list_do : MutableLiveData<task_item> = MutableLiveData()
//    fun get_data: MutableLiveData<task_item>{
//
//    }
}