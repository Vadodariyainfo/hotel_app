package com.example.hotelmanagementsystem.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hotelmanagementsystem.Activity.AboutUsActivity;
import com.example.hotelmanagementsystem.R;

public class SettingFragment extends Fragment {

    TextView tv_share_application,tv_rate_application,tv_more_application,tv_privacy_policy,tv_about_us;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_setting, container, false);

        tv_share_application = view.findViewById(R.id.tv_share_application);
        tv_rate_application = view.findViewById(R.id.tv_rate_application);
        tv_more_application = view.findViewById(R.id.tv_more_application);
        tv_privacy_policy = view.findViewById(R.id.tv_privacy_policy);
        tv_about_us = view.findViewById(R.id.tv_about_us);

        tv_share_application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String app_url = " https://play.google.com/store/apps/details?id=my.example.javatpoint";
                intent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
                startActivity(intent);
            }
        });

        tv_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), AboutUsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}