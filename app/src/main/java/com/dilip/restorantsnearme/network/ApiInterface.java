package com.dilip.restorantsnearme.network;

import com.dilip.restorantsnearme.model.restaurantResponses.RestaurantResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by Dilip Birajadar.
 */
public interface ApiInterface {
    @GET("nearbysearch/json?")
    Call<RestaurantResponse> getRestaurantList( @Query("location") String location,
                                                @Query("radius") String radius,
                                                @Query("type") String typeRestaurant,
                                                @Query("key") String apiKey);
}
