package uk.ac.shef.oak.com4510.model.repositry;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import uk.ac.shef.oak.com4510.model.datebase.MyDatabase;
import uk.ac.shef.oak.com4510.model.datebase.VisitDao;
import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.model.models.VisitImages;



//The beginning of Areej part

/**
 *  *InsertVisitRepositry this class is repositry to connect with database get object of visitdao
 *  * @author  Areej
 */
public class InsertVisitRepositry {

/// i use this to return id
    public MutableLiveData<Long>visitInsert=new MutableLiveData<>();

    /// i use this to return ids of images
    public MutableLiveData<Long[]>visitImagesInsert=new MutableLiveData<>();



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
     * this method to insert object of visit into database
     * @param visit
     * @return MutableLiveData<Long>
     */
    public MutableLiveData<Long> insertVisit(final Visit visit) {
/// here i crete object of asynctask to insert data in background thread
        InsertVistAsyncTask insertVistAsyncTask=new InsertVistAsyncTask();
        insertVistAsyncTask.execute(visit);
        return visitInsert;

    }

    /**
     * this method uses to insert list of images for particular visit
     * @param images
     * @return
     */
    public MutableLiveData<Long[]> insertVisitImage(final List<VisitImages> images) {

       new InsertVistImagesAsyncTask().execute(images);
        return visitImagesInsert;
    }







    /**
     *  this aasyncktask class to insert visit images into database using background thread
     * @author Areej
     */
    private  class InsertVistImagesAsyncTask extends AsyncTask<List<VisitImages>, Void, Long[]> {

        @Override
        protected Long[] doInBackground(List<VisitImages>... visitImages) {
            try {
                return getDao().insertVisitImage(visitImages[0]);
            }
            catch (Exception e){
                return null;
            }

        }
        @Override
        protected void onPostExecute(Long []lon) {
///here return ids of images
            visitImagesInsert.setValue(lon);
        }

    }
    /**
     *  this aasyncktask class to insert visit  into database using background thread
     * @author Areej
     */
    private  class InsertVistAsyncTask extends AsyncTask<Visit, Void, Long> {

        @Override
        protected Long doInBackground(Visit... models) {
            try {
                return getDao().insertVist(models[0]);
            }
            catch (Exception e){

                Log.v("rrrr",e.getMessage());
                return Long.valueOf(0);
            }

        }
        @Override
        protected void onPostExecute(Long lon) {
            visitInsert.setValue(lon);
        }

    }

}
//The end of Areej part