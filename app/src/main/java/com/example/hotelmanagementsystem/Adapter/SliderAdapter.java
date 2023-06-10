package com.example.hotelmanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotelmanagementsystem.Activity.SingleRoomDetailsActivity;
import com.example.hotelmanagementsystem.Model.SingleRoomGalleryImage;
import com.example.hotelmanagementsystem.R;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {

    private List<SingleRoomGalleryImage> images;
    private Context ctx;

    public SliderAdapter(Context ctx, List<SingleRoomGalleryImage> sliderList) {
        this.ctx = ctx;
        this.images = sliderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.slider_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(holder.images.getContext())
                .load(""+images.get(position).getImageName())
              .into(holder.images);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.iv_image);
        }
    }
}

