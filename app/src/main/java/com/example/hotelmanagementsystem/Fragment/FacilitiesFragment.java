package com.example.hotelmanagementsystem.Fragment;

import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.hotelmanagementsystem.Adapter.FacilitiesAdapter;
import com.example.hotelmanagementsystem.Model.FacilitiesModel;
import com.example.hotelmanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class FacilitiesFragment extends Fragment {

    ListView list_view_facilities;

    List<FacilitiesModel> avsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_facilities, container, false);

        list_view_facilities = view.findViewById(R.id.list_view_facilities);

        FacilitiesModel avs;

        avs = new FacilitiesModel("Gym");
        avsList.add(avs);
        avs = new FacilitiesModel("Cellar");
        avsList.add(avs);
        avs = new FacilitiesModel("Balcony");
        avsList.add(avs);
        avs = new FacilitiesModel("Storage");
        avsList.add(avs);
        avs = new FacilitiesModel("Wine Cellar");
        avsList.add(avs);
        avs = new FacilitiesModel("Dryer");
        avsList.add(avs);
        avs = new FacilitiesModel("FirePlace");
        avsList.add(avs);
        avs = new FacilitiesModel("Deck");
        avsList.add(avs);
        avs = new FacilitiesModel("Front Yard");
        avsList.add(avs);
        avs = new FacilitiesModel("Washer");
        avsList.add(avs);

        FacilitiesAdapter facilitiesAdapter= new FacilitiesAdapter(getContext(),avsList);
        list_view_facilities.setAdapter(facilitiesAdapter);

        return view;
    }
}