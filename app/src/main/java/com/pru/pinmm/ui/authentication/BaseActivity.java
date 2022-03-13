package com.pru.pinmm.ui.authentication;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.pru.pinmm.R;

abstract public class BaseActivity extends AppCompatActivity {

    ProgressDialog dialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void setToolbar(Toolbar toolBar) {
        setSupportActionBar(toolBar);
    }

    public void setToolbar(Toolbar toolBar, String title) {
        setSupportActionBar(toolBar);
        setTitle(title);
    }

    public void setToolbar(Toolbar toolBar, int title) {
        setSupportActionBar(toolBar);
        setTitle(title);
    }

    public void showToolbarBackButton() {
        showToolbarBackButton(0);
    }

    public void showToolbarBackButton(int title) {
        if (title == 0) {
            title = R.string.app_name;
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }
        /*EditText baseedt = alrt.findviewby(R.id.sdfsd);
        EditText gps = alrt.findviewby(R.id.sdfsd);
        Button update = alrt.findviewby(R.id.sdfsd);
        if (TextUtils.isEmpty(MyApplication.getMyPreferences().getKeyBASEURL())){
            baseedt.setText(kBASEURL);
            MyApplication.getMyPreferences().setKeyBASEURL(kBASEURL);
        }else{
            baseedt.setText(MyApplication.getMyPreferences().getKeyBASEURL());
        }
        gps.setText(MyApplication.getMyPreferences().getgps());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.myPreferences.setKeyBASEURL(baseedt.getText().toString().trim());
                MyApplication.myPreferences.se(baseedt.getText().toString().trim());
                APIHelper.resetRepository();
            }
        });*/

    }

    public void showToolbarBackButton(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }

    }

    public void hideToolbar() {
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
    }
}
