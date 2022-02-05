package com.pru.pinmm.ui.maps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.pru.pinmm.MyApplication;
import com.pru.pinmm.R;
import com.pru.pinmm.databinding.ActivityMapsBinding;
import com.pru.pinmm.model.custom.MapMarkerModel;
import com.pru.pinmm.model.payloads.MapAPIPayload;
import com.pru.pinmm.model.response.Job;
import com.pru.pinmm.model.response.JsonMapResponse;
import com.pru.pinmm.model.response.MapResponse;
import com.pru.pinmm.remote.APIHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnPolylineClickListener, GoogleMap.OnPolygonClickListener {
    private ActivityMapsBinding binding;
    private GoogleMap googleMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (fragment != null)
            fragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        // API CALL for for latlng
        /*List<MapMarkerModel> latLngs = new ArrayList<>();
        latLngs.add(new MapMarkerModel(new LatLng(-35.016, 143.321),"stop"));
        latLngs.add(new MapMarkerModel(new LatLng(-34.747, 145.592),"stop"));
        latLngs.add(new MapMarkerModel(new LatLng(-34.364, 147.891),"stop"));
        latLngs.add(new MapMarkerModel(new LatLng(-33.501, 150.217),"stop"));
        latLngs.add(new MapMarkerModel(new LatLng(-32.306, 149.248),"stop"));
        latLngs.add(new MapMarkerModel(new LatLng(-32.491, 147.309),"stop"));
        prepareMap(latLngs);*/
        MapAPIPayload payload = new MapAPIPayload();
        payload.setSessionToken(MyApplication.getMyPreferences().getKeySessionToken());
        payload.setLoginName(MyApplication.getMyPreferences().getKeyLoggedUserId());
        Call<MapResponse> request = APIHelper.getRepository().getMapDetails(payload);
        request.enqueue(new Callback<MapResponse>() {
            @Override
            public void onResponse(Call<MapResponse> call, Response<MapResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MapResponse response1 = response.body();
                    if (response1.getJsonList() != null && response1.getJsonList().size() > 0) {
                        JsonMapResponse jsonMapResponse = response1.getJsonList().get(0);
                        if (jsonMapResponse.getJobList() != null && jsonMapResponse.getJobList().size() > 0) {
                            List<Job> jobs = jsonMapResponse.getJobList();
                            List<MapMarkerModel> markerModelList = new ArrayList<>();
                            for (int i = 0; i < jobs.size(); i++) {
                                LatLng latLng = new LatLng(jobs.get(i).getLatitude(), jobs.get(i).getLongitude());
                                MapMarkerModel mapMarkerModel = new MapMarkerModel(latLng, jobs.get(i).getStopName());
                                markerModelList.add(mapMarkerModel);
                            }
                            if (markerModelList.size() > 0) {
                                // bottom view RecyclerView List
                                prepareMap(markerModelList);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MapResponse> call, Throwable t) {

            }
        });
    }

    private void prepareMap(List<MapMarkerModel> markerModelList) {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.color(R.color.black);
        polylineOptions.width(4);
        for (int i = 0; i < markerModelList.size(); i++) {
            polylineOptions.add(markerModelList.get(i).getLatLng());
            setMarker(markerModelList.get(i), i == 0);
        }
        googleMap.addPolyline(polylineOptions);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }

    private void setMarker(MapMarkerModel mapMarkerModel, boolean positionCamera) {
        if (positionCamera) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapMarkerModel.getLatLng(), 4));
        }
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(mapMarkerModel.getLatLng());
        markerOptions.title(mapMarkerModel.getStopName());
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        googleMap.addMarker(markerOptions);
    }

    @Override
    public void onPolylineClick(@NonNull Polyline polyline) {

    }

    @Override
    public void onPolygonClick(@NonNull Polygon polygon) {

    }
}