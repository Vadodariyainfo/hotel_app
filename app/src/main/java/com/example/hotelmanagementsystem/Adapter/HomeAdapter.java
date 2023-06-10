package com.example.hotelmanagementsystem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanagementsystem.Activity.AboutUsActivity;
import com.example.hotelmanagementsystem.Activity.NavigationActivity;
import com.example.hotelmanagementsystem.Activity.ProfileActivity;
import com.example.hotelmanagementsystem.Fragment.AllShowRoomFragment;
import com.example.hotelmanagementsystem.Fragment.ContactUsFragment;
import com.example.hotelmanagementsystem.Fragment.FacilitiesFragment;
import com.example.hotelmanagementsystem.Fragment.GalleryCategoriesFragment;
import com.example.hotelmanagementsystem.Fragment.LocationFragment;
import com.example.hotelmanagementsystem.Fragment.SettingFragment;
import com.example.hotelmanagementsystem.Model.HomeModel;
import com.example.hotelmanagementsystem.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    List<HomeModel> home;
    Context context;

    public HomeAdapter(Context context, List<HomeModel> product) {

        this.context = context;
        this.home = product;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {

        HomeModel homeModel = home.get(position);
//        holder.circle.setCardBackgroundColor(holder.itemView.getContext().getResources().getColor(homeModel.getCircle()));

//        Drawable bgbackground = holder.backgroung.getBackground();
//        bgbackground = DrawableCompat.wrap(bgbackground);
//        DrawableCompat.setTint(bgbackground, holder.itemView.getContext().getResources().getColor(homeModel.getCircle()));
//        holder.backgroung.setBackground(bgbackground);

        holder.image.setImageResource(homeModel.getImage());
        holder.name.setText(homeModel.getName());

        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(holder.getAdapterPosition()==0){

                    openFragment( new AllShowRoomFragment() );
                    NavigationActivity.tt_text.setText("Room");

                }else if(holder.getAdapterPosition()==1){

                    Intent intent = new Intent(context, ProfileActivity.class);
                    context.startActivity(intent);
                    NavigationActivity.tt_text.setText("Profile");

                }
                /*else if(holder.getAdapterPosition()==2){

                    openFragment( new LocationFragment() );
                    NavigationActivity.tt_text.setText("Location");

                }*/
                else if(holder.getAdapterPosition()==3){

                    openFragment( new FacilitiesFragment() );
                    NavigationActivity.tt_text.setText("Facilities");

                } else if(holder.getAdapterPosition()==4){

                    openFragment( new GalleryCategoriesFragment() );
                    NavigationActivity.tt_text.setText("Gallery");

                }else if(holder.getAdapterPosition()==5){

                    openFragment( new SettingFragment() );
                    NavigationActivity.tt_text.setText("Setting");

                } else if(holder.getAdapterPosition()==6){

                    openFragment( new ContactUsFragment() );
                    NavigationActivity.tt_text.setText("Contact Us");

                }else if(holder.getAdapterPosition()==7){

                    Intent intent = new Intent(context, AboutUsActivity.class);
                    context.startActivity(intent);
                    NavigationActivity.tt_text.setText("About US");

                }
            }
        } );

    }

    @Override
    public int getItemCount() {

        return home.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

       FrameLayout all_show_room;
        ImageView image;
        TextView name;


    public ViewHolder(@NonNull View itemView) {

            super(itemView);

            all_show_room = itemView.findViewById(R.id.all_show_room);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }

    void openFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
