package com.vivekrai.vivekraivaluetainment.valuetainment;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class BooksActivity extends AppCompatActivity {

    private static final int MAX_LINES =1;
    private Toolbar toolbar;
    private ImageView imageView;
    private TextView name, authorName, description;
    private Button button;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        interstitialAd = new InterstitialAd(BooksActivity.this);
        interstitialAd.setAdUnitId("ca-app-pub-7875006650320994/9850376697");
        AdRequest request = new AdRequest.Builder().build();
        interstitialAd.loadAd(request);

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        authorName = findViewById(R.id.authorName);
        description = findViewById(R.id.description);
        toolbar = findViewById(R.id.aboutUsToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Buy Books");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Bundle bundle = getIntent().getExtras();
        Glide.with(BooksActivity.this).load(bundle.getString("imageUrl")).into(imageView);
        name.setText(bundle.getString("name"));
        authorName.setText(bundle.getString("authorName"));
        description.setText(bundle.getString("description"));
        ResizableCustomView.doResizeTextView(description, MAX_LINES, "Read More", true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isLoaded()){
                    interstitialAd.show();
                }
                Intent intent = new Intent(BooksActivity.this, WebActivity.class);
                intent.putExtra("url", bundle.getString("url"));
                startActivity(intent);
            }
        });

    }
}
