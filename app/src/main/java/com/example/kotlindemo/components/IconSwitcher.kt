package com.example.kotlindemo.components

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager

object IconSwitcher {

    fun switchIcon(context: Context, componentName: String) {
        val pm: PackageManager = context.packageManager

        // List of all alias component names
        val aliases = listOf(
            "com.kotlindemo.BPMainActivity",
            "com.kotlindemo.PassMainActivity"
            // Add more aliases as needed
        )

        // Disable all aliases
        for (alias in aliases) {
            pm.setComponentEnabledSetting(
                ComponentName(context, alias),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
        }

        // Enable the selected alias
        pm.setComponentEnabledSetting(
            ComponentName(context, componentName),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }
}
