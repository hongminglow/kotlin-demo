package com.example.kotlindemo

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlindemo.components.IconSwitcher
import com.example.kotlindemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

       /* setSupportActionBar(binding.toolbar)*/

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.btnChangeIcon.setOnClickListener {
            switchIcon("com.example.kotlindemo.BPMainActivity")
        }

        // Handle back button click on toolbar
//        binding.toolbar.setNavigationOnClickListener {
//            // Navigate back to LoginActivity
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish() // Close MainActivity
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)

        val menuItem = menu?.findItem(R.id.action_custom)
        val actionView = menuItem?.actionView

        val tvDynamicLabel: TextView = actionView?.findViewById(R.id.tv_dynamic_label)!!
        val ivRefresh: ImageView = actionView.findViewById(R.id.iv_refresh)

        // Set the dynamic text
        tvDynamicLabel.text = "RM53250"

        // Handle refresh button click
        ivRefresh.setOnClickListener {
            // Handle refresh action
        }

        // Handle custom button click
        actionView.setOnClickListener {
            // Handle custom button action
        }

        return true
        return true
    }

    private fun switchIcon(componentName: String) {
        val pm: PackageManager = applicationContext.packageManager

        // List of all alias component names
        val aliases = listOf(
            "com.example.kotlindemo.BPMainActivity",
            "com.example.kotlindemo.PassMainActivity"
            // Add more aliases as needed
        )

        // Disable all aliases
        for (alias in aliases) {
            pm.setComponentEnabledSetting(
                ComponentName(applicationContext, alias),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
        }

        // Enable the selected alias
        pm.setComponentEnabledSetting(
            ComponentName(applicationContext, componentName),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }
}