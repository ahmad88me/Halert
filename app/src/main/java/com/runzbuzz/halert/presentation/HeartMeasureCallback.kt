package com.runzbuzz.halert.presentation

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import androidx.health.services.client.MeasureCallback
import androidx.health.services.client.data.Availability
import androidx.health.services.client.data.DataPointContainer
import androidx.health.services.client.data.DataType
import androidx.health.services.client.data.DataTypeAvailability
import androidx.health.services.client.data.DeltaDataType



class HeartMeasureCallback: MeasureCallback {
    lateinit var context: Context
    override fun onAvailabilityChanged(dataType: DeltaDataType<*, *>, availability: Availability) {
        if (availability is DataTypeAvailability) {
            Log.d("Health", "Availability is changed")
            Log.d("Health", availability.toString())

            // Handle availability change.
        }
    }

    override fun onDataReceived(data: DataPointContainer) {
        // Inspect data points.
        Log.d("Health", "Data is received")
        Log.d("Health", data.toString())
        Log.d("Health", data.getData(DataType.HEART_RATE_BPM).last().toString())
        Log.d("Health", data.getData(DataType.HEART_RATE_BPM).last().dataType.toString())
        Log.d("Health", data.getData(DataType.HEART_RATE_BPM).last().value.toString())
//        val heartRate = data.getData(DataType.HEART_RATE_BPM).last()
//        Log.d("HeartRate", "Heart rate: $heartRate bpm")
        runAlarm(data.getData(DataType.HEART_RATE_BPM).last().value.toFloat())
    }

    fun runAlarm(heart_bpm: Float){
        Log.d("Health", "Run Alarm {$heart_bpm}")


        val ve = VibrationEffect.createOneShot(2000, 255)
        val vibrator2: Vibrator
//        val context = this.getApplicationContext()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Log.d("PassiveDataService", "New version")

            val vmanager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
//        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator2 = vmanager.defaultVibrator
        } else{
            Log.d("PassiveDataService", "Old version")
            vibrator2 = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        Log.d("PassiveDataService", "hasVibrator: " + vibrator2.hasVibrator().toString())
//        Log.d("HWorker", "Stopped the Vibration for now.")
        vibrator2.vibrate(ve)


    }

    override fun onRegistered() {
        super.onRegistered()
        Log.d("Health", "Registered")
    }

    override fun onRegistrationFailed(throwable: Throwable) {
        super.onRegistrationFailed(throwable)
        Log.d("Health", "Registration failed")
    }
}
