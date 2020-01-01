package uk.ac.shef.oak.com4510.model.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.util.Log;

public class Temperature {
    private static final String TAG = Temperature.class.getSimpleName();
    private long mSamplingRateInMSecs;
    private long mSamplingRateNano;
    private SensorEventListener mTemperatureListener = null;
    private SensorManager mSensorManager;
    private Sensor mTemperatureSensor;
    private long timePhoneWasLastRebooted = 0;
    private long TEMPERATURE_READING_FREQUENCY= 20000;
    private float lastReportTime = 0;

    public float getLastReportTime() {
        return lastReportTime;
    }

    private boolean started;
    private Accelerometer accelerometer;
    /**
     * this is used to stop the temperature measurement if we have not seen any movement in the last 200 seconds
     */
    private static final long STOPPING_THRESHOLD = (long)200000;


    public Temperature(Context context) {
        // http://androidforums.com/threads/how-to-get-time-of-last-system-boot.548661/
        timePhoneWasLastRebooted = System.currentTimeMillis() - SystemClock.elapsedRealtime();

        mSamplingRateNano = (long) (TEMPERATURE_READING_FREQUENCY) * 1000000;
        mSamplingRateInMSecs = (long) TEMPERATURE_READING_FREQUENCY;
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mTemperatureSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        initTemperatureListener();
    }

    /**
     * it inits the listener and establishes the actions to take when a reading is available
     */
    private void initTemperatureListener() {
        if (!standardTemperatureSensorAvailable()) {
            Log.d(TAG, "Standard Barometer unavailable");
        } else {
            Log.d(TAG, "Using Barometer");
            mTemperatureListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    float diff = event.timestamp - lastReportTime;
                    // time is in nanoseconds it represents the set reference times the first time we come here
                    // set event timestamp to current time in milliseconds
                    // see answer 2 at http://stackoverflow.com/questions/5500765/accelerometer-sensorevent-timestamp
                    // the following operation avoids reporting too many events too quickly - the sensor may always
                    // misbehave and start sending data very quickly
                    if (diff >= mSamplingRateNano) {
                        long actualTimeInMseconds = timePhoneWasLastRebooted + (long) (event.timestamp / 1000000.0);
                        float temperatureValue = event.values[0];

                        int accuracy = event.accuracy;

                        Log.i(TAG, Utilities.mSecsToString(actualTimeInMseconds) + ": current temperature: " + temperatureValue + " with accuracy: " + accuracy);
                        lastReportTime = event.timestamp;
                        // if we have not see any movement on the side of the accelerometer, let's stop
                        long timeLag= actualTimeInMseconds-accelerometer.getLastReportTime();
                        if (timeLag> STOPPING_THRESHOLD)
                            stopBarometer();
                    }
                }
                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                }
            };
        }

    }


    /**
     * it returns true if the sensor is available
     * @return
     */
    public boolean standardTemperatureSensorAvailable() { return (mTemperatureSensor != null); }

    /**
     * it starts the pressure monitoring
     * @param accelerometer
     */
    public void startSensingTemperature(Accelerometer accelerometer) {
        this.accelerometer = accelerometer;
        // if the sensor is null,then mSensorManager is null and we get a crash
        if (standardTemperatureSensorAvailable()) {
            Log.d("Standard Temperature", "starting listener");
            // delay is in microseconds (1millisecond=1000 microseconds)
            // it does not seem to work though
            // stopBarometer();
            // otherwise we stop immediately because
            mSensorManager.registerListener(mTemperatureListener, mTemperatureSensor, (int) (mSamplingRateInMSecs * 1000));
            setStarted(true);
        } else {
            Log.i(TAG, "barometer unavailable or already active");
        }
    }


    /**
     * this stops the barometer
     */
    public void stopBarometer() {
        if (standardTemperatureSensorAvailable()) {
            Log.d("Standard Temperature", "Stopping listener");
            try {
                mSensorManager.unregisterListener(mTemperatureListener);
            } catch (Exception e) {
                // probably already unregistered
            }
        }
        setStarted(false);
    }

    /**
     * returns true if the barometer is currently working
     * @return
     */

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}

