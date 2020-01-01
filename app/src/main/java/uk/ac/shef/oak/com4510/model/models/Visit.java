package uk.ac.shef.oak.com4510.model.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//The beginning of Areej part

/**
 * this is entity class uses to create a table in room database
 * for visit
 * @author Areej
 */

@Entity(tableName = "visit")

public class Visit implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String title;
    private String desscription;
    private String date;
    private double longitude;
    private double latitude;
    private double temp;
    private double pressure;


    public Visit(){

    }
    public Visit(String title,String desc,String date, double longitude, double latitude, double temp, double pressure) {
        this.title = title;
        this.date = date;
        this.desscription=desc;
        this.longitude = longitude;
        this.latitude = latitude;
        this.temp = temp;
        this.pressure = pressure;
    }
    public Visit(String title,String date, double longitude, double latitude, double temp, double pressure) {
        this.title = title;
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
        this.temp = temp;
        this.pressure = pressure;
    }

    /**
     * get visit description
     * @return String
     */
    public String getDesscription() {
        return desscription;
    }
    /**
     * set visit description
     * @return void
     */
    public void setDesscription(String desscription) {
        this.desscription = desscription;
    }
    /**
     * get visit id

     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * set visit id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * return visit title
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * set visit title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * return visit date
     * @return String
     */
    public String getDate() {
        return date;
    }

    /**
     * set date of visit
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }
    /**
     * return longitude of visit
     * @return double
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * set longitude of visit location
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * return latitude of visit
     * @return double
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * set latitude of visit location
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * return tempreture of visit location
     * @return double
     */
    public double getTemp() {
        return temp;
    }

    /**
     * set tempreature of visit location
     * @param temp
     */
    public void setTemp(double temp) {
        this.temp = temp;
    }

    /**
     * return preassure of visit location
     * @return
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * set preassure of visit location
     * @param pressure
     */
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
}

//The end of Areej part