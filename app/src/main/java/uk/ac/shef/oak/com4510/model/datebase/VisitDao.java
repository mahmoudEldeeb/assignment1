package uk.ac.shef.oak.com4510.model.datebase;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.model.models.VisitImages;


/**
 * this is interface to connect to room database
 *   @author Areej
 */
@Dao
public interface VisitDao {
    /**
     * it insert visit
     * @param vist
     * @return Long
     */
    //The beginning of Areej part
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insertVist(Visit vist);

    /**
     * update visit
     * @param vist
     */
    @Update
    void updateVist(Visit vist);

    /**
     * delet visit
     * @param vist
     */
    @Delete
    void deleteVist(Visit vist);

    /**
     * it insert images
     * @param image
     * @return Long []
     */
    @Insert
    Long[] insertVisitImage(List<VisitImages> image);



    @Query("select * from visit where title like :search")
    LiveData<List<Visit>> search(String search);

//The end of Areej part


    @Query("select * from visit")
    LiveData<List<Visit>> getAllVisits();

 @Query("select * from visit where id=:iD")
    LiveData<Visit> getVisitByID(long iD);

    @Query("select * from visit_images where visitId=:visitID")
    LiveData<List<VisitImages>> getVisitImagesByVisitID(long visitID);


    @Query("select * from visit_images where Id=:ID")
    LiveData<VisitImages> getVisitImageByID(long ID);

    @Query("select * from visit_images where Id=:ID")
    LiveData<List<VisitImages>> getVisitImagesByID(long ID);


    @Query("select * from visit_images ORDER BY id ASC")
    LiveData<List<VisitImages>> getAllVisitImagesSorted();






}