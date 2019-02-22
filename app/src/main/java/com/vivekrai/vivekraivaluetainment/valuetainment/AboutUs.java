package com.vivekrai.vivekraivaluetainment.valuetainment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AboutUs extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        toolbar = findViewById(R.id.aboutUsToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About US");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
