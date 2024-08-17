package com.example.kotlindemo.services

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import android.util.Log

class ChangeAppIconService: Service() {
    private val aliases = arrayOf(".BPMainActivity", ".PassMainActivity")

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onTaskRemoved(rootIntent: Intent?) {
        changeAppIcon()
        stopSelf()
    }

    fun changeAppIcon() {
        val sp = getSharedPreferences("GLOBAL_PREFERENCES", Context.MODE_PRIVATE)
    Log.d("shared preferences", "sp..${sp}")
        sp.getString("selectedApp", ".BPMainActivity").let { aliasName ->
            if (!aliasName.isNullOrEmpty() &&!isAliasEnabled(aliasName)) {
                setAliasEnabled(aliasName)
            }
        }
    }

    private fun isAliasEnabled(aliasName: String): Boolean {
        return packageManager.getComponentEnabledSetting(
            ComponentName(this, aliasName)
        ) == PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    }

    private fun setAliasEnabled(aliasName: String) {
        aliases.forEach {
            val action = if (it == aliasName)
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            else
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED

            packageManager.setComponentEnabledSetting(
                ComponentName(
                    this, aliasName
                ),
                action,
                PackageManager.DONT_KILL_APP
            )
        }
    }
}