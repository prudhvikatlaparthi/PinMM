package com.pru.pinmm.ui.authentication

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.navigation.NavigationView
import com.pru.pinmm.MyApplication
import com.pru.pinmm.MyApplication.Companion.getMyPreferences
import com.pru.pinmm.R
import com.pru.pinmm.databinding.ActivityMainBinding
import com.pru.pinmm.interfaces.DialogClickInterface
import com.pru.pinmm.ui.maps.MapsActivity
import com.pru.pinmm.utils.CommonUtils
import com.pru.pinmm.utils.CommonUtils.isPermissionGranted
import com.pru.pinmm.utils.CommonUtils.showAlertDialog
import com.pru.pinmm.utils.Constants.REQUEST_PHONE_STATE
import com.pru.pinmm.utils.LocationHelper
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private var toggle: ActionBarDrawerToggle? = null
    private var locationHelper: LocationHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getLocation()
        initViews()
        initializeNavigation()
        onClickListener()
        hideToolbar()
    }

    private fun initViews() {
        fetchTime()
        binding.tvIMEI.text = getSerialNumber()
        setupNavHeader()
    }

    private fun setupNavHeader() {
        val headerView = binding.navigationView.getHeaderView(0)
        headerView.findViewById<TextView>(R.id.tvHeaderName).text = getMyPreferences().serialNumber
        headerView.findViewById<TextView>(R.id.tvHeaderEmail).text = getMyPreferences().serialNumber
    }

    private fun fetchTime(): Job {
        return lifecycleScope.launchWhenStarted {
            while (true) {
                binding.tvTimeStamp.text = CommonUtils.getCurrentTimeStamp()
                delay(1000)
            }
        }
    }

    private fun getLocation() {
        if (locationHelper == null) {
            locationHelper = LocationHelper(this, binding.tvLatLong, this)
        }

        locationHelper?.fetchLocation()
        locationHelper?.setListener(object : LocationHelper.Location {
            override fun found(latitude: Double, longitude: Double) {
                val text =
                    "Latitude:-${getMyPreferences().latitude} " + " Longitude:-${getMyPreferences().longitude}"
                binding.tvLatLong.text = text

            }

            override fun start() {
            }
        })
    }

    private fun onClickListener() {
        binding.imgMenu.setOnClickListener { v: View? -> binding.drawerLayout.openDrawer(Gravity.LEFT) }
        binding.btnStartTrip.setOnClickListener {
            lifecycleScope.launch {
                while (true) {
                    locationHelper?.fetchLocation()
                    delay(15_000)
                }
            }
        }
    }

    private fun initializeNavigation() {
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle!!)
        toggle?.syncState()
        binding.navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.drawerLayout.closeDrawers()
        val id = item.itemId
        if (id == R.id.home) {
            startActivity(Intent(this, HomeActivity::class.java))
        } else if (id == R.id.settings) {
            startActivity(Intent(this, SettingsActivity::class.java))
        } else if (id == R.id.profile) {
            startActivity(Intent(this, ProfileActivity::class.java))
        } else if (id == R.id.map) {
            startActivity(Intent(this, MapsActivity::class.java))
        } else if (id == R.id.log_out) {
            val alertDialog = showAlertDialog(this,
                "Are you sure to Logout?",
                false,
                "Yes", "No", object : DialogClickInterface {
                    override fun positiveClick(dialog: DialogInterface) {
                        getMyPreferences().clearMyPreferences()
                        dialog.dismiss()
                        finish()
                    }

                    override fun negativeClick(dialog: DialogInterface) {
                        dialog.dismiss()
                    }
                })
            alertDialog.show()
        }
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        toggle?.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        locationHelper?.onActivityResult(requestCode, resultCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PHONE_STATE) {
            if (isPermissionGranted(grantResults)) {
                MyApplication.getMyPreferences().serialNumber = getSerialNumber()
            }
        }
        locationHelper?.onRequestPermissionsResult(requestCode, grantResults)

//        MyApplication.getMyPreferences().keyBaseUrl = edtBaseUrl.text.toString().trim
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            binding.drawerLayout.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }

    private fun getSerialNumber(): String {
        var serialNumber = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_PHONE_STATE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                serialNumber = try {
                    Build.getSerial()
                } catch (e: java.lang.Exception) {
                    Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
                }
            } else
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_PHONE_STATE),
                    REQUEST_PHONE_STATE
                )
        } else {
            serialNumber = Build.SERIAL
        }
        if (MyApplication.getMyPreferences().serialNumber.isEmpty()) {
            MyApplication.getMyPreferences().serialNumber = serialNumber
        }
        return serialNumber
    }
}