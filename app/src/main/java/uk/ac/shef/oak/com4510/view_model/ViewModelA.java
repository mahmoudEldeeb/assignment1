package uk.ac.shef.oak.com4510.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import uk.ac.shef.oak.com4510.model.Objects.PathItem;
import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.model.models.VisitImages;
import uk.ac.shef.oak.com4510.model.repositry.RetrieveDataRepository;

public class ViewModelA extends AndroidViewModel {

    private RetrieveDataRepository repositry;

    public ViewModelA(@NonNull Application application) {
        super(application);
        repositry = new RetrieveDataRepository();
    }


    //Get Images By ID
    public LiveData<List<VisitImages>> getImagesByVisitID(long visitId) {
        return repositry.getVisitImagesByVisitID(visitId);

    }

    //Create PathList
    public LiveData<List<PathItem>> getPathItemList() {
        LiveData<List<Visit>> allVisitsLiveData = repositry.getAllVisits();
        List<Visit> allVisits = allVisitsLiveData.getValue();
        List<PathItem> pathItemList = new ArrayList<>();
        if (allVisits != null) {
            for (Visit visit : allVisits) {
                PathItem pathItem = new PathItem(visit, getImagesByVisitID(visit.getId()).getValue());
                pathItemList.add(pathItem);
            }
        }
        MutableLiveData<List<PathItem>> liveData = new MutableLiveData<>();
        liveData.setValue(pathItemList);
        return liveData;
    }

    //Get Visit By ID
    public LiveData<Visit> getVisitByID(long id) {
        return repositry.getVisitByID(id);
    }

    //Get VisitImage by key
    public LiveData<VisitImages> getVisitImageById(long id) {
        return repositry.getVisitImageByID(id);
    }

    //Get All Visit images sorted
    public LiveData<List<VisitImages>> getAllVisitImagesSorted() {
        return repositry.getAllVisitImagesSorted();
    }



}
