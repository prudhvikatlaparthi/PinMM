package com.pru.pinmm.ui.maps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.pru.pinmm.R;
import com.pru.pinmm.databinding.ActivityMapsBinding;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnPolylineClickListener, GoogleMap.OnPolygonClickListener { @NonNull
    private ActivityMapsBinding binding;
    @Nullable
    private GoogleMap googleMap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (fragment != null)
            fragment.getMapAsync(this);
        // API CALL for for latlng

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        prepareMap();
    }

    private void prepareMap() {
        // Add polylines to the map.
        // Polylines are useful to show a route or some other connection between points.
        LatLng latLng1 = new LatLng(-35.016, 143.321);
        LatLng latLng2 = new LatLng(-34.747, 145.592);
        LatLng latLng3 = new LatLng(-34.364, 147.891);
        LatLng latLng4 = new LatLng(-33.501, 150.217);
        LatLng latLng5 = new LatLng(-32.306, 149.248);
        LatLng latLng6 = new LatLng(-32.491, 147.309);
        googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        latLng1,
                        latLng2,
                        latLng3,
                        latLng4,
                        latLng5,
                        latLng6));

        // Position the map's camera near Alice Springs in the center of Australia,
        // and set the zoom factor so most of Australia shows on the screen.
        setMarker(googleMap, latLng1);
        setMarker(googleMap, latLng2);
        setMarker(googleMap, latLng3);
        setMarker(googleMap, latLng4);
        setMarker(googleMap, latLng5);
        setMarker(googleMap, latLng6);
    }

    private void setMarker(@NonNull GoogleMap googleMap, LatLng latLng) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-23.684, 133.903), 4));
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
        googleMap.addMarker(markerOptions);
    }

    @Override
    public void onPolylineClick(@NonNull Polyline polyline) {

    }

    @Override
    public void onPolygonClick(@NonNull Polygon polygon) {

    }
}