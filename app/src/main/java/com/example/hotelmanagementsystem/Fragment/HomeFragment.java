package com.example.hotelmanagementsystem.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hotelmanagementsystem.Adapter.HomeAdapter;
import com.example.hotelmanagementsystem.Adapter.ViewPagerHomeAdapter;
import com.example.hotelmanagementsystem.Model.HomeModel;
import com.example.hotelmanagementsystem.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    RecyclerView recyclerview;
    GridLayoutManager Orientation;

    ImageView call, whatsapp, facebook, instagram;

    ViewPager view_pager;
    ViewPagerHomeAdapter viewPagerHomeAdapter;
    int[] images = {R.drawable.hotel1,R.drawable.hotel2,R.drawable.hotel3,R.drawable.hotel4,R.drawable.hotel5};

    List<HomeModel> aRecycler = new ArrayList<>();
    int NUM_PAGES = 0;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);



        recyclerview = view.findViewById(R.id.home_recycler);
//        all_show_room = view.findViewById(R.id.all_show_room);

        call = view.findViewById(R.id.call);
        whatsapp = view.findViewById(R.id.whatsapp);
        facebook = view.findViewById(R.id.facebook);
        instagram = view.findViewById(R.id.instagram);


        view_pager = view.findViewById(R.id.view_pager);
        viewPagerHomeAdapter = new ViewPagerHomeAdapter(getContext(),images);
        view_pager.setAdapter(viewPagerHomeAdapter);

        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {

                if (currentPage == NUM_PAGES-1) {
                    currentPage = 0;
                }
                view_pager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

//        room_frame_layout = view.findViewById( R.id.home_fragment);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callShare(""+view);
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                whatsAppShare("" + view);
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(getOpenFacebookIntent());
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instagramShare(""+view);
            }
        });

        HomeModel a;

        a = new HomeModel("1", R.drawable.room, "Room");
        aRecycler.add(a);
        a = new HomeModel("2", R.drawable.account, "Profile");
        aRecycler.add(a);
        a = new HomeModel("3", R.drawable.location, "Location");
        aRecycler.add(a);
        a = new HomeModel("4", R.drawable.facilities, "Facilities");
        aRecycler.add(a);
        a = new HomeModel("5", R.drawable.image, "Gallery");
        aRecycler.add(a);
        a = new HomeModel("6", R.drawable.setting, "Setting");
        aRecycler.add(a);
        a = new HomeModel("7", R.drawable.phone, "Contact Us");
        aRecycler.add(a);
        a = new HomeModel("8", R.drawable.about_us, "About Us");
        aRecycler.add(a);

        HomeAdapter HomeAdapter = new HomeAdapter(getContext(), aRecycler);

        Orientation = new GridLayoutManager(getContext(), 2);
        recyclerview.setLayoutManager(Orientation);

        recyclerview.setAdapter(HomeAdapter);

        return view;
    }

    public void callShare(String name){

        Uri uri = Uri.parse("tel: 7624066500");
        Intent call = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(call);
    }

    public void whatsAppShare(String name) {

        Uri uri = Uri.parse("smsto: +91 7624066500");
        Intent whatsapp = new Intent(Intent.ACTION_SENDTO, uri);
        whatsapp.setPackage("com.whatsapp");
        startActivity(whatsapp);
    }

    public Intent getOpenFacebookIntent() {

        Uri uri = Uri.parse("https://www.facebook.com/ankit.senjaliya.395");
        Intent facebook = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(facebook);

        return facebook;
    }

    public void instagramShare(String name) {

        Uri uri = Uri.parse("https://www.instagram.com/mr._a_s__/");
        Intent instagram = new Intent(Intent.ACTION_VIEW, uri);
        instagram.setPackage("com.instagram.android");
        startActivity(instagram);
    }

}