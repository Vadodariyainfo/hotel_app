package com.example.hotelmanagementsystem.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.hotelmanagementsystem.R;

import java.util.Objects;

public class ViewPagerHomeAdapter extends PagerAdapter {
    Context context;
    int[] images;
    LayoutInflater mLayoutInflater;


    public ViewPagerHomeAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return images.length;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }

    public Object instantiateItem(ViewGroup container, final int position) {

        View itemView = mLayoutInflater.inflate(R.layout.view_pager_home, container, false);

        ImageView view_pager_image = (ImageView) itemView.findViewById(R.id.view_pager_image);

        view_pager_image.setImageResource(images[position]);
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }
}
