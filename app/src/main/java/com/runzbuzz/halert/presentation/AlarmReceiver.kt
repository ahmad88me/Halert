package com.runzbuzz.halert.presentation

// Import the necessary packages
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.PowerManager
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.health.services.client.HealthServices
import androidx.health.services.client.data.DataType
import androidx.health.services.client.data.PassiveListenerConfig

// Define a BroadcastReceiver class that handles the alarm events
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
//        Log.d("alarm", "**** Now the alarm is fired ***")
//// Get the Vibrator service from the context
//        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//
//// Vibrate the device for one second
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//// For Android Oreo and above, use VibrationEffect
//            vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
//            Log.d("alarm", "Alarm 1")
//        } else {
//// For older versions, use the deprecated vibrate method
//            vibrator.vibrate(1000)
//            Log.d("alarm", "Alarm 2")
//        }

        // Another
        Log.d("HWorker", "Attempt 2")


        val ve = VibrationEffect.createOneShot(2000, 255)
        val vibrator2: Vibrator
//        val context = this.getApplicationContext()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Log.d("HWorker", "New version")

            val vmanager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
//        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator2 = vmanager.defaultVibrator
        } else{
            Log.d("HWorker", "Old version")
            vibrator2 = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        Log.d("HWorker", "hasVibrator: " + vibrator2.hasVibrator().toString())
        Log.d("HWorker", "Stopped the Vibration for now.")
        //vibrator2.vibrate(ve)



        val wakeLock: PowerManager.WakeLock =
            (context.getSystemService(Context.POWER_SERVICE) as PowerManager).run {
                newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyApp::MyWakelockTag").apply {
                    acquire()
                }
            }
        wakeLock.release()
//
//        val heartRateCallback = HeartMeasureCallback()
//        val healthClient = HealthServices.getClient(context /*context*/)
//        val measureClient = healthClient.measureClient
//        measureClient.registerMeasureCallback(DataType.HEART_RATE_BPM, heartRateCallback)
//



//                    val passiveListenerConfig = PassiveListenerConfig.builder()
//                .setDataTypes(setOf(DataType.HEART_RATE_BPM))
//                .build()
//            val healthClient = HealthServices.getClient(context /*context*/)
//            val passiveMonitoringClient = healthClient.passiveMonitoringClient
//            passiveMonitoringClient.setPassiveListenerServiceAsync()
    }
}
