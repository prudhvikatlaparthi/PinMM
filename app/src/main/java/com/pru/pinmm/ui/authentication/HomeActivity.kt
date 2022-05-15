package com.pru.pinmm.ui.authentication

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import com.pru.pinmm.adapters.VehicleListAdapter
import com.pru.pinmm.databinding.ActivityHomeBinding
import com.pru.pinmm.model.response.VehicleItem
import com.pru.pinmm.utils.ObjectHolder

class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val vehiclesData = arrayListOf<VehicleItem>()
    private val adapter: VehicleListAdapter by lazy {
        VehicleListAdapter(vehicleList = vehiclesData, listener = { item ->
            vehiclesData.forEach {
                it.isSelected = it.vehId == item.vehId
            }
            adapter.notifyDataSetChanged()
        }, sendListSizeListener = { size ->
            if (size > 0) {
                binding.rcVehicles.isVisible = true
                binding.tvNoVehFound.isVisible = false
            } else {
                binding.rcVehicles.isVisible = false
                binding.tvNoVehFound.isVisible = true
            }
        })
    }
    private var selectedVehicle : VehicleItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showToolbarBackButton("Home Activity")
        vehiclesData.addAll(ObjectHolder.getVehicles())
        Log.i("Prudhvi Log", "onCreate: $vehiclesData")

        binding.rcVehicles.adapter = adapter


        binding.btnVehicles.setOnClickListener {
            binding.mainWrapper.isVisible = false
            binding.vehicleSelectWrapper.isVisible = true
        }
        binding.btnSelectVehicle.setOnClickListener {
            binding.vehicleSelectWrapper.isVisible = false
            binding.vehiclesWrapper.isVisible = true
        }
        binding.searchViewVeh.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        binding.btnVehicleDone.setOnClickListener {
            selectedVehicle = vehiclesData.filter {
                it.isSelected
            }.getOrNull(0)
            if (selectedVehicle == null) {
                Toast.makeText(this, "Please select", Toast.LENGTH_SHORT).show()
            } else {
                vehiclesData.forEach {
                    it.isSelected = false
                }
                adapter.notifyDataSetChanged()
                binding.searchViewVeh.setQuery("",false)
                binding.vehiclesWrapper.isVisible = false
                binding.vehicleSelectWrapper.isVisible = false
                binding.mainWrapper.isVisible = true
                binding.tvSelectedVeh.text = selectedVehicle?.vehicleRegNo?.plus(" (${selectedVehicle?.vehicleType})")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (binding.vehicleSelectWrapper.isVisible) {
            binding.vehicleSelectWrapper.isVisible = false
            binding.mainWrapper.isVisible = true
            binding.tvSelectedVeh.text =""
        } else if (binding.vehiclesWrapper.isVisible) {
            binding.vehicleSelectWrapper.isVisible = true
            binding.vehiclesWrapper.isVisible = false
        } else {
            super.onBackPressed()
        }
    }
}