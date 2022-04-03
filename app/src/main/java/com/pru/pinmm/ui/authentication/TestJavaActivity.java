package com.pru.pinmm.ui.authentication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pru.pinmm.R;

import java.util.ArrayList;
import java.util.List;

public class TestJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_java);

        List<String> city = new ArrayList<>();
        Log.e("TAG", "onCreate: "+city.size() );

        String[] mainTitle = new String[city.size()];
        if (mainTitle.length == 0) {
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        }
    }
}