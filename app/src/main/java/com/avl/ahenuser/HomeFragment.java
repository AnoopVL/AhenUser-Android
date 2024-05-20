package com.avl.ahenuser;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    private Button searchNowButton;
    private Button findNowButton;
    private Button book2Button;
    private Button book3Button;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Find views by their IDs
        searchNowButton = view.findViewById(R.id.search_now);
//        findNowButton = view.findViewById(R.id.findNow);
        findNowButton = view.findViewById(R.id.findNow);
        book2Button = view.findViewById(R.id.book2);
        book3Button = view.findViewById(R.id.book3);

        // Set click listeners for the buttons
        searchNowButton.setOnClickListener(this);
        findNowButton.setOnClickListener(this);
        book2Button.setOnClickListener(this);
        book3Button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        // Handle button clicks
        if (v.getId() == R.id.search_now) {
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.findNow) {
            Intent intent = new Intent(getActivity(), listedDS.class);
            startActivity(intent);
        } else if (v.getId() == R.id.book2) {
            Toast.makeText(getActivity(), "Feature coming soon!!", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.book3) {
            Toast.makeText(getActivity(), "Feature coming soon!!", Toast.LENGTH_SHORT).show();
        }
    }
}