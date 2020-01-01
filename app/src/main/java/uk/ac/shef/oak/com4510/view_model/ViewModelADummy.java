package uk.ac.shef.oak.com4510.view_model;

import android.app.Application;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import uk.ac.shef.oak.com4510.model.Objects.PathItem;
import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.model.models.VisitImages;



//Dummy data view model
public class ViewModelADummy extends AndroidViewModel {


    private MutableLiveData<List<PathItem>> pathItemList;

    public ViewModelADummy(@NonNull Application application) {
        super(application);

    }

    //Get Images By ID
    public MutableLiveData<List<VisitImages>> getImagesByVisitID(long visitId){
        MutableLiveData<List<VisitImages>> listVisitImages=new MutableLiveData<>();
        List<VisitImages> imagesList=new ArrayList<>();

        for (int i=0; i<20;i++){
            VisitImages visitImage=new VisitImages(visitId,new byte[1]);
            visitImage.setId((long)i);
            imagesList.add(visitImage);
        }

        listVisitImages.setValue(imagesList);
        return listVisitImages;
    }
    //Create PathList
    public MutableLiveData<List<PathItem>> getPathItemList() {
        if (pathItemList == null) {
            pathItemList = new MutableLiveData<>();
            pathItemList.setValue(getList());
        }
        return pathItemList;
    }
    //Get Visit By ID
    public MutableLiveData<Visit> getVisitByID(long id) {
        MutableLiveData<Visit> visit=new MutableLiveData<>();
        Visit dummy=new Visit("Dummy Title","Dummy Date",0.5,0.6,0.7,05);
        dummy.setId(id);
        visit.setValue(dummy);
        return visit;
    }
    //Get VisitImage by key
    public MutableLiveData<VisitImages>getVisitImageById(long key){
        MutableLiveData<VisitImages> visitimage=new MutableLiveData<>();


        visitimage.setValue(new VisitImages((long)key,new byte[1]));
        return visitimage;
    }
    //Get All Visit images sorted
    public MutableLiveData<List<VisitImages>>getAllVisitImagesSorted(){
        MutableLiveData<List<VisitImages>> listVisitImages=new MutableLiveData<>();
        List<VisitImages> imagesList=new ArrayList<>();


        for (int i=0;i<20;i++){
            VisitImages visitImage=new VisitImages((long)i,new byte[1]);
            visitImage.setId((long)1);
            imagesList.add(visitImage);
        }

        listVisitImages.setValue(imagesList);
        return listVisitImages;
    }


    private List<PathItem> getList(){
        List<VisitImages> imagesList=new ArrayList<>();


        for (int i=0;i<20;i++){
            VisitImages visitImage=new VisitImages((long)i,new byte[1]);
            visitImage.setId((long)i);
            imagesList.add(visitImage);
        }
        List<PathItem> list=new ArrayList<>();

        for (int i=0;i<20;i++){
            Visit dummy=new Visit("Dummy Title","Dummy Date",0.5,0.6,0.7,05);
            dummy.setId((long)i);
            list.add(new PathItem(dummy,imagesList));
        }

        return list;

    }
}
