package com.example.hotelmanagementsystem.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelmanagementsystem.Fragment.AllShowRoomFragment;
import com.example.hotelmanagementsystem.Fragment.ContactUsFragment;
import com.example.hotelmanagementsystem.Fragment.FacilitiesFragment;
import com.example.hotelmanagementsystem.Fragment.GalleryCategoriesFragment;
import com.example.hotelmanagementsystem.Fragment.HomeFragment;
import com.example.hotelmanagementsystem.Fragment.SettingFragment;
import com.example.hotelmanagementsystem.Model.HotelLoginModel;
import com.example.hotelmanagementsystem.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Method;

public class NavigationActivity extends AppCompatActivity {

    DrawerLayout drawer_layout;
    Toolbar tool_bar;
    NavigationView navigation_view;
    ImageView three_line;
    public static TextView tt_text;

    String name,phone_no,email_id;
    View header;
    TextView tv_name,tv_email_id;

    SharedPreferences sharedPrefrences;
    SharedPreferences.Editor editor;

    ActionBarDrawerToggle toggle;
    boolean doubleBackToExitPressedOnce = false;

    private Method method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        three_line = findViewById(R.id.three_line);
        drawer_layout = findViewById(R.id.drawer_layout);
        tool_bar = findViewById(R.id.tool_bar);
        tt_text = findViewById(R.id.tv_text);
        navigation_view = findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawer_layout, tool_bar, R.string.Open, R.string.Close);
        toggle.setDrawerIndicatorEnabled(false);

        sharedPrefrences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPrefrences.edit();

        header = navigation_view.getHeaderView(0);

        tv_name = header.findViewById(R.id.tv_name);
        tv_email_id = header.findViewById(R.id.tv_email_id);

        name = sharedPrefrences.getString("name",name);
        email_id = sharedPrefrences.getString("email",email_id);

        tv_name.setText(name);
        tv_email_id.setText(email_id);

        three_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START);
                } else {
                    drawer_layout.openDrawer(GravityCompat.START);
                }
            }
        });

        name = getIntent().getStringExtra("name");
        phone_no = getIntent().getStringExtra("phone_no");
        email_id = getIntent().getStringExtra("email_id");

        navigation_view = findViewById(R.id.navigation_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new HomeFragment()).commit();
        tt_text.setText("Home");

        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.home) {

                    getSupportFragmentManager().beginTransaction().replace( R.id.frame_layout, new HomeFragment() ).commit();
                    tt_text.setText( "Home" );

                    drawer_layout.closeDrawer( GravityCompat.START );

                } else if (id == R.id.profile) {

                    Intent intent = new Intent( NavigationActivity.this, ProfileActivity.class );
                    startActivity( intent );

                    drawer_layout.closeDrawer( GravityCompat.START );

                } else if (id == R.id.room) {

                    getSupportFragmentManager().beginTransaction().replace( R.id.frame_layout, new AllShowRoomFragment() ).commit();
                    tt_text.setText( "All Room Details" );

                    drawer_layout.closeDrawer( GravityCompat.START );

                } else if (id == R.id.facilities) {

                    getSupportFragmentManager().beginTransaction().replace( R.id.frame_layout, new FacilitiesFragment() ).commit();
                    tt_text.setText( "Facilities" );

                    drawer_layout.closeDrawer( GravityCompat.START );

                } else if (id == R.id.gallery) {

                    getSupportFragmentManager().beginTransaction().replace( R.id.frame_layout, new GalleryCategoriesFragment() ).commit();
                    tt_text.setText( "Categories" );

                    drawer_layout.closeDrawer( GravityCompat.START );

                } else if (id == R.id.setting) {

                    getSupportFragmentManager().beginTransaction().replace( R.id.frame_layout, new SettingFragment() ).commit();
                    tt_text.setText( "Setting" );

                    drawer_layout.closeDrawer( GravityCompat.START );

                } else if (id == R.id.contact_us) {

                    getSupportFragmentManager().beginTransaction().replace( R.id.frame_layout, new ContactUsFragment() ).commit();
                    tt_text.setText( "Contact Us" );

                    drawer_layout.closeDrawer( GravityCompat.START );

                } else if (id == R.id.about_us) {

                    Intent intent2 = new Intent( NavigationActivity.this, AboutUsActivity.class );
                    startActivity( intent2 );

                    drawer_layout.closeDrawer( GravityCompat.START );

                } else if (id == R.id.log_out) {

                    logout();
                }

                return true;

            }

            public void logout() {

                SharedPreferences sharedPreferences = getSharedPreferences("mypref",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Toast.makeText(NavigationActivity.this, "Log Out SuccessFully", Toast.LENGTH_SHORT).show();

                editor.clear();
                editor.apply();
                Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        });
    }


    @Override
    public void onBackPressed() {
          if (doubleBackToExitPressedOnce) {
              finish();
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getResources().getString(R.string.Please_Click_Back_Again_To_Exit), Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);

        }
}