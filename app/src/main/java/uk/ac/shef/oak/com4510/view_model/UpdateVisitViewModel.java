package uk.ac.shef.oak.com4510.view_model;

import androidx.lifecycle.ViewModel;

import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.model.repositry.UpdateAndDeleteRepositry;

/**
 * this view for update page
 * @author areej
 */
public class UpdateVisitViewModel extends ViewModel {


    /**
     * it for update
     * @param visit
     */
    public void update(Visit visit){
    new UpdateAndDeleteRepositry().update(visit);
}
}
