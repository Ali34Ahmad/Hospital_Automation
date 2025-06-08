package com.example.navigation.extesion

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.net.toUri

fun Context.navigateToCallApp(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = "tel:$phoneNumber".toUri()
    }

    if (intent.resolveActivity(packageManager) != null) {
        try {
           startActivity(intent)
        } catch (e: SecurityException) {
            Toast.makeText(this, "Could not open dialer: Permission denied.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Could not open dialer.", Toast.LENGTH_SHORT).show()
        }
    } else {
        Toast.makeText(this, "No application available to make calls.", Toast.LENGTH_SHORT).show()
    }
}

fun Context.navigateToEmailApp(email: String){
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = "mailto:${Uri.encode(email)}".toUri()
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
    }

    if (intent.resolveActivity(packageManager) != null) {
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Could not open email app.", Toast.LENGTH_SHORT).show()
        }
    } else {
        Toast.makeText(this, "No application available to send emails.", Toast.LENGTH_SHORT).show()
    }
}