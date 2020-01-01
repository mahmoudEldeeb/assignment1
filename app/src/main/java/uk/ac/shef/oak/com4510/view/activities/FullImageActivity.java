package uk.ac.shef.oak.com4510.view.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.utils.Constants;
import uk.ac.shef.oak.com4510.view_model.ViewModelA;
import uk.ac.shef.oak.com4510.model.models.VisitImages;
import uk.ac.shef.oak.com4510.view_model.ViewModelADummy;

/**
 * Displays the full size Image
 */
public class FullImageActivity extends AppCompatActivity {
    ViewModelADummy viewModelA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        Constants.context=this;
        viewModelA= ViewModelProviders.of(this).get(ViewModelADummy.class);
        final ImageView imageView=findViewById(R.id.fullImage);
        Intent intent =getIntent();
        Long imageKey=intent.getExtras().getLong("imageKey",0);
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
