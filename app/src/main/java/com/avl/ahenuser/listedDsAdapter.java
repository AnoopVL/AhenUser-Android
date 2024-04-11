package com.avl.ahenuser;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class listedDsAdapter extends RecyclerView.Adapter<listedDsAdapter.listedDsHolder> {

    Context context;
    ArrayList<ListedDrivingSchools> list;

    public listedDsAdapter(Context context, ArrayList<ListedDrivingSchools> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public listedDsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.displayed_ds, parent, false);
        return new listedDsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull listedDsHolder holder, int position) {

        ListedDrivingSchools listedDrivingSchools = list.get(position);
        holder.dsName.setText(listedDrivingSchools.getDsName());
        holder.dsAddress.setText(listedDrivingSchools.getAddress());
        holder.dsPhone.setText(listedDrivingSchools.getPhone());
        holder.bookNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, selectedDS.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class listedDsHolder extends RecyclerView.ViewHolder{

        TextView dsName, dsAddress, dsPhone;
         MaterialButton bookNowBtn;
        public listedDsHolder(@NonNull View itemView) {
            super(itemView);

            dsName = itemView.findViewById(R.id.dsName);
            dsAddress = itemView.findViewById(R.id.dsAddress);
            dsPhone = itemView.findViewById(R.id.dsPhone);
            bookNowBtn = itemView.findViewById(R.id.bookNowBtn);

        }
    }
}
