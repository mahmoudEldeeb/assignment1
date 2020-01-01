package uk.ac.shef.oak.com4510.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.model.models.VisitImages;
import uk.ac.shef.oak.com4510.model.repositry.InsertVisitRepositry;


/**
 *
 *The beginning of Areej part
 *InsertVisitsViewModel this class is viewmodel uses to call repositry to insert data into my database
 * @author  Areej
 */
public class InsertVisitsViewModel extends ViewModel {


    /**
     *     here call insertVisitRepositry.insertVisitImage to insert visit and return id
     * @param  visit
     * it take cisit object
     * @return  MutableLiveData<Long>
     *     return id of visit
      */
    public MutableLiveData<Long>insertVisit(Visit visit)
    {
        InsertVisitRepositry insertVisitRepositry=new InsertVisitRepositry();
        return insertVisitRepositry.insertVisit(visit);
    }


    /**
     *     here call insertVisitRepositry.insertVisitImage to insert image
     *
     *  it take list of images object
     * it take cisit object
     *      return id of visit
     * @param  images
     * @return  MutableLiveData<Long>
     *
     */

    public MutableLiveData<Long[]> insertVisitImage(List<VisitImages> images )
    {
        InsertVisitRepositry insertVisitRepositry=new InsertVisitRepositry();
        return insertVisitRepositry.insertVisitImage(images);
    }
}
//The end of Areej part

