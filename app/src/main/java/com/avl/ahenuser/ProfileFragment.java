package com.avl.ahenuser;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private TextView fullNameTextView;
    private TextView phone_TextView;
    private TextInputLayout fullName_TextInputLayout;
    private TextInputLayout email_TextInputLayout;
    private TextInputLayout phone_TextInputLayout;
    private TextInputLayout password_TextInputLayout;
    private TextInputEditText fullNameEditText;
    private TextInputEditText emailEditText;
    private TextInputEditText phoneEditText;
    private TextInputEditText passwordEditText;
    private Button updateButton;
    private Button signOut;
    private SharedPreferences sharedPreferences;

    public ProfileFragment() {
        // Required empty public constructor
    }
//    public static ProfileFragment newInstance(String fullName, String email, String phone, String password) {
//        ProfileFragment fragment = new ProfileFragment();
//        Bundle args = new Bundle();
//        args.putString("fullName", fullName);
//        args.putString("email", email);
//        args.putString("phone", phone);
//        args.putString("password", password);
//        fragment.setArguments(args);
//        return fragment;
//    }
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

        fullName_TextInputLayout = view.findViewById(R.id.fullName_TextInputLayout);
        email_TextInputLayout = view.findViewById(R.id.email_TextInputLayout);
        phone_TextInputLayout = view.findViewById(R.id.phone_TextInputLayout);
        password_TextInputLayout = view.findViewById(R.id.password_TextInputLayout);
        fullNameTextView = view.findViewById(R.id.fullName_textView);
        phone_TextView = view.findViewById(R.id.phone_textView);
        updateButton = view.findViewById(R.id.updateButton);
        signOut = view.findViewById(R.id.sign_out);

        fullNameEditText = (TextInputEditText) fullName_TextInputLayout.getEditText();
        emailEditText = (TextInputEditText) email_TextInputLayout.getEditText();
        phoneEditText = (TextInputEditText) phone_TextInputLayout.getEditText();
        passwordEditText = (TextInputEditText) password_TextInputLayout.getEditText();

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

        // Extracting data from arguments
        Bundle args = getArguments();
        if (args != null) {
            String fullName = args.getString("fullName");
            String email = args.getString("email");
            String phone = args.getString("phone");
            String password = args.getString("password");

            fullNameTextView.setText(fullName);
            fullNameEditText.setText(fullName);
            emailEditText.setText(email);
            phoneEditText.setText(phone);
            phone_TextView.setText(phone);
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



