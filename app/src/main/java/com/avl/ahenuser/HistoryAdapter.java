package com.avl.ahenuser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<BookingRequests> bookings;
    @SuppressLint("RestrictedApi")
    private Context context;

    public HistoryAdapter( android.content.Context context, List<BookingRequests> bookings) {
        this.context = context;
        this.bookings = bookings;
    }




    public void setBookings(List<BookingRequests> bookings) {
        this.bookings = bookings;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_request_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        BookingRequests booking = bookings.get(position);
        holder.bind(booking);
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView drivingSchoolName;
        TextView timeSlot;
        TextView status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            drivingSchoolName = itemView.findViewById(R.id.drivingSchoolName);
            timeSlot = itemView.findViewById(R.id.timeSlot);
            status = itemView.findViewById(R.id.status);
        }
        public void bind(BookingRequests booking) {
            drivingSchoolName.setText(booking.getDsName());
            timeSlot.setText("Time Slot: " + booking.getTimeSlot());
            status.setText("Status: " + booking.getStatus());
        }
    }
}
