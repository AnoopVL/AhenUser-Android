package com.avl.ahenuser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private LinearLayout bookingRequestContainer;
    private TextView noHistoryText;
    private List<BookingRequests> bookingRequests;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        bookingRequestContainer = rootView.findViewById(R.id.bookingRequestContainer);
        noHistoryText = rootView.findViewById(R.id.noHistoryText);
        bookingRequests = new ArrayList<>();

        // Access user ID (replace with your method to get the user ID)
//        String userId = /* get user ID */;

        // Firebase Database reference
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child("phone");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Get a list of booking request IDs associated with the user (if present)
                List<String> bookingRequestIds = (List<String>) snapshot.child("bookingRequestIds").getValue();
                if (bookingRequestIds != null && !bookingRequestIds.isEmpty()) {
                    // Fetch details of each booking request using the IDs
                    fetchBookingRequestDetails(bookingRequestIds);
                } else {
                    // No booking history found
                    showNoHistoryText();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return rootView;
    }
    private void addBookingRequestView(BookingRequests bookingRequest) {
        // Inflate the layout for the booking request item
        View bookingItemView = LayoutInflater.from(getContext()).inflate(R.layout.booking_request_item, bookingRequestContainer, false);

        // Get references to TextViews within the booking item layout
        TextView drivingSchoolName = bookingItemView.findViewById(R.id.drivingSchoolName);
        TextView timeSlot = bookingItemView.findViewById(R.id.timeSlot);
        TextView status = bookingItemView.findViewById(R.id.status);

        // Set text values for each TextView based on booking request data
        getDrivingSchoolName(bookingRequest.getDsName(), drivingSchoolName); // Asynchronous name retrieval

        timeSlot.setText(bookingRequest.getTimeSlot());
        status.setText(bookingRequest.getStatus());

        // Add the inflated view as a child to the bookingRequestContainer
        bookingRequestContainer.addView(bookingItemView);
    }
    private void showNoHistoryText() {
        bookingRequestContainer.setVisibility(View.GONE);
        noHistoryText.setVisibility(View.VISIBLE);
    }
    private void getDrivingSchoolName(String drivingSchoolName, final TextView drivingSchoolNameTextView) {
        // Access driving schools node
        DatabaseReference drivingSchoolsRef = FirebaseDatabase.getInstance().getReference().child("drivingSchool");

        // Perform a single value event to get the driving school data
        drivingSchoolsRef.child(drivingSchoolName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = (String) dataSnapshot.child("name").getValue();
                    drivingSchoolNameTextView.setText(name); // Update UI with retrieved name
                } else {
                    drivingSchoolNameTextView.setText("Driving School N/A"); // Handle case where driving school data is missing
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database errors
            }
        });
    }

}