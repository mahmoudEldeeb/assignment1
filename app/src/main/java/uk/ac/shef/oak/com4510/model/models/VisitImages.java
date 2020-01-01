package uk.ac.shef.oak.com4510.model.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


//The beginning of Areej part

/**
 * this is entity class uses to create a table in room database
 * for images of visits
 *  * @author Areej
 */
@Entity(tableName = "visit_images")

public class VisitImages {

    @ColumnInfo(name = "visitId")
    public  Long visitId;



    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;


    private double longitude;
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
@Ignore
    public VisitImages(Long visitId, byte[] image) {
        this.visitId = visitId;
        this.image = image;
    }

    public VisitImages(Long visitId, byte[] image, double longitude, double latitude) {
        this.visitId = visitId;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public Long getVisitId() {
        return visitId;
    }
}

//The end of Areej part