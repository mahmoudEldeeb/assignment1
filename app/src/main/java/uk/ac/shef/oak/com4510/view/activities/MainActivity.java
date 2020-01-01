package uk.ac.shef.oak.com4510.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.databinding.ActivityMainBinding;
import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.utils.Constants;
import uk.ac.shef.oak.com4510.view_model.ViewModelA;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ViewModelA viewModelA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        viewModelA= ViewModelProviders.of(this).get(ViewModelA.class);
        Constants.context=this;

        binding.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // title
                if (!binding.insertTitleEditText.getText().toString().isEmpty()) {
                    Intent intent = new Intent(
                            MainActivity.this,
                            Main2Activity.class);
                    intent.putExtra("title", binding.insertTitleEditText.getText().toString());
                    intent.putExtra("description", binding.description.getText().toString());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getBaseContext(),"enter title of visit",Toast.LENGTH_LONG).show();

                }
            }
        });

        Constants.context=this;
        binding.showPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,selectActivity.class));
            }
        });
        binding.showVisits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,ShowVisitDataActivity.class));
            }
        });
    }
}