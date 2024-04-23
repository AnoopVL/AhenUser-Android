package com.avl.ahenuser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> implements PaymentResultListener  {

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

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(context, "Payment was successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(context, "Payment failed try again!", Toast.LENGTH_SHORT).show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView drivingSchoolName;
        TextView timeSlot;
        TextView status;
        Button payNow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            drivingSchoolName = itemView.findViewById(R.id.drivingSchoolName);
            timeSlot = itemView.findViewById(R.id.timeSlot);
            status = itemView.findViewById(R.id.status);
            payNow = itemView.findViewById(R.id.payNow);
        }
        public void bind(BookingRequests booking) {
            drivingSchoolName.setText(booking.getDsName());
            timeSlot.setText(booking.getTimeSlot());
            status.setText(booking.getStatus());

            if (booking.getStatus().equalsIgnoreCase("accepted")) {
                payNow.setVisibility(View.VISIBLE);
                payNow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startPayment();
                    }
                });
            } else {
                payNow.setVisibility(View.GONE);
            }

        }
    }
    private  void startPayment(){
        Checkout checkout = new Checkout();
        checkout.setImage(R.drawable.dsimg_trans);

//        final HistoryAdapter activity = this;
        final Activity activity = (Activity) context;
        try{
            JSONObject options = new JSONObject();
            options.put("name", R.string.app_name);
            options.put("descrpipton", "Payment for Driving session");
            options.put("send_sams_hash", true);
            options.put("allow_rotation", false);

            options.put("currency", "INR");
            options.put("amount", "100");

            JSONObject preFill = new JSONObject();
            preFill.put("email", " ");
            preFill.put("contact", " ");

            options.put("prefill", preFill);

            checkout.open(activity, options);

        }catch (Exception e){
            Toast.makeText(context, "Error in payment!"+e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
//    private void makepayment()
//    {
//        Checkout checkout = new Checkout();
//        getCheckout(checkout).setKeyID("rzp_test_mvu02siRvhe1YQ");
//
//        checkout.setImage(R.drawable.dsimg_trans);
//
//
//        final HistoryAdapter activity = this;
//
//        try {
//            JSONObject options = new JSONObject();
//
//            options.put("name", "Ahen");
//            options.put("description", "Payment for Driving session");
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
//            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
//            options.put("theme.color", "#DC143C");
//            options.put("currency", "INR");
//            options.put("amount", "100");//pass amount in currency subunits
//            options.put("Email", " ");
//            options.put("Contact"," ");
//            JSONObject retryObj = new JSONObject();
//            retryObj.put("enabled", true);
//            retryObj.put("max_count", 4);
//            options.put("retry", retryObj);
//
//            checkout.open(activity, options);
//
//        } catch(Exception e) {
//            Log.e("TAG", "Error in starting Razorpay Checkout", e);
//        }
//        private static Checkout getCheckout(Checkout checkout) {
//        return checkout;
//    }
//    }
}