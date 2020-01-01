package uk.ac.shef.oak.com4510.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.model.repositry.RetrieveDataRepository;
import uk.ac.shef.oak.com4510.model.repositry.UpdateAndDeleteRepositry;

/**
 * @author areej
 */
public class ShowVisitViewModel extends ViewModel {

    /**
     * this function fro get visits details
     * @return
     */
    public LiveData<List<Visit>>getAllVisitsDetails(){
    return new RetrieveDataRepository().getAllVisits();
}

    /**
     * search function
     * @param search
     * @return LiveData<List<Visit>>
     */
    public LiveData<List<Visit>>search(String search){
        return new RetrieveDataRepository().search(search);
    }

    /**
     * for delete visit
     * @param visit
     */
    public void delete(Visit visit){
    new UpdateAndDeleteRepositry().delete(visit);
}
}
