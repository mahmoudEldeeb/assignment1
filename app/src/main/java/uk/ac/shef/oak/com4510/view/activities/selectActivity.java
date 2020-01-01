package uk.ac.shef.oak.com4510.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.utils.Constants;
/**
 * Displays Three Buttons that Start a particular activity for Displaying the Pictures or the Paths
 */
public class selectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Button button=findViewById(R.id.AllImagesButton);
        Constants.context=this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(selectActivity.this,AllImagesActivity.class));
            }
        });
        Button button1=findViewById(R.id.AllImagesByPathButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(selectActivity.this,AllPathImagesActivity.class));
            }
        });
        Button button2=findViewById(R.id.AllPathsButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(selectActivity.this, PathActivity.class));
            }
        });
    }
}
