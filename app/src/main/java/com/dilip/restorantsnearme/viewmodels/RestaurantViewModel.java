package com.dilip.restorantsnearme.viewmodels;

import android.content.res.Resources;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.dilip.restorantsnearme.R;
import com.dilip.restorantsnearme.model.restaurantResponses.RestaurantResponse;
import com.dilip.restorantsnearme.network.ApiRepository;
import com.dilip.restorantsnearme.utilities.Prefs;

/**
 * Created by Dilip Birajadar.
 */

public class RestaurantViewModel extends ViewModel {

    private MutableLiveData<RestaurantResponse> mutableLiveData;
    private ApiRepository apiRepository;


    public void init(){
        if (mutableLiveData != null){
            return;
        }
        apiRepository = ApiRepository.getInstance();
        String location = Prefs.getInstance().getCurrentLat() +","+ Prefs.getInstance().getCurrentLng();
        mutableLiveData = apiRepository.getRestaurantListData(location,"5000","restaurant", "AIzaSyD7pCMDWN_nEfk1mgUE1tfAjCGZtrBbbIQ");

    }

    public LiveData<RestaurantResponse> getRestaurantRepository() {
        return mutableLiveData;
    }

}
