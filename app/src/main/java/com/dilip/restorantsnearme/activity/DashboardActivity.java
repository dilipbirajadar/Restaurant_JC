package com.dilip.restorantsnearme.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Bundle;

import com.dilip.restorantsnearme.R;
import com.dilip.restorantsnearme.utilities.LogUtils;
import com.dilip.restorantsnearme.utilities.Prefs;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;

import com.dilip.restorantsnearme.adapters.SectionsPagerAdapter;

import java.util.Objects;

/**
 * Created by Dilip Birajadar.
 */
public class DashboardActivity extends AppCompatActivity {


    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        ImageFilterButton filter = findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayFliterDialog(DashboardActivity.this);

            }
        });

    }

    private void displayFliterDialog(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.filter_dialog, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


            Button btnApply = alertDialog.findViewById(R.id.btn_apply);
            Button btnNo = alertDialog.findViewById(R.id.btn_no);
            RadioGroup rg = alertDialog.findViewById(R.id.rbGroub);

            rg.isSelected();

            btnApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = rg.getCheckedRadioButtonId();
                    if(id == R.id.rb_kilometer){
                        Prefs.getInstance().setMileFilter(false);
                    }else {
                        Prefs.getInstance().setMileFilter(true);
                    }
                    sectionsPagerAdapter.notifyDataSetChanged();
                    alertDialog.dismiss();
                }
            });

            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}


