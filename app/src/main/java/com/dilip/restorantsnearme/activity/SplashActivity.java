package com.dilip.restorantsnearme.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dilip.restorantsnearme.R;
import com.dilip.restorantsnearme.utilities.Constant;
import com.dilip.restorantsnearme.utilities.LogUtils;
import com.dilip.restorantsnearme.utilities.Prefs;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by Dilip Birajadar.
 */
public class SplashActivity extends Activity {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 101;
    private boolean isPermission;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        checkPermission();

        if(isPermission) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
                    finish();
                }
            }, Constant.SPLASH_TIME);

        }else {
            checkPermission();
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);

        } else {
            isPermission = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 101){
            isPermission = true;
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                Double currentLat = location.getLatitude();
                                Double currentLang = location.getLongitude();
                                LogUtils.errorLog("LAT: " + currentLat, "Long " + currentLang);
                                Prefs.getInstance().setCurrentLat(String.valueOf(currentLat));
                                Prefs.getInstance().setCurrentLng(String.valueOf(currentLang));
                                startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
                                finish();
                            }
                        }
                    });
        }else {
            checkPermission();
        }
    }
}
