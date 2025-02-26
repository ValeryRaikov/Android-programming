package com.example.googlemaps;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.googlemaps.databinding.ActivityMaps2Binding;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

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
        Button tusofiaB = findViewById(R.id.tusofia);
        Button unssB = findViewById(R.id.unss);
        Button ltyB = findViewById(R.id.lty);
        Button nsaB = findViewById(R.id.nsa);
        Button line = findViewById(R.id.line);
        Button polygon = findViewById(R.id.polygon);
        Button plusZoom = findViewById(R.id.zoomIN);
        Button minusZoom = findViewById(R.id.zoomOUT);

        final LatLng TUsofia = new LatLng(42.6570607, 23.3551086);
        final LatLng unss = new LatLng(42.651266, 23.3466593);
        final LatLng lty = new LatLng(42.6537179, 23.3564474);
        final LatLng nsa = new LatLng(42.6484442, 23.3466905);

        tusofiaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.addMarker(new MarkerOptions().position(TUsofia).title("ТУ София"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(TUsofia, 15));
            }
        });

        unssB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.addMarker(new MarkerOptions().position(unss).title("УНСС"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(unss, 15));
            }
        });

        ltyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.addMarker(new MarkerOptions().position(lty).title("ЛТУ"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lty, 15));
            }
        });

        nsaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMinZoomPreference(14);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(nsa)
                        .title("НСА")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                Marker m = mMap.addMarker(markerOptions);
                assert m != null;
                m.showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(nsa));
            }
        });

        line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMinZoomPreference(14);

                PolylineOptions plo = new PolylineOptions();
                plo.add(TUsofia);
                plo.add(unss);
                plo.color(Color.YELLOW);
                plo.geodesic(true);
                plo.startCap(new RoundCap());
                plo.width(20);
                plo.jointType(JointType.BEVEL);

                mMap.addPolyline(plo);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(TUsofia));
            }
        });

        polygon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.setMinZoomPreference(14);

                PolygonOptions polygonOptions = new PolygonOptions();
                polygonOptions.add(TUsofia, unss, lty, nsa);
                polygonOptions.strokeJointType(JointType.ROUND);
                polygonOptions.strokeColor(Color.BLUE);
                polygonOptions.strokeWidth(10);

                mMap.addPolygon(polygonOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(unss));
            }
        });

        plusZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });

        minusZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });
    }
}