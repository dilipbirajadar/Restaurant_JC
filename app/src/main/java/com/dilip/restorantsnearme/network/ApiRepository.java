package com.dilip.restorantsnearme.network;

import androidx.lifecycle.MutableLiveData;

import com.dilip.restorantsnearme.model.restaurantResponses.RestaurantResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by Dilip Birajadar.
 */
public class ApiRepository {
    private static ApiRepository apiRepository;
    private ApiInterface apiInterface;

    public static ApiRepository getInstance(){
        if (apiRepository == null){
            apiRepository = new ApiRepository();
        }
        return apiRepository;
    }

    public ApiRepository(){
        apiInterface = RetrofitService.cteateService(ApiInterface.class);
    }

    public MutableLiveData<RestaurantResponse> getRestaurantListData(String location, String radius,String type,String key){
        final MutableLiveData<RestaurantResponse> restaurantLiveData = new MutableLiveData<>();
        apiInterface.getRestaurantList(location, radius,type,key).enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call,
                                   Response<RestaurantResponse> response) {
                if (response.isSuccessful()){
                    restaurantLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                restaurantLiveData.setValue(null);
            }
        });
        return restaurantLiveData;
    }
}
