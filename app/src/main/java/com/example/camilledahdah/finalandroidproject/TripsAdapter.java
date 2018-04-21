package com.example.camilledahdah.finalandroidproject;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilledahdah.finalandroidproject.models.Trip;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by camilledahdah on 4/21/18.
 */



public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Trip> mList;

    public TripsAdapter(Context context, ArrayList<Trip> list){
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.trip_view, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Trip trip = mList.get(position);

        // Set item views based on your views and data model
        TextView tripText = holder.tripText;
        TextView observationsText = holder.observationsText;
        TextView weight = holder.weight;
        TextView volume = holder.volume;
        ImageView phone = holder.phone;
        ImageView profilePic = holder.profilePic;

        String text = "Camile" + " " + "El Dahdah" + " is travelling from " + trip.getFromLocation()
                + " to " + trip.getToLocation() + " on " + trip.getToDate();

        tripText.setText(text);
        observationsText.setText(trip.getObservations());
        weight.setText(trip.getWeight());
        volume.setText(trip.getCapacityVolume());



        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //profilePic.setImageResource();
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        TextView tripText;
        TextView observationsText;
        TextView weight;
        TextView volume;
        ImageView phone;
        ImageView profilePic;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);


            tripText = itemView.findViewById(R.id.trip_text);
            observationsText = itemView.findViewById(R.id.trip_observations);
            weight = itemView.findViewById(R.id.weight_trip);
            volume = itemView.findViewById(R.id.volume_trip);
            phone = itemView.findViewById(R.id.phone);
            profilePic = itemView.findViewById(R.id.profile_pic);


        }
    }





}
