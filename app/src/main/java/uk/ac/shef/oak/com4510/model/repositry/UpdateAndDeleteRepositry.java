package uk.ac.shef.oak.com4510.model.repositry;

import android.os.AsyncTask;

import uk.ac.shef.oak.com4510.model.datebase.MyDatabase;
import uk.ac.shef.oak.com4510.model.datebase.VisitDao;
import uk.ac.shef.oak.com4510.model.models.Visit;


//The beginning of Areej part

/**
 *  *UpdateAndDeleteRepositry this class is repositry to connect with database get object of visitdao
 *  * @author  Areej
 */
public class UpdateAndDeleteRepositry {


    /**
     *
     * this function return object of dao
     * @return VisitDao
     */
    public static VisitDao getDao(){
        MyDatabase db = MyDatabase.getDatabase();
        return db.dao();
    }

    /**
     * this method to delete object of visit into database
     * @param visit
     * @return MutableLiveData<Long>
     */
    public void delete(final Visit visit) {

        new DeleteVistAsyncTask().execute(visit);


    }
    /**
     * this method to update object of visit into database
     * @param visit
     * @return MutableLiveData<Long>
     */
    public void update(final Visit visit) {

        new UpdateVistAsyncTask().execute(visit);


    }








    /**
     *  this aasyncktask class to delete visit  into database using background thread
     * @author Areej
     */
    private  class DeleteVistAsyncTask extends AsyncTask<Visit, Void, Void> {


        @Override
        protected Void doInBackground(Visit... visits) {
            getDao().deleteVist(visits[0]);
            return null;
        }
    }
    /**
     *  this aasyncktask class to update visit  into database using background thread
     * @author Areej
     * ///
     */

    private  class UpdateVistAsyncTask extends AsyncTask<Visit, Void, Void> {


        @Override
        protected Void doInBackground(Visit... visits) {
            getDao().updateVist(visits[0]);
            return null;
        }
    }



}
//The end of Areej part