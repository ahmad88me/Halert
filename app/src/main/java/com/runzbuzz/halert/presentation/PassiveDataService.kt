package com.runzbuzz.halert.presentation

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import androidx.health.services.client.PassiveListenerService
import androidx.health.services.client.data.*
import kotlinx.coroutines.runBlocking

class PassiveDataService : PassiveListenerService() {

    override fun onNewDataPointsReceived(dataPoints: DataPointContainer) {
        Log.d("PassiveDataService", " *** New data points received ")
//            val heart_bpm = dataPoints.getData(DataType.HEART_RATE_BPM).last().value.toString()
//                Log.d("PassiveDataService", "The new received data point: {$heart_bpm}")
        val heart_bpm = dataPoints.getData(DataType.HEART_RATE_BPM).last().value.toString()
        Log.d("PassiveDataService", "Got heart rate BPM: {$heart_bpm}")


        val context = applicationContext
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

    override fun onHealthEventReceived(event: HealthEvent) {
        runBlocking {
            Log.i("PassiveDataService", "event type: ")
            Log.i("PassiveDataService", event.toString())
            Log.i("PassiveDataService", event.type.toString())
        }
    }

}
