package com.dilip.restorantsnearme.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;


import android.content.Context;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dilip.restorantsnearme.R;
import com.dilip.restorantsnearme.model.restaurantResponses.Result;

import com.dilip.restorantsnearme.utilities.LogUtils;
import com.dilip.restorantsnearme.viewmodels.RestaurantViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Dilip Birajadar.
 */
public class MapsFragment extends Fragment {
    private RestaurantViewModel restaurantViewModel;
    private ArrayList<Result> resultArrayList = new ArrayList<>();
    private Marker marker;
    private List <MarkerOptions>  mrk = new ArrayList<>();


    public MapsFragment() {

    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

        /**
         * get View model
         */
        restaurantViewModel = ViewModelProviders.of(this).get(RestaurantViewModel.class);

        restaurantViewModel.init();

        restaurantViewModel.getRestaurantRepository().observe(this, restaurantResponse -> {
            List<Result> resultLis= restaurantResponse.getResults();
            resultArrayList.addAll(resultLis);

            SupportMapFragment mapFragment1 =
                    (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            if (mapFragment1 != null) {
                mapFragment1.getMapAsync(callback);
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LogUtils.errorLog("result array",resultArrayList.toString());
            if(resultArrayList!= null) {
                for (int i = 0; i < resultArrayList.size(); i++) {
                    LatLng melbourneLocation = new LatLng(resultArrayList.get(i).getGeometry().getLocation().getLat(), resultArrayList.get(i).getGeometry().getLocation().getLng());
                    marker = googleMap.addMarker(
                        new MarkerOptions()
                                .position(melbourneLocation)
                                .title(resultArrayList.get(i).getName())
                                .snippet(resultArrayList.get(i).getVicinity())


                );
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(resultArrayList.get(i).getGeometry().getLocation().getLat(), resultArrayList.get(i).getGeometry().getLocation().getLng()), 12.0f));
                }

            }else {
                LogUtils.errorLog("not come here","");
            }

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    AlertDialog alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.AppTheme)).create();
                    alertDialog.setTitle(marker.getTitle());
                    alertDialog.setMessage(marker.getSnippet());
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                    return false;
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

    }



}