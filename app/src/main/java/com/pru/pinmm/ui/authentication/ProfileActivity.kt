package com.pru.pinmm.ui.authentication

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.pru.pinmm.databinding.ActivityProfileBinding
import com.pru.pinmm.databinding.SuccessAlertdialogLayoutBinding
import com.pru.pinmm.model.payloads.LoginPayload
import com.pru.pinmm.model.response.StartedResponse
import com.pru.pinmm.remote.APIHelper
import com.pru.pinmm.utils.CommonUtils.showCustomAlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : BaseActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showToolbarBackButton("Profile Activity")
        val payload = LoginPayload(emailId = "", password = "")
        APIHelper.repository.startTrip(payload).enqueue(object : Callback<StartedResponse> {
            override fun onResponse(
                call: Call<StartedResponse>,
                response: Response<StartedResponse>
            ) {
                Log.i("Prudhvi Log", "onResponse: $response")
                if (response.isSuccessful && response.body() != null) {
                    val startedResponse = response.body()!!
//                    if (startedResponse.success == true) {
                        Toast.makeText(this@ProfileActivity,startedResponse.message,Toast.LENGTH_SHORT).show()
//                    } else {
//
//                    }
                }
            }

            override fun onFailure(call: Call<StartedResponse>, t: Throwable) {
                Log.i("Prudhvi Log", "onFailure: $t")
            }

        })
        binding.startTrip.setOnClickListener {
            val alertBinding = SuccessAlertdialogLayoutBinding.inflate(layoutInflater)
            showCustomAlertDialog(this, alertBinding.root)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}