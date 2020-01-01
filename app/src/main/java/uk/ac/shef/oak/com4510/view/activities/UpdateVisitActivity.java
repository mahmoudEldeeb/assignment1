package uk.ac.shef.oak.com4510.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.databinding.ActivityUpdateVisitBinding;
import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.view_model.UpdateVisitViewModel;


/**
 *
 */
public class UpdateVisitActivity extends AppCompatActivity {
    ActivityUpdateVisitBinding updateVisitBinding;
    UpdateVisitViewModel visitViewModel;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateVisitBinding= DataBindingUtil.setContentView(this, R.layout.activity_update_visit);
/*
here i get object of visit to sow it in fields
 */
        Visit visit= (Visit) getIntent().getSerializableExtra("VisitObject");
        visitViewModel= ViewModelProviders.of(this).get(UpdateVisitViewModel.class);

        updateVisitBinding.title.setText(visit.getTitle());
        updateVisitBinding.date.setText(visit.getDate());
        updateVisitBinding.description.setText(visit.getDesscription());
        updateVisitBinding.temp.setText(visit.getTemp()+"");
        updateVisitBinding.barometer.setText(visit.getPressure()+"");


        updateVisitBinding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visit.setTitle(updateVisitBinding.title.getText().toString());

                visit.setDate(updateVisitBinding.date.getText().toString());
                visit.setDesscription(updateVisitBinding.description.getText().toString());
                visit.setTemp(Double.parseDouble(updateVisitBinding.temp.getText().toString()));
                visit.setPressure(Double.parseDouble(updateVisitBinding.barometer.getText().toString()));
                visitViewModel.update(visit);
                Toast.makeText(getBaseContext(),"done",Toast.LENGTH_LONG).show();

            }
        });

    }
}
