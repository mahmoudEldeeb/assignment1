package uk.ac.shef.oak.com4510.view.activities;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import uk.ac.shef.oak.com4510.utils.Constants;
import uk.ac.shef.oak.com4510.view.Adapters.AllPathAdapter;
import uk.ac.shef.oak.com4510.view.Adapters.ImageAdapter;
import uk.ac.shef.oak.com4510.model.Objects.PathItem;
import uk.ac.shef.oak.com4510.R;
import uk.ac.shef.oak.com4510.view_model.ViewModelA;
import uk.ac.shef.oak.com4510.model.models.VisitImages;
import uk.ac.shef.oak.com4510.view_model.ViewModelADummy;

public class AllPathImagesActivity extends AppCompatActivity implements ImageAdapter.ImageViewClickListener{
    private ViewModelADummy viewModelA;
    private RecyclerView recyclerView;
    private AllPathAdapter pathAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.path_layout);
        buildRecyclerView();
        Constants.context=this;
        viewModelA = ViewModelProviders.of(this).get(ViewModelADummy.class);
        //get data from view model
        viewModelA.getPathItemList().observe(this, new Observer<List<PathItem>>() {
            @Override
            public void onChanged(List<PathItem> pathItems) {
                //Notify the adapter about the data
                pathAdapter.refreshData(pathItems);

            }
        });
    }

    public void buildRecyclerView(){
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        pathAdapter =new AllPathAdapter(new ArrayList<PathItem>(),this,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pathAdapter);

    }
    //Start ImageDescriptionActivity on Image Click while passing the necessary data
    @Override
    public void onImageViewClick(int position, VisitImages image) {
        long visitID=image.getVisitId();
        long imageKey=image.getId();
        Intent intent=new Intent(this, ImageDescriptionActivity.class);
        intent.putExtra("visitID",visitID);
        intent.putExtra("imageKey",imageKey);
        startActivity(intent);
    }
}
