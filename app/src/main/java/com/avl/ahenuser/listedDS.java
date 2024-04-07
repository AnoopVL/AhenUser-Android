package com.avl.ahenuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class listedDS extends AppCompatActivity {

    private DatabaseReference drivingSchoolRef;
    private TextView drivingSchoolName;
    private TextView drivingSchoolAddress;
    private TextView drivingSchoolPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listed_ds);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            // Set the status bar color to transparent
            window.setStatusBarColor(Color.TRANSPARENT);
        }

//        drivingSchoolName = findViewById(R.id.drivingSchoolName);
//        drivingSchoolAddress = findViewById(R.id.drivingSchoolAddress);
//        drivingSchoolPhone = findViewById(R.id.drivingSchoolPhone);
//        drivingSchoolRef = FirebaseDatabase.getInstance().getReference().child("drivingSchool");
//        drivingSchoolRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    ListedDrivingSchools listedDrivingSchools = snapshot.getValue(ListedDrivingSchools.class);
//                    drivingSchoolName.setText(listedDrivingSchools.getName());
//                    drivingSchoolAddress.setText(listedDrivingSchools.getAddress());
//                    drivingSchoolPhone.setText(listedDrivingSchools.getPhoneNumber());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }
}