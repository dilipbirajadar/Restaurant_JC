package com.dilip.restorantsnearme.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dilip.restorantsnearme.R;
import com.dilip.restorantsnearme.adapters.RestaurantItemRecyclerViewAdapter;
import com.dilip.restorantsnearme.model.restaurantResponses.Result;
import com.dilip.restorantsnearme.viewmodels.RestaurantViewModel;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Created by Dilip Birajadar.
 *
 * A fragment representing a list of Items.
 */
public class RestaurantFragment extends Fragment {


    // viewModel
    private RestaurantViewModel restaurantViewModel;
    private ArrayList<Result> resultArrayList = new ArrayList<>();
    private RestaurantItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RestaurantFragment() {
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /**
         * get View model
         */
        restaurantViewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);

        restaurantViewModel.init();
        restaurantViewModel.getRestaurantRepository().observe(this, restaurantResponse -> {
            List<Result> resultList = restaurantResponse.getResults();
            resultArrayList.addAll(resultList);
            itemRecyclerViewAdapter.notifyDataSetChanged();
        });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        recyclerView = view.getRootView().findViewById(R.id.list);
        setAdapter();
        return view;
    }

    private void setAdapter() {
        if (itemRecyclerViewAdapter == null) {
            Collections.sort ( resultArrayList , new MileFromDeviceComp () );
            itemRecyclerViewAdapter = new RestaurantItemRecyclerViewAdapter(getActivity(), resultArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(itemRecyclerViewAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            itemRecyclerViewAdapter.notifyDataSetChanged();
        }
    }




    /**
     * Sort on rating or distance
     */
    class MileFromDeviceComp implements Comparator<Result> {
        @Override
        public int compare(Result e1 , Result e2) {
            if (e1.getRating() > e2.getRating ()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}