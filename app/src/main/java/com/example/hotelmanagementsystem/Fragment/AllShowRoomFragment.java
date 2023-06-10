package com.example.hotelmanagementsystem.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.hotelmanagementsystem.Adapter.AllShowRoomAdapter;
import com.example.hotelmanagementsystem.Api.APIInterface;
import com.example.hotelmanagementsystem.Api.RetrofitApi;
import com.example.hotelmanagementsystem.Model.HotelAllRoomModel;
import com.example.hotelmanagementsystem.Model.AllRoomModel;
import com.example.hotelmanagementsystem.R;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllShowRoomFragment extends Fragment {

    GridView gridView;
    MKLoader twin_fishes_spinner;
    APIInterface api;

    List<HotelAllRoomModel> aGrid = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_allshowroom, container, false);

        gridView = view.findViewById(R.id.room_grid);

        twin_fishes_spinner = view.findViewById(R.id.twin_fishes_spinner);

        all_room_data();

        return view;
    }

    private void all_room_data() {

        api = RetrofitApi.getClient(getContext()).create(APIInterface.class);
        Call<AllRoomModel> loginModel = api.GetAllRoom();
        loginModel.enqueue(new Callback<AllRoomModel>() {
            @Override
            public void onResponse(Call<AllRoomModel> call, Response<AllRoomModel> response) {

                twin_fishes_spinner.setVisibility(View.GONE);

                aGrid = response.body().getAllRoomBookingData();

                AllShowRoomAdapter roomBookingAdapter = new AllShowRoomAdapter(getContext(), aGrid);
                gridView.setAdapter(roomBookingAdapter);
            }

            @Override
            public void onFailure(Call<AllRoomModel> call, Throwable t) {

                Toast.makeText(getContext(), "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
            }
        });

    }

}