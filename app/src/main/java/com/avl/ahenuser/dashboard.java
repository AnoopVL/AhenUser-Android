package com.avl.ahenuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.avl.ahenuser.databinding.ActivityDashboardBinding;

public class dashboard extends AppCompatActivity {

    ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        replaceFragment(new  HomeFragment());
        setContentView(binding.getRoot());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            ((Window) window).getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.history) {
                replaceFragment(new HistoryFragment());
            } else if (item.getItemId() == R.id.profile) {
                Bundle extras = getIntent().getExtras();
                if (extras != null && extras.containsKey("fullName") && extras.containsKey("email")
                        && extras.containsKey("phone") && extras.containsKey("password")) {
                    String fullName = extras.getString("fullName");
                    String email = extras.getString("email");
                    String phone = extras.getString("phone");
                    String password = extras.getString("password");

                    // Pass user data to a temporary variable (or create a User class)
                    UserHelper userData = new UserHelper(fullName, email, phone, password);  // Assuming UserData class exists

                    replaceFragment(ProfileFragment.newInstance(userData));
                } else {
                    // Handle the case when user data is not available
                    // You can display an error message or take appropriate action
                }
            }
            return true;
        });
    }

//        Bundle extras = getIntent().getExtras();
//        if (extras != null && extras.containsKey("fullName") && extras.containsKey("email")
//                && extras.containsKey("phone") && extras.containsKey("password")) {
//            String fullName = extras.getString("fullName");
//            String email = extras.getString("email");
//            String phone = extras.getString("phone");
//            String password = extras.getString("password");
//
//            UserHelper userData = new UserHelper(fullName, email, phone, password);  // Assuming UserData class exists
//            String fragmentToLoad = extras.getString("fragmentToLoad");
//            if (fragmentToLoad != null) {
//                if (fragmentToLoad.equals("home")) {
//                    replaceFragment(new HomeFragment());
//                } else if (fragmentToLoad.equals("history")) {
//                    replaceFragment(new HistoryFragment());
//                } else if (fragmentToLoad.equals("profile")) {
//                    replaceFragment(ProfileFragment.newInstance(userData));
//                }
//            } else {
//                // Load HomeFragment by default
//                replaceFragment(new HomeFragment());
//            }
//        }
//            else {
//                // Load HomeFragment by default
//                replaceFragment(new HomeFragment());
//            }

            // Pass user data to a temporary variable (or create a User class)
//            UserHelper userData = new UserHelper(fullName, email, phone, password);  // Assuming UserData class exists
//            ProfileFragment profileFragment = ProfileFragment.newInstance(userData);
//            binding.bottomNavigation.setOnItemSelectedListener(item -> {
//            if (item.getItemId() == R.id.home) {
//                replaceFragment(new HomeFragment());
//            } else if (item.getItemId() == R.id.history) {
//                replaceFragment(new HistoryFragment());
//            } else if (item.getItemId() == R.id.profile) {
//                replaceFragment(ProfileFragment.newInstance(userData));
//            }            return true;
//            });




            // Now, based on bottom navigation selection, replace fragments
//            if (binding.bottomNavigation.getSelectedItemId() == R.id.home) {
//                replaceFragment(new HomeFragment());  // Pass userData to HomeFragment constructor (if needed)
//            } else if (binding.bottomNavigation.getSelectedItemId() == R.id.history) {
//                replaceFragment(new HistoryFragment());
//            } else if (binding.bottomNavigation.getSelectedItemId() == R.id.profile) {
//                replaceFragment(ProfileFragment.newInstance(userData));  // Pass userData to ProfileFragment constructor
//            }
//        else {
//                // If no user data, load HomeFragment by default
//                replaceFragment(new HomeFragment());
//                }

////        replaceFragment(new  HomeFragment());
//        Bundle extras = getIntent().getExtras();
//        if (extras != null && extras.containsKey("fullName") && extras.containsKey("email")
//                && extras.containsKey("phone") && extras.containsKey("password")) {
//            String fullName = extras.getString("fullName");
//            String email = extras.getString("email");
//            String phone = extras.getString("phone");
//            String password = extras.getString("password");
//            // Pass user data to ProfileFragment
//            ProfileFragment profileFragment = ProfileFragment.newInstance(fullName, email, phone, password);
//            replaceFragment(profileFragment);
////                replaceFragment(new  HomeFragment());
//        } else {
//            // If no user data, load HomeFragment by default
//            replaceFragment(new HomeFragment());
//        }
//
//        binding.bottomNavigation.setOnItemSelectedListener(item -> {
//            if (item.getItemId() == R.id.home) {
//                replaceFragment(new HomeFragment());
//            } else if (item.getItemId() == R.id.history) {
//                replaceFragment(new HistoryFragment());
//            } else if (item.getItemId() == R.id.profile) {
//                replaceFragment(new ProfileFragment());
//            }            return true;
//        });
//    }
//    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}