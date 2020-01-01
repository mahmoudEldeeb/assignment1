package uk.ac.shef.oak.com4510.view.activities;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Camera;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uk.ac.shef.oak.com4510.utils.Constants;
import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.databinding.ActivityMain2Binding;
import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.model.models.VisitImages;
import uk.ac.shef.oak.com4510.model.sensors.Accelerometer;
import uk.ac.shef.oak.com4510.model.sensors.Barometer;
import uk.ac.shef.oak.com4510.model.sensors.LocationService;
import uk.ac.shef.oak.com4510.model.sensors.Temperature;
import uk.ac.shef.oak.com4510.view_model.InsertVisitsViewModel;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class Main2Activity extends AppCompatActivity implements OnMapReadyCallback {
    ActivityMain2Binding binding;
    private final int GALLERY = 101;
    final int RequestCameraPermissionID = 1001;

    private static AppCompatActivity activity;
    private static GoogleMap mMap;
    private static final int ACCESS_FINE_LOCATION = 123;
    private LocationRequest mLocationRequest;
    private FusedLocationProviderClient mFusedLocationClient;
    private MapView mapView;
    private Button mButtonEnd;
    private PendingIntent mLocationPendingIntent;

    // A list of locations of each picture taken
    List<LatLng> image_loc = new ArrayList();


    public static AppCompatActivity getActivity() {
        return activity;
    }

    public static void setActivity(AppCompatActivity activity) {
        Main2Activity.activity = activity;
    }

    public static GoogleMap getMap() {
        return mMap;
    }

    private Barometer barometer;
    private Temperature temperature;
    private Accelerometer accelerometer;

    InsertVisitsViewModel insertVisitsViewModel;
    Long visitId= Long.valueOf(0);
    String time,title,description;
    List<VisitImages>visitImages;
    private FusedLocationProviderClient fusedLocationClient;
    Location myLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        barometer = new Barometer(this);
        temperature = new Temperature(this);
        accelerometer = new Accelerometer(this, barometer, temperature);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main2);
        insertVisitsViewModel= ViewModelProviders.of(this).get(InsertVisitsViewModel.class);
        Constants.context=this;
        visitImages=new ArrayList<>();
        visitId= Long.valueOf(0);
        title=getIntent().getStringExtra("title");
        description=getIntent().getStringExtra("description");

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                 time = df.format(new Date());

                barometer.startSensingPressure(accelerometer);
                temperature.startSensingTemperature(accelerometer);
                if(hasCamera()) {

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                            android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Main2Activity.this,
                                new String[]{android.Manifest.permission.CAMERA},RequestCameraPermissionID);

                    }
                    selectOption();
                }
                else{

                    useGallery();
                }


            }
        });

        /* Alvin */

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // The activity is to be stopped explicitly by the user.
        //The beginning of Areej part
        binding.buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //stopping position

                if (visitId != -1) {
                    insertVisitsViewModel.insertVisitImage(visitImages).observe(Main2Activity.this, new Observer<Long[]>() {
                        @Override
                        public void onChanged(Long[] longs) {
                            if(longs[0]>0){
                                Toast.makeText(Main2Activity.this, "Visit Saved!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Main2Activity.this, "failed try later!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    stopLocationUpdates();
                }
                else Toast.makeText(Main2Activity.this, "choose image first", Toast.LENGTH_SHORT).show();

            }

        });


        getLocation();
        initLocations();

    }
    public void getLocation(){
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object

                            myLocation=location;
                        }
                    }
                });
    }

    //The beginning of Areej part

    /**
     * check if hase camera or not
     * @return
     */
    private boolean hasCamera(){
        int numCameras = Camera.getNumberOfCameras();
        if(numCameras>0)
            return true;
        else
            return false;
    }
    /**
     * ask permission request results
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this,
                            android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        //beginScan(type);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            break;

            case ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                            mLocationCallback, null /* Looper */);
                } else {
                        }
                return;
            }
        }
    }
    //The beginning of Areej part

    /**
     * show option to choose gallery or camera
     */
    private void selectOption(){
        final CharSequence[] items={"Camera","Gallery"};
        AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
        builder.setTitle("Options");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(items[which]=="Camera")
                    useCamera();
                else if(items[which]=="Gallery")
                    useGallery();

            }
        });
        builder.show();
    }

    /**
     * here open gallery to choose image from it galary
     */
    private void useGallery() {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(
                intent,
                GALLERY);
    }
    /**
     * here call camera
     */
    private void useCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    /**
     *  this function called when pickup an image from gallard or camera
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == this.RESULT_CANCELED) {
            return;
        }

        if(visitId==0) {
            // here ou must put longitude and latitude
            if(myLocation!=null) {
                Visit visit = new Visit(title + "",description, time, myLocation.getLongitude(),
                        myLocation.getLatitude(), temperature.getLastReportTime()
                        , barometer.getLastReportTime());

                insertVisitsViewModel.insertVisit(visit).observe(Main2Activity.this, new Observer<Long>() {
                    @Override
                    public void onChanged(Long aLong)
                    {

                        visitId = aLong;
                    }
                });
            }
            else Toast.makeText(getBaseContext(),"can not picup location try again",Toast.LENGTH_LONG).show();

        }

        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                     saveImage(bitmap);
                    Toast.makeText(Main2Activity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    //  imageview.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(Main2Activity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {

            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            // imageview.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(Main2Activity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * here i create object from VisitImage and add to list
     * @param myBitmap
     */
    public void saveImage(Bitmap myBitmap) {

        // Saves the location to image_loc as the pic is taken
//        image_loc.add(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()));
//        Log.w("Location", "coordinates: " + image_loc.toString());
//        mMap.addMarker(new MarkerOptions().position(new LatLng(mCurrentLocation.getLatitude(),
//                mCurrentLocation.getLongitude()))
//                .title(mLastUpdateTime));
        // End

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        myBitmap.recycle();
        if(myLocation!=null) {
            VisitImages visitImage = new VisitImages(visitId, byteArray, myLocation.getLongitude(), myLocation.getLatitude());
            visitImages.add(visitImage);
        }
        else Toast.makeText(getBaseContext(),"Can't pick up location try later",Toast.LENGTH_LONG).show();


    }

//The end of Areej part

    /* Alvin */

    private void initLocations() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        ACCESS_FINE_LOCATION);

            }

            return;
        }
    }

    private void startLocationUpdates(Context context) {
        Intent intent = new Intent(context, LocationService.class);
        mLocationPendingIntent = PendingIntent.getService(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Task<Void> locationTask = mFusedLocationClient.requestLocationUpdates(mLocationRequest,mLocationPendingIntent);

            if (locationTask != null) {
                locationTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            Log.w("Main2Activity", ((ApiException) e).getStatusMessage());
                        } else {
                            Log.w("Main2Activity", e.getMessage());
                        }
                    }
                });

                locationTask.addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        Log.d("Main2Activity", "restarting gps successful!");
                    }
                });


            }
        }
    }

    /**
     * it stops the location updates
     */
    private void stopLocationUpdates(){
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        accelerometer.startAccelerometerRecording();

    }

    @Override
    protected void onPause() {
        super.onPause();
        accelerometer.stopAccelerometer();
    }


    private Location mCurrentLocation;
    private String mLastUpdateTime;

    /* For plotting path */
    LatLng previous_location = null;
    /*  END */
    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            mCurrentLocation = locationResult.getLastLocation();
            mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
            Log.i("MAP", "new location " + mCurrentLocation.toString());
            if (mMap != null) {

                /*
                /*  Display the current location and connect them with a path
                 */

                LatLng next_location = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
                if (previous_location != null)
                    mMap.addPolyline(new PolylineOptions().add(previous_location, next_location).width(5).color(Color.RED));
                previous_location = next_location;
                mMap.addMarker(new MarkerOptions().position(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()))
                        .title(mLastUpdateTime));
                /* END */
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()), 14.0f));
        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14.0f));
        startLocationUpdates(this);

    }

}