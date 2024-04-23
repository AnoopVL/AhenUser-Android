package com.avl.ahenuser;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private TextView fullName_textView;
    private TextView phone_TextView;
    private TextInputLayout fullName_TextInputLayout;
    private TextInputLayout email_TextInputLayout;
    private TextInputLayout phone_TextInputLayout;
    private TextInputLayout password_TextInputLayout;
    private TextInputEditText full_name_edittext;
    private TextInputEditText email_edittext;
    private TextInputEditText phone_edittext;
    private TextInputEditText passwordEditText;
    private Button updateButton;
    private Button signOut;
    private SharedPreferences sharedPreferences;

    public ProfileFragment() {
        // Required empty public constructor
    }

public static ProfileFragment newInstance(UserHelper userData) {
    ProfileFragment fragment = new ProfileFragment();
    Bundle args = new Bundle();
    args.putParcelable("userData", (Parcelable) userData);  // Assuming UserData implements Parcelable
    fragment.setArguments(args);
    return fragment;
}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        fullName_textView = view.findViewById(R.id.fullName_textView);
        fullName_TextInputLayout = view.findViewById(R.id.fullName_TextInputLayout);
        full_name_edittext = view.findViewById(R.id.full_name_edittext);
        email_TextInputLayout = view.findViewById(R.id.email_TextInputLayout);
        email_edittext = view.findViewById(R.id.email_edittext);
        phone_TextInputLayout = view.findViewById(R.id.phone_TextInputLayout);
        phone_edittext = view.findViewById(R.id.phone_edittext);
        signOut = view.findViewById(R.id.sign_out);

//        fullNameEditText = (TextInputEditText) fullName_TextInputLayout.getEditText();
//        emailEditText = (TextInputEditText) email_TextInputLayout.getEditText();
//        phoneEditText = (TextInputEditText) phone_TextInputLayout.getEditText();
//        passwordEditText = (TextInputEditText) password_TextInputLayout.getEditText();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userLogin", MODE_PRIVATE);
        String phoneNumber = sharedPreferences.getString("phoneNumber", "");

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(phoneNumber);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Check if the user data exists
                if (dataSnapshot.exists()) {
                    // Get the user data
                    String fullName = dataSnapshot.child("name").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String phone = dataSnapshot.child("phone").getValue(String.class);

                    // Set the retrieved data to the corresponding views
                    fullName_textView.setText(fullName);
                    full_name_edittext.setText(fullName);
                    email_edittext.setText(email);
                    phone_edittext.setText(phone);
                } else {
                    // Handle the case where the user data does not exist
                    Toast.makeText(getActivity(), "User data not found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear saved phone number from SharedPreferences
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userLogin", MODE_PRIVATE);
                sharedPreferences.edit().remove("phoneNumber").apply();

                // Go back to login screen
                Intent newIntent = new Intent(getActivity(), login.class);
                startActivity(newIntent);
                getActivity().finish();
            }
        });

        return view;
    }
}



