package com.example.note.domain

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.note.presentation.navigation
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun notiPermission(context: Context) {
    val permissionState =
        rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)
    LaunchedEffect(key1 = Unit) {
        permissionState.launchPermissionRequest()
    }
    if (permissionState.status.isGranted) {
        navigation()
    } else {
//        if(permissionState.status.shouldShowRationale){
//            Nếu từ chối quyền
//        }else{
//            Nếu không ấn chọn bất kì button cho phép/ không cho phép
//        }
        Box(modifier = Modifier.fillMaxSize()) {
            Button(
                onClick = {
                    val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                    intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                    context.startActivity(intent)
                },
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(text = "Đề nghị đồng chí cấp quyền truy cập thông báo bằng cách ấn vào nút này")
            }
        }
    }

}