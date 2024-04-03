package com.avl.ahenuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Button login, signUp, forgetPassword;
    TextInputLayout Phone, Password;

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            // Set the status bar color to transparent
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        signUp = findViewById(R.id.signUpHere);
        Phone = findViewById(R.id.phoneNo);
        Password = findViewById(R.id.Password);
        login = findViewById(R.id.login);
        forgetPassword = findViewById(R.id.forgetPassword);

        String userEnteredPhone = Phone.getEditText().getText().toString();
        String userEnteredPassword = Password.getEditText().getText().toString();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validatePhoneNo() | !validatePassword()){
                    Toast.makeText(login.this, "Invaild Phone or Password", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    isUser();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, signUp.class);
                startActivity(intent);
            }
        });

    }
    private Boolean validatePhoneNo() {
        String val = Phone.getEditText().getText().toString();
        if (val.isEmpty()) {
            Phone.setError("Field cannot be empty");
            return false;
        } else {
            Phone.setError(null);
            Phone.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = Password.getEditText().getText().toString();
        if (val.isEmpty()) {
            Password.setError("Field cannot be empty");
            return false;
        }
         else {
            Password.setError(null);
            Password.setErrorEnabled(false);
            return true;
        }
    }

    private void goToDashboard(String phone) {
        // Save phone number to SharedPreferences
        sharedPreferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        sharedPreferences.edit().putString("phoneNumber", phone).apply();

        Intent intent = new Intent(login.this, dashboard.class);
        startActivity(intent);
        finish();
    }
    private void isUser(){
        String userEnteredPhone = Phone.getEditText().getText().toString();
        String userEnteredPassword = Password.getEditText().getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkPhone = reference.orderByChild("phone").equalTo(userEnteredPhone);

        checkPhone.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Phone.setError(null);
                    Phone.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(userEnteredPhone).child("password").getValue(String.class);
                    if(passwordFromDB.equals(userEnteredPassword)){
                        Password.setError(null);
                        Password.setErrorEnabled(false);

                        String fullNameFromDB = snapshot.child(userEnteredPhone).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredPhone).child("email").getValue(String.class);
                        String phoneFromDB = snapshot.child(userEnteredPhone).child("phone").getValue(String.class);
                        goToDashboard(userEnteredPhone);

//                        Intent dataIntent = new Intent(login.this, dashboard.class);
////                        dataIntent.putExtra("fullName", fullNameFromDB);
////                        dataIntent.putExtra("email", emailFromDB);
////                        dataIntent.putExtra("phone", phoneFromDB);
////                        dataIntent.putExtra("password", passwordFromDB);
////                        dataIntent.putExtra("fragmentToLoad", "home");
//                        startActivity(dataIntent);
////                        Intent intent = new Intent(login.this, dashboard.class);
////                        startActivity(intent);
                    }
                    else {
                        Password.setError("Wrong Password");
                        Password.requestFocus();
                        Toast.makeText(login.this, "Wrong Password!!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Phone.setError("Enter valid phone number");
                    Phone.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}