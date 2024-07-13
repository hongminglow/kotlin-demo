package com.example.kotlindemo

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlindemo.databinding.ActivityMainBinding
import com.example.kotlindemo.ui.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isBackButtonVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initially set the state or manage it based on some condition
        isBackButtonVisible = false

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
//        val backButton = menu?.findItem(R.id.action_back)
        val actionView = menuItem?.actionView
        val tvDynamicLabel: TextView = actionView?.findViewById(R.id.tv_dynamic_label)!!
        val ivRefresh: ImageView = actionView.findViewById(R.id.iv_refresh)

        //update component value
//        backButton?.isVisible = isBackButtonVisible

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
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_back -> {
//                val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)
//                if (fragment is HomeFragment) {
//                    fragment.backToFirstView()
//                }
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)
                val currentFragment = navHostFragment?.childFragmentManager?.fragments?.firstOrNull()
                if (currentFragment is HomeFragment) {
                    currentFragment.backToFirstView()
                    updateBackButtonVisibility(false)
                    true
                } else {
                    false
                }
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    internal fun updateBackButtonVisibility(visible: Boolean) {
        //Alternative to show/hide custom back button
        //        isBackButtonVisible = visible
        //        invalidateOptionsMenu()

        //Android default back button in menu bar
        // calling the action bar
        val actionBar: ActionBar? = supportActionBar

        // showing the back button in action bar
        actionBar?.setDisplayHomeAsUpEnabled(visible)

    }
}