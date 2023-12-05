package com.example.note.domain

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.note.MainActivity
import com.example.note.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId = "Notification_channel"
const val channelName = "Notification_name"

class MyFirebaseMessagingService : FirebaseMessagingService() {
    
}