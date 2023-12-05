package com.example.note.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.note.data.task_item
import com.example.note.domain.operation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun add_note(
    nav: NavController
) {
    var dateController by remember {
        mutableStateOf("")
    }
    var titleController by remember {
        mutableStateOf("")
    }
    var descriptionController by remember {
        mutableStateOf("")
    }
    var timeController by remember {
        mutableStateOf("")
    }
    val operation = operation()

    Box(
        modifier = Modifier
            .background(Color.White),
    ) {
        Button(onClick = { nav.popBackStack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
        Box(modifier = Modifier.align(Alignment.TopCenter)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextField(
                    value = titleController,
                    onValueChange = {
                        titleController = it
                    },
                    label = {
                        Text(text = "Title:")
                    },
                    modifier = Modifier.padding(2.5.dp)

                )
                TextField(
                    value = timeController,
                    onValueChange = {
                        timeController = it
                    },
                    label = {
                        Text(text = "Time:")
                    },
                    modifier = Modifier.padding(2.5.dp)
                )
                TextField(
                    value = dateController,
                    onValueChange = {
                        dateController = it
                    },
                    label = {
                        Text(text = "Date:")
                    },
                    modifier = Modifier.padding(2.5.dp)
                )
                TextField(
                    value = descriptionController,
                    onValueChange = {
                        descriptionController = it
                    },
                    label = {
                        Text(text = "Description (Có thể không điền):")
                    },
                    modifier = Modifier.padding(2.5.dp)

                )
                Button(onClick = {
                    val x = task_item(
                        dateController,
                        descriptionController,
                        timeController,
                        titleController
                    )
                    operation.add(x)
                    nav.popBackStack()
                    dateController = ""
                    timeController = ""
                    timeController = ""
                    descriptionController = ""
                }) {
                    Text(text = "Save")
                }
            }
        }

    }
}