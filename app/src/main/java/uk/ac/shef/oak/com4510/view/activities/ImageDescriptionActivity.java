package uk.ac.shef.oak.com4510.view.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.utils.Constants;
import uk.ac.shef.oak.com4510.view_model.ViewModelA;
import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.model.models.VisitImages;
import uk.ac.shef.oak.com4510.view_model.ViewModelADummy;


public class ImageDescriptionActivity extends AppCompatActivity {
    private ViewModelADummy viewModelA;
    private long visitID=0;
    private long imageKey=0;
    private ImageView imageView;
    private TextView textView;
//    private GoogleMap googleMap

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_description);
        imageView=findViewById(R.id.image2);
        Constants.context=this;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ImageDescriptionActivity.this,FullImageActivity.class);
                intent.putExtra("imageKey",imageKey);
                startActivity(intent);
            }
        });
        textView=findViewById(R.id.description);

        visitID=getIntent().getExtras().getLong("visitID",0);
        imageKey=getIntent().getExtras().getLong("imageKey",0);
        //Get data from view model
        viewModelA = ViewModelProviders.of(this).get(ViewModelADummy.class);
        viewModelA.getVisitByID(visitID).observe(this, new Observer<Visit>() {
            @Override
            public void onChanged(Visit visit) {
                textView.setText("visit.getDate()    visit.getTitle()   visit.getPressure() ....");
                //To create the map the following are needed
                //visit.getLatitude() visit.getLongitude()
                //GoogleMap googleMap = ((MapView) rootView.findViewById(R.id.map2)).getMap();
                //googleMap.addMarker(new MarkerOptions().position(new LatLng( visit.getLatitude(), -visit.getLongitude())).title("Mark"));


            }

        });
        viewModelA.getVisitImageById(imageKey).observe(this, new Observer<VisitImages>() {
            @Override
            public void onChanged(VisitImages image) {
                Bitmap bitmap= BitmapFactory.decodeByteArray(image.getImage(),0,image.getImage().length);
                //Database Image
//                imageView.setImageBitmap(bitmap);
                //Dummy Image
                imageView.setImageResource(R.drawable.b1);

            }
        });

    }
}

