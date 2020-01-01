package uk.ac.shef.oak.com4510.model.datebase;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.model.models.VisitImages;
import uk.ac.shef.oak.com4510.utils.Constants;




//The beginning of Areej part

/**
 * this class uses to create and build database
 * @author Areej
 */
@Database(entities = {Visit.class, VisitImages.class},version = 1)

public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase instance;

    public abstract VisitDao dao();


    /**
     *     here i build my room database and get reference
     * @return MyDatabase
     */
    public static MyDatabase getDatabase() {
        if (instance == null) {
            synchronized (MyDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(Constants.context.getApplicationContext(),
                            MyDatabase.class, Constants.DATABASE_NAME)
                            .build();
                }
            }
        }
        return instance;
    }

}
//The end of Areej part