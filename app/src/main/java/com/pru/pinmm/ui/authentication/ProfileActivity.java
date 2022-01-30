package com.pru.pinmm.ui.authentication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.pru.pinmm.R;

public class ProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        showToolbarBackButton("Profile Activity");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}