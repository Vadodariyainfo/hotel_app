package com.example.hotelmanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotelmanagementsystem.Model.HotelGalleryModel;
import com.example.hotelmanagementsystem.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

public class SliderGalleryAdapter extends RecyclerView.Adapter<SliderGalleryAdapter.ViewHolder> {

    private List<HotelGalleryModel> images;
    private Context ctx;


    public SliderGalleryAdapter(Context ctx, List<HotelGalleryModel> sliderGalleryList) {
        this.ctx = ctx;
        this.images = sliderGalleryList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( ctx ).inflate( R.layout.slider_gallery_layout, parent, false );
        return new ViewHolder( view );

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with( holder.iv_gallery_image.getContext() )
                .load( "" + images.get( position ).getWallpaperImage() )
                .into( holder.iv_gallery_image );
    }

    @Override
    public int getItemCount() {

        return images.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        PhotoView iv_gallery_image;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            iv_gallery_image = itemView.findViewById( R.id.iv_gallery_image );
        }

    }

}

