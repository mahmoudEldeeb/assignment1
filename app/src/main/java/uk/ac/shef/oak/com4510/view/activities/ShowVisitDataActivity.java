package uk.ac.shef.oak.com4510.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.databinding.ActivityShowVisitDataBinding;
import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.utils.Constants;
import uk.ac.shef.oak.com4510.view.Adapters.VisitsAdapter;
import uk.ac.shef.oak.com4510.view_model.ShowVisitViewModel;

public class ShowVisitDataActivity extends AppCompatActivity implements VisitsAdapter.OnVisitClickListener {
ActivityShowVisitDataBinding showVisitDataBinding;
ShowVisitViewModel visitViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       showVisitDataBinding= DataBindingUtil.setContentView(this, R.layout.activity_show_visit_data);
       visitViewModel= ViewModelProviders.of(this).get(ShowVisitViewModel.class);

        Constants.context=this;

       showVisitDataBinding.resVisit.setLayoutManager(new LinearLayoutManager(this));

       visitViewModel.getAllVisitsDetails().observe(this, new Observer<List<Visit>>() {
           @Override
           public void onChanged(List<Visit> visits) {
               showVisitDataBinding.resVisit.setAdapter(new VisitsAdapter(visits));
           }
       });

       showVisitDataBinding.search.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {
                search(showVisitDataBinding.search.getText().toString());
           }
       });


    }

    /**
     * search function
     * @param s
     */
    private void search(String s) {
        visitViewModel.search(s).observe(this, new Observer<List<Visit>>() {
            @Override
            public void onChanged(List<Visit> visits) {
                showVisitDataBinding.resVisit.setAdapter(new VisitsAdapter(visits));
            }
        });
    }

    /**
     * on update button pressed call this activity  to update
     * @param visit
     */
    @Override
    public void onUpdateClick(Visit visit) {
        Intent intent=new Intent(ShowVisitDataActivity.this,UpdateVisitActivity.class);
        intent.putExtra("VisitObject",visit);
        startActivity(intent);
    }

    /**
     * on delete button pressed call this activity  to delete
     * @param visit
     */
    @Override
    public void onDeleteClick(Visit visit) {
            visitViewModel.delete(visit);
    }
}
