package com.avl.ahenuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.avl.ahenuser.databinding.ActivityMapsBinding;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //here we use SupportMapFragment hence, we have to user getSupportFragmentManager

        mapFragment.getMapAsync(this);

        }
        @Override
        public void onMapReady (@NonNull GoogleMap googleMap){
            mMap = googleMap;
            LatLng punlatLng = new LatLng(18.5204, 73.8567);
            MarkerOptions markerOptions = new MarkerOptions().position(punlatLng).title("Pune");
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(punlatLng));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(punlatLng, 16f));
    }

}