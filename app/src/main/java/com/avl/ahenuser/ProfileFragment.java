package com.avl.ahenuser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private static TextView fullNameTextView;
    private static TextView emailTextView;
    private static TextView phoneTextView;
    private static TextInputEditText fullNameEditText;
    private static TextInputEditText emailEditText;
    private static TextInputEditText phoneEditText;
    private static TextInputEditText passwordEditText;
    private static Button updateButton;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String fullName, String email, String phone, String password) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString("fullName", fullName);
        args.putString("email", email);
        args.putString("phone", phone);
        args.putString("password", password);
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

        fullNameEditText = view.findViewById(R.id.full_name_edittext);
        emailEditText = view.findViewById(R.id.email_edittext);
        phoneEditText = view.findViewById(R.id.phone_edittext);
        passwordEditText = view.findViewById(R.id.password_edittext);
        updateButton = view.findViewById(R.id.updateButton);
//        profileImageView = view.findViewById(R.id.profile_img);
//        fullNameTextView = view.findViewById(R.id.full_name);
//        userNameTextView = view.findViewById(R.id.user_name);

        // Extracting data from arguments
        Bundle args = getArguments();
        if (args != null) {
            String fullName = args.getString("fullName");
            String email = args.getString("email");
            String phone = args.getString("phone");
            String password = args.getString("password");

            fullNameEditText.setText(fullName);
            emailEditText.setText(email);
            phoneEditText.setText(phone);
            passwordEditText.setText(password);
        }
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Sorry, try again later!!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}



