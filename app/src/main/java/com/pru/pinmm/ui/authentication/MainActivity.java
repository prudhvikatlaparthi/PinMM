package com.pru.pinmm.ui.authentication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pru.pinmm.MyApplication;
import com.pru.pinmm.R;
import com.pru.pinmm.databinding.ActivityMainBinding;
import com.pru.pinmm.model.payloads.LoginPayload;
import com.pru.pinmm.model.response.LoginResponse;
import com.pru.pinmm.model.response.User;
import com.pru.pinmm.remote.APIHelper;
import com.pru.pinmm.utils.CommonUtils;

import interfaces.DialogClickInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.pru.pinmm.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pd = new ProgressDialog(this);
        pd.setMessage(getString(R.string.loading));
        binding.loginBtn.setText(MyApplication.getMyPreferences().getKeyLoggedUserId());

        binding.loginBtn.setOnClickListener(v -> {
            AlertDialog dialog = CommonUtils.showAlertDialog(this, "Success", true, "Ok", null, new DialogClickInterface() {
                @Override
                public void positiveClick(DialogInterface dialog) {
//                    Intent(sdfsdf)
                    dialog.dismiss();
                    //Intent -> Validate Mpin
                }

                @Override
                public void negativeClick(DialogInterface dialog) {
                    dialog.dismiss();
                }
            });
            dialog.show();
            MyApplication.getMyPreferences().setKeyLoggedUserId("sdfsdfsdf");
            LoginPayload payload = new LoginPayload();
            String pword = "sdfsf";
            payload.setEmailId("uName");
            payload.setPassword(Base64.encodeToString(pword.getBytes(), Base64.NO_WRAP));
            payload.setLoginMenuId("12313");

            Call<LoginResponse> call = APIHelper.getRepository().authenticateUser(payload);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    pd.dismiss();
                    Log.i("TAG", "onResponse: " + response);
                    if (response.isSuccessful() && response.body() != null) {
                        Log.i("TAG", "onResponse: " + response.body());
                        LoginResponse response1 = response.body();
                        String token = response1.getSessionToken();
                        MyApplication.getMyPreferences().setKeySessionToken(token);
                        User user = response1.getUser();
                        int id = user.getUserId();
                        MyApplication.getMyPreferences().setKeyUserId(id);
                        // MPincreate -> save mpin
                        // loginwithMPing -> validate -> MainActivity


                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    pd.dismiss();
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });

        Call<String> call = APIHelper.getRepository().getUserDetail(new LoginPayload());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        String mpin = "S" + "23";
        if (TextUtils.isEmpty(MyApplication.getMyPreferences().getKeyMPIN())) {
            // login

        } else {
            // validate
        }
    }
}