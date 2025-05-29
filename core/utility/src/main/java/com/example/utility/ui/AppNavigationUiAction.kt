package com.example.utility.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.net.toUri

interface AppNavigationUiAction {
    fun navigateToCallApp(context: Context, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = "tel:$phoneNumber".toUri()
        }

        if (intent.resolveActivity(context.packageManager) != null) {
            try {
                context.startActivity(intent)
            } catch (e: SecurityException) {
                Toast.makeText(context, "Could not open dialer: Permission denied.", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(context, "Could not open dialer.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "No application available to make calls.", Toast.LENGTH_SHORT).show()
        }
    }

    fun navigateToEmail(context: Context, email: String, subject: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = "mailto:${Uri.encode(email)}".toUri()
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            if (subject.isNotEmpty()) {
                putExtra(Intent.EXTRA_SUBJECT, subject)
            }
        }

        if (intent.resolveActivity(context.packageManager) != null) {
            try {
                context.startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, "Could not open email app.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "No application available to send emails.", Toast.LENGTH_SHORT).show()
        }
    }
}