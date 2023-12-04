package com.example.note.data

import androidx.annotation.DrawableRes

data class item(
    val title: String,
    @DrawableRes
    val icon: Int,
)