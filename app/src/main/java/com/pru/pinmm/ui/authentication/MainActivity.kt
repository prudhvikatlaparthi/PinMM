package com.pru.pinmm.ui.authentication

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
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
import com.pru.pinmm.model.payloads.LoginPayload
import com.pru.pinmm.model.response.LoginResponse
import com.pru.pinmm.model.response.VehicleItem
import com.pru.pinmm.remote.APIHelper
import com.pru.pinmm.ui.maps.MapsActivity
import com.pru.pinmm.utils.CommonUtils
import com.pru.pinmm.utils.CommonUtils.getCurrentTimeStamp
import com.pru.pinmm.utils.CommonUtils.isPermissionGranted
import com.pru.pinmm.utils.CommonUtils.showAlertDialog
import com.pru.pinmm.utils.Constants.REQUEST_PHONE_STATE
import com.pru.pinmm.utils.LocationHelper
import com.pru.pinmm.utils.ObjectHolder
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val vehiclesData : ArrayList<VehicleItem> = arrayListOf()
        val vehicleItem1 = VehicleItem(
            vehId = 1,
            vehicleRegNo = "11sdf4fsd",
            vehicleType = "sfs4",
            isSelected = false
        )
        val vehicleItem2 = VehicleItem(
            vehId = 2,
            vehicleRegNo = "22sdf4fsd",
            vehicleType = "sfs4",
            isSelected = false
        )
        val vehicleItem3 = VehicleItem(
            vehId = 3,
            vehicleRegNo = "33sdf4fsd",
            vehicleType = "sfs4",
            isSelected = false
        )
        val vehicleItem4 = VehicleItem(
            vehId = 4,
            vehicleRegNo = "44sdf4fsd",
            vehicleType = "sfs4",
            isSelected = false
        )

        vehiclesData.add(vehicleItem1)
        vehiclesData.add(vehicleItem2)
        vehiclesData.add(vehicleItem3)
        vehiclesData.add(vehicleItem4)

        MyApplication.getMyPreferences().keySessionToken = "sdfsdfsd"

        val payload = LoginPayload(emailId = "", password = "")
        APIHelper.repository.authenticateUser(payload).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.i("Prudhvi Log", "onResponse: $response")
                if (response.isSuccessful && response.body() != null) {
                    val loginResponse = response.body()!!
                    /*loginResponse.sessionToken?.let{
                        MyApplication.getMyPreferences().keySessionToken = it
                    }*/


                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.i("Prudhvi Log", "onFailure: $t")
            }

        })

        /*binding.btnStartTrip.setOnClickListener {

            ObjectHolder.resetVehicles()
            ObjectHolder.setVehicles(vehiclesData)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }*/
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
            /*lifecycleScope.launch {
                while (true) {
                    locationHelper?.fetchLocation()
                    delay(15_000)
                }
            }*/
            val selectedTimeStamp = getCurrentTimeStamp()
            val message = "sdfsfdewfe currentTimeStamp ${selectedTimeStamp}"
            val alertDialog = showAlertDialog(context = this, message =message,isCancelable = false, positiveBtnName = "Ok", negativeBtnName = "Cancel", dialogClickInterface = object :DialogClickInterface{
                override fun positiveClick(dialog: DialogInterface?) {
                    // API CAll

                }

                override fun negativeClick(dialog: DialogInterface?) {
                    dialog?.dismiss()
                }
            })
            alertDialog.show()
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