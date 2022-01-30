package com.pru.pinmm.ui.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;

import com.google.android.material.navigation.NavigationView;
import com.pru.pinmm.R;
import com.pru.pinmm.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeNavigation();
        onClickListener();
        hideToolbar();
    }

    private void onClickListener() {
        binding.imgMenu.setOnClickListener(v -> {
            binding.drawerLayout.openDrawer(Gravity.LEFT);
        });
    }

    private void initializeNavigation() {
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        binding.drawerLayout.closeDrawers();
        int id = item.getItemId();
        if (id == R.id.home) {
            startActivity(new Intent(this, HomeActivity.class));
        } else if (id == R.id.settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (id == R.id.profile) {
            startActivity(new Intent(this, ProfileActivity.class));
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        toggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            binding.drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
}