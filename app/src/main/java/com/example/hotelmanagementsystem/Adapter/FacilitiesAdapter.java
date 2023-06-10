package com.example.hotelmanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotelmanagementsystem.Model.FacilitiesModel;
import com.example.hotelmanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class FacilitiesAdapter extends BaseAdapter{

    List<FacilitiesModel> team = new ArrayList<>();
    private LayoutInflater mInflater;
    Context context;

    public FacilitiesAdapter(Context context, List<FacilitiesModel> teamList){

        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.team = teamList;
    }
    public int getCount(){
        return team.size();
    }

    public Object getItem(int i){
        return team.get(i);
    }

    public long getItemId(int i){
        return i;
    }

    public static class ViewHolder{

        TextView facilities_name;
        ImageView shape_image;
    }

    public View getView(int i, View view, ViewGroup viewGroup){

        ViewHolder viewHolder = null;

        if(view == null){

            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.facilities,null);

            viewHolder.facilities_name = view.findViewById(R.id.facilities_name);
            viewHolder.shape_image = view.findViewById(R.id.shape_image);

            view.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.facilities_name.setText(team.get(i).getFacilities_name());

        return view;
    }

}
