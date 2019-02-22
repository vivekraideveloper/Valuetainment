package com.vivekrai.vivekraivaluetainment.valuetainment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BooksFragment extends Fragment implements BooksAdapter.OnItemClickListener {
    BooksAdapter adapter;
    private RecyclerView recyclerView;
    private BooksAdapter imageAdapter;
    private DatabaseReference databaseReference;
    private List<Upload> uploads;
    private ValueEventListener valueEventListener;
    private SwipeRefreshLayout swipeRefreshLayout;

    private InterstitialAd interstitialAd;
    private InterstitialAd interstitialAd2;
    private InterstitialAd interstitialAd3;



    public BooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_books, container, false);

        interstitialAd = new InterstitialAd(getContext());
        interstitialAd2 = new InterstitialAd(getContext());
        interstitialAd3 = new InterstitialAd(getContext());

        interstitialAd.setAdUnitId("ca-app-pub-7875006650320994/1161367888");
        interstitialAd2.setAdUnitId("ca-app-pub-7875006650320994/3270733892");
        interstitialAd3.setAdUnitId("ca-app-pub-7875006650320994/2401054099");

        AdRequest request = new AdRequest.Builder().build();
        interstitialAd.loadAd(request);
        interstitialAd2.loadAd(request);
        interstitialAd3.loadAd(request);


        swipeRefreshLayout = view.findViewById(R.id.swipe);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        swipeRefreshLayout.setColorSchemeColors(Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });

        recyclerView = view.findViewById(R.id.rvNumbers);
        recyclerView.setHasFixedSize(true);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(numberOfColumns, LinearLayoutManager.VERTICAL));

        uploads = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("books");
        databaseReference.keepSynced(true);

        imageAdapter = new BooksAdapter(getActivity(), uploads);
        recyclerView.setAdapter(imageAdapter);
        imageAdapter.setOnItemClickListener(this);


        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                uploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    upload.setMkey(postSnapshot.getKey());
                    uploads.add(upload);

                }
                imageAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


                Toast.makeText(getContext(), "Unable to Retrieve , some error occured!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onItemClick(int position) {

        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }

        Upload selectedItem = uploads.get(position);
        String imageUrl = selectedItem.getImageUrl();
        String name = selectedItem.getName();
        String description = selectedItem.getLikes();
        String url = selectedItem.getVideoId();
        String authorName = selectedItem.getAuthorName();
        Intent intent = new Intent(getActivity(), BooksActivity.class);
        intent.putExtra("imageUrl", imageUrl);
        intent.putExtra("name", name);
        intent.putExtra("description", description);
        intent.putExtra("url", url);
        intent.putExtra("authorName", authorName);
        startActivity(intent);

    }

    @Override
    public void onWhatEverClick(int position) {
        if (interstitialAd2.isLoaded()) {
            interstitialAd2.show();
        }

    }

    @Override
    public void onDeleteClick(int position) {
        if (interstitialAd3.isLoaded()) {
            interstitialAd3.show();
        }


    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ff2216'>Valuetainment </font>"));
////        MainActivity m = new MainActivity();
////        m.toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.red));
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Valuetainment </font>"));
////        MainActivity m = new MainActivity();
////        m.toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
//
//    }

}
