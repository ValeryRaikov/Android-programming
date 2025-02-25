package com.example.googlemaps;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemaps.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private float zoomvar = 10;

    private final LatLng SofiaCenter = new LatLng(42.6977082, 23.3218675);
    private final LatLng TUsofia = new LatLng(42.6570607, 23.3551086);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapBtn);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(SofiaCenter).title("Sofia Center"));
        mMap.addMarker(new MarkerOptions().position(TUsofia).title("TU Sofia"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SofiaCenter, zoomvar));

        Button plusZOOM = findViewById(R.id.zoomIN);
        Button minusZOOM = findViewById(R.id.zoomOUT);
        Button hybrid = findViewById(R.id.hybridBUT);
        Button normal = findViewById(R.id.normalBUT);
        Button satellite = findViewById(R.id.satelliteBUT);
        Button terrain = findViewById(R.id.terrainBUT);
        Button tus = findViewById(R.id.tusBUT);
        Button back = findViewById(R.id.BACK);

        plusZOOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomvar += 1;

                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });

        minusZOOM.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 zoomvar -= 1;

                 mMap.animateCamera(CameraUpdateFactory.zoomOut());
             }
        });

        hybrid.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        normal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        satellite.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE
                 );
             }
        });

        terrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });

        tus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.addMarker(new MarkerOptions().position(TUsofia).title("TU SOFIA"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(TUsofia));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(18));

                CircleOptions circleOptions = new CircleOptions();
                circleOptions.center(TUsofia);
                circleOptions.radius(25);
                circleOptions.fillColor(Color.BLUE);
                circleOptions.strokeColor(Color.RED);
                circleOptions.strokeWidth(4);

                mMap.addCircle(circleOptions);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}