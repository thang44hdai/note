package com.example.note.presentation.homePage

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.note.data.task_item

@Composable
fun list_task() {
    LazyColumn() {
        items(15){
            task(task_item("04/12/2023", "Thực hành mạng máy tính", "19h00", "Thực hành không lấy điểm"))
        }

    }
}