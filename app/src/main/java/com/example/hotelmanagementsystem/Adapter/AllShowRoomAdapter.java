package com.example.hotelmanagementsystem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.hotelmanagementsystem.Activity.SingleRoomDetailsActivity;
import com.example.hotelmanagementsystem.Model.HotelAllRoomModel;
import com.example.hotelmanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class AllShowRoomAdapter extends BaseAdapter {

    List<HotelAllRoomModel> room_booking = new ArrayList<>();

    LayoutInflater mInflater;
    Context context;
    Button btn_viewroom;

    public AllShowRoomAdapter(Context context, List<HotelAllRoomModel> roombooking_grid){

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.room_booking = roombooking_grid;
    }

    public int getCount(){

        return room_booking.size();
    }

    public Object getItem(int i){

        return room_booking.get(i);
    }

    public long getItemId(int i){

        return i;
    }

    public static class ViewHolder{

        public ImageView image;
        public TextView price;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View getView(int i, View view, ViewGroup viewGroup){

        ViewHolder viewholder = null;

        if(view == null){

            viewholder = new ViewHolder();
            view = mInflater.inflate(R.layout.room_gridview,null);

            viewholder.image = view.findViewById(R.id.image);
            viewholder.price = view.findViewById(R.id.price);
            btn_viewroom = view.findViewById(R.id.btn_viewroom);

            String id;
            id = room_booking.get(i).getId();

            btn_viewroom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, SingleRoomDetailsActivity.class);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });

            view.setTag(viewholder);

        } else {

            viewholder = (ViewHolder) view.getTag();

        }

      viewholder.price.setText(""+room_booking.get(i).getRoomPrice());
        Glide.with(viewholder.image.getContext())
                .load(""+room_booking.get(i).getRoomImage())
//                .placeholder(R.drawable.account)
                .into(viewholder.image);

      return view;
    }
}