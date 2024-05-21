package com.example.syndicapp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity implements OnMapReadyCallback, LocationListener {
    MapView mapView;
    GoogleMap Map;
    private LocationManager locationManager;
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        mapView=findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Map=googleMap;
        LatLng coordonne=new LatLng(33.59135225413525, -7.6048121046475226);
        Map.addMarker(new MarkerOptions().position(coordonne).title("Emsi Centre"));
        Map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordonne,18));
        if (ActivityCompat.checkSelfPermission(this,
                 android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            // Request runtime permissions if not granted
            ActivityCompat.requestPermissions(this, new
                            String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Map.clear();
// Add a marker for the current location
        LatLng currentLocation = new LatLng(location.getLatitude(),
                location.getLongitude());
        Map.addMarker(new MarkerOptions().position(currentLocation).title("Current Location"));
// Move camera to the current location
                Map.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        Map.animateCamera(CameraUpdateFactory.zoomTo(15));

    }
}
