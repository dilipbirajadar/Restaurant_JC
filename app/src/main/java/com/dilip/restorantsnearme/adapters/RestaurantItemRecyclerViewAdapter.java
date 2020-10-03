package com.dilip.restorantsnearme.adapters;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dilip.restorantsnearme.R;
import com.dilip.restorantsnearme.model.restaurantResponses.Result;
import com.dilip.restorantsnearme.utilities.LogUtils;
import com.dilip.restorantsnearme.utilities.Prefs;
import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Dilip Birajadar.
 * {@link RecyclerView.Adapter} that can display a {@link Result}.
 *
 */
public class RestaurantItemRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantItemRecyclerViewAdapter.ViewHolder> {

    private final List<Result> mValues;
    private Context context;

    public RestaurantItemRecyclerViewAdapter(FragmentActivity activity, List<Result> items) {
        mValues = items;
        context = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String distanceType= " Km";
        if(Prefs.getInstance().isMileFilter()){
            distanceType = " Mile";
        }else {
             distanceType = " Km";
        }
        holder.mItem = mValues.get(position);
        holder.restaurant_name.setText(mValues.get(position).getName());
        holder.restaurant_address.setText(mValues.get(position).getVicinity());
        holder.restaurant_review.setText(String.valueOf(mValues.get(position).getRating()));
        holder.restaurant_distance.setText(String.valueOf(CalculationByDistance( new LatLng( Double.parseDouble(Prefs.getInstance().getCurrentLat()) ,Double.parseDouble(Prefs.getInstance().getCurrentLng())),  new LatLng(mValues.get(position).getGeometry().getLocation().getLat(), mValues.get(position).getGeometry().getLocation().getLng()) )).concat(distanceType));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView restaurant_name;
        public final TextView restaurant_address;
        public final TextView restaurant_review;
        public final TextView restaurant_distance;

        public Result mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            restaurant_name = (TextView) view.findViewById(R.id.restaurant_name);
            restaurant_address = (TextView) view.findViewById(R.id.restaurant_address);
            restaurant_review = (TextView) view.findViewById(R.id.restaurant_review);
            restaurant_distance = (TextView) view.findViewById(R.id.restaurant_distance);

        }

    }

    /**
     *
     * @param StartP
     * @param EndP
     * @return
     * calculate distance
     */
    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        boolean isMile;
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.parseInt(newFormat.format(km));
        double meter = valueResult % 1000;
        double mile = Double.parseDouble(newFormat.format(kmInDec * 0.62137));
        int meterInDec = Integer.parseInt(newFormat.format(meter));
        //LogUtils.errorLog("Radius Value", "" + valueResult + "   KM  " + kmInDec + " Meter   " + meterInDec);

        if(Prefs.getInstance().isMileFilter()){
            return mile;
        }else {
            return kmInDec;
        }
        //return Double.parseDouble(newFormat.format(Radius * c));
        //return kmInDec;
        //return mile;
    }
}