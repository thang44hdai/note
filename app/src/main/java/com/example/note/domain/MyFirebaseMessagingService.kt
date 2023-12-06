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
import com.google.firebase.messaging.remoteMessage

const val channelId = "Notification_channel"
const val channelName = "com.example.note.domain"

class MyFirebaseMessagingService : FirebaseMessagingService() {
//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        if (remoteMessage.notification != null) {
//            generateNotification(
//                remoteMessage.notification!!.title!!,
//                remoteMessage.notification!!.body!!
//            )
//        }
//    }
//
//    fun getRemoteView(title: String, message: String): RemoteViews {
//        val remoteViews = RemoteViews("com.example.note.domain", R.layout.notification)
//        remoteViews.setTextViewText(R.id.tv_title, title)
//        remoteViews.setTextViewText(R.id.tv_content, message)
//        remoteViews.setImageViewResource(R.id.imgv_logo, R.drawable.background)
//        return remoteViews
//    }
//
//    fun generateNotification(title: String, content: String) {
//        val intent = Intent(this, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//        val pendingIntent = PendingIntent.getActivity(
//            this, 0, intent,
//            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
//        )
//        var builder: NotificationCompat.Builder =
//            NotificationCompat.Builder(applicationContext, channelId)
//                .setSmallIcon(R.drawable.background)
//                .setAutoCancel(true)
//                .setContentIntent(pendingIntent)
//
//        builder = builder.setContent(getRemoteView(title, content))
//        val notificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val notificationChannel =
//                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
//        notificationManager.notify(0, builder.build())
//    }


}