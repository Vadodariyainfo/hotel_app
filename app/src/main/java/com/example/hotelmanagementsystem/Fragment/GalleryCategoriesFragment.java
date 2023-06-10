package com.example.hotelmanagementsystem.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelmanagementsystem.Adapter.AllShowRoomAdapter;
import com.example.hotelmanagementsystem.Adapter.CategoriesGalleryAdapter;
import com.example.hotelmanagementsystem.Api.APIInterface;
import com.example.hotelmanagementsystem.Api.RetrofitApi;
import com.example.hotelmanagementsystem.Model.AllRoomModel;
import com.example.hotelmanagementsystem.Model.CategoriesGalleryModel;
import com.example.hotelmanagementsystem.Model.HotelCategoriesGalleryModel;
import com.example.hotelmanagementsystem.R;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryCategoriesFragment extends Fragment {

    GridView grid_categories_gallery;
    MKLoader twin_fishes_spinner;
    APIInterface api;

    List<HotelCategoriesGalleryModel> aGrid = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_gallery_categories, container, false);

        grid_categories_gallery = view.findViewById(R.id.grid_categories_gallery);

        twin_fishes_spinner = view.findViewById(R.id.twin_fishes_spinner);

        all_categories_gallery_date();

        return view;
    }

    private void all_categories_gallery_date() {

        api = RetrofitApi.getClient(getContext()).create(APIInterface.class);
        Call<CategoriesGalleryModel> categoriesGalleryModel = api.GetCategoriesGalleryModel();
        categoriesGalleryModel.enqueue(new Callback<CategoriesGalleryModel>() {
            @Override
            public void onResponse(Call<CategoriesGalleryModel> call, Response<CategoriesGalleryModel> response) {

                twin_fishes_spinner.setVisibility(View.GONE);

                aGrid = response.body().getHotelCategoriesGalleryModel();

                CategoriesGalleryAdapter categoriesGalleryAdapter = new CategoriesGalleryAdapter(getContext(), aGrid);
                grid_categories_gallery.setAdapter(categoriesGalleryAdapter);
            }

            @Override
            public void onFailure(Call<CategoriesGalleryModel> call, Throwable t) {

                Toast.makeText(getContext(), "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
            }
        });

    }

}