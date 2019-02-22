package com.vivekrai.vivekraivaluetainment.valuetainment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    public  ActionBarDrawerToggle toggle;
    private BottomNavigationView navigationView;
    private PostsFragment postsFragment;
    private VideosFragment videosFragment;
    private StoreFragment storeFragment;
    private BooksFragment booksFragment;
    private FrameLayout frameLayout;
    private int id;
    private String[] to = {"vivekraideveloper@gmail.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frame_layout);
        navigationView = findViewById(R.id.bottom_nav);
        postsFragment = new PostsFragment();
        storeFragment = new StoreFragment();
        videosFragment = new VideosFragment();
        booksFragment = new BooksFragment();
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Valuetainment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, new PostsFragment());
        ft.addToBackStack("");
        ft.commit();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                id = item.getItemId();


                switch (id) {

                    case R.id.navigation_posts:
                        setFragment(postsFragment);
                        item.setEnabled(true);
                        item.setChecked(true);
                        break;
                    case R.id.navigation_videos:
                        setFragment(videosFragment);
                        item.setEnabled(true);
                        item.setChecked(true);
                        break;
                    case R.id.navigation_stores:
                        setFragment(storeFragment);
                        item.setEnabled(true);
                        item.setChecked(true);
                        break;
                    case R.id.navigation_books:
                        setFragment(booksFragment);
                        item.setEnabled(true);
                        item.setChecked(true);
                        break;

                }
                return false;
            }
        });

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.home:
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.aboutUs:
                Intent aboutIntent = new Intent(MainActivity.this, AboutUs.class);
                startActivity(aboutIntent);
                break;
            case R.id.help:
                final AlertDialog.Builder helpAlert = new AlertDialog.Builder(MainActivity.this);
                helpAlert.setIcon(R.drawable.valuetainment);
                helpAlert.setTitle("Help");
                helpAlert.setMessage("Valuetainment is your go to app for developing Entrepreneurship skillset, getting self motivation and all other stuff that can help you to strive ahead in your life. Navigate to the Posts, Videos, Stores and Books section for an action packed journey. ");
                helpAlert.setPositiveButton("Go Ahead", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent1);
                    }
                });
                helpAlert.setCancelable(false);
                helpAlert.show();
                break;
            case R.id.moreApps:
                Intent morIntent = new Intent(Intent.ACTION_VIEW);
                morIntent.setData(Uri.parse("https://play.google.com/store/search?q=pub:+Vivek+Rai&c=apps"));
                if (morIntent != null) {
                    startActivity(morIntent);
                }
                break;

            case R.id.contact:
                Intent conatctIntent = new Intent(Intent.ACTION_SEND);
                conatctIntent.setData(Uri.parse("mailto:"));
                conatctIntent.putExtra(Intent.EXTRA_EMAIL, to);
                conatctIntent.putExtra(Intent.EXTRA_SUBJECT, "Valuetainment feedback");
                conatctIntent.putExtra(Intent.EXTRA_TEXT, "");
                conatctIntent.setType("message/UTF-8");
                startActivity(Intent.createChooser(conatctIntent, "Please Choose your Email"));
                break;
            case R.id.terms:
                Intent termsIntent = new Intent(MainActivity.this, Terms.class);
                startActivity(termsIntent);
                break;
        }
        return true;
    }



    private void setFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.addToBackStack("");
        ft.commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Download Valuetainment, an app that is meant for developing Entrepreneurship skillset and provides self motivation that helps you to strive ahead!\n" + "https://play.google.com/store/apps/details?id=com.vivekrai.vivekraivaluetainment.valuetainment");
            startActivity(Intent.createChooser(shareIntent, "Share Using"));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();
    }

}
