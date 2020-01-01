package uk.ac.shef.oak.com4510.model.repositry;

import androidx.lifecycle.LiveData;

import java.util.List;

import uk.ac.shef.oak.com4510.model.datebase.MyDatabase;
import uk.ac.shef.oak.com4510.model.datebase.VisitDao;
import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.model.models.VisitImages;


//The beginning of Areej part

/**
 *  this class used to retrieve data from database  here i get instance from visitdao to connect with room database
 * @author Areej
 */
public class RetrieveDataRepository {

    /**
     * here i get instance from visitdao to connect with room database
     * @return VisitDao
     */
    public static VisitDao getDao(){
        MyDatabase db = MyDatabase.getDatabase();
        return db.dao();
    }

    /**
     * it take visit id to retrieve visit details
     * @param id
     * @return LiveData<Visit>
     */
    public LiveData<Visit> getVisitByID(Long id) {
        return getDao().getVisitByID(id);
    }


    /**
     * it return all visits
     * @return LiveData<List<Visit>>
     */
    public LiveData<List<Visit>> getAllVisits() {

        return getDao().getAllVisits();
    }



    /**
     *  this function return all images of visit
     * @param visitId
     * @return LiveData<List<VisitImages>>
     */
    public LiveData<List<VisitImages>> getVisitImagesByVisitID(Long visitId) {
        return getDao().getVisitImagesByVisitID(visitId);
    }

    /**
     *    this function return one  image of visit
     * @param id
     * @return  LiveData<VisitImages>
     */
    public LiveData<VisitImages> getVisitImageByID(Long id) {
        return getDao().getVisitImageByID(id);
    }

    /**
     * this function return all images of visit
     * @param id
     * @return LiveData<List<VisitImages>>
     */
    public LiveData<List<VisitImages>> getVisitImagesByID(Long id) {
        return getDao().getVisitImagesByID(id);
    }

    /**
     *
     this function return all images of all visit
     * @return LiveData<List<VisitImages>>
     */
    public LiveData<List<VisitImages>> getAllVisitImagesSorted() {
        return getDao().getAllVisitImagesSorted();
    }

    /**
     *
     * @param search
     * @return
     */

    public LiveData<List<Visit>> search(String search) {
        return getDao().search("%"+search+"%");
    }
}
//The end of Areej part