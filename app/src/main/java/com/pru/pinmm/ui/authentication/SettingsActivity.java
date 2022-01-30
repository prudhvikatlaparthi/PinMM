package com.pru.pinmm.ui.authentication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.pru.pinmm.R;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        showToolbarBackButton("Settings Activity");

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}