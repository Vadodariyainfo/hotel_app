package com.example.hotelmanagementsystem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.hotelmanagementsystem.Activity.GalleryActivity;
import com.example.hotelmanagementsystem.Activity.NavigationActivity;
import com.example.hotelmanagementsystem.Model.HotelCategoriesGalleryModel;
import com.example.hotelmanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriesGalleryAdapter extends BaseAdapter {

    List<HotelCategoriesGalleryModel> categories_gallery = new ArrayList<>();

    private LayoutInflater mInflater;
    Context context;
    CardView cv_cardview;

    public CategoriesGalleryAdapter(Context context, List<HotelCategoriesGalleryModel> categoriesgallery_grid) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.categories_gallery = categoriesgallery_grid;
    }

    public int getCount() {
        return categories_gallery.size();
    }

    public Object getItem(int i) {
        return categories_gallery.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder {

        public ImageView iv_gallery;
        public TextView tv_name;
    }


    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewholder = null;

        if (view == null) {
            viewholder = new ViewHolder();
            view = mInflater.inflate(R.layout.gallery_gridview, null);
            viewholder.iv_gallery = view.findViewById(R.id.iv_gallery);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            cv_cardview = view.findViewById(R.id.cv_cardview);

            String cat_id;
            cat_id = categories_gallery.get(i).getCid();

            cv_cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, GalleryActivity.class);
                    intent.putExtra("cat_id",cat_id);
//                    Toast.makeText(context, cat_id, Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            });

            view.setTag(viewholder);

        } else {
            viewholder = (ViewHolder) view.getTag();
        }

        Glide.with(viewholder.iv_gallery.getContext())
                .load(""+categories_gallery.get(i).getCategoryImage())
                .into(viewholder.iv_gallery);

        viewholder.tv_name.setText(""+categories_gallery.get(i).getCategoryName());

        return view;
    }
}
