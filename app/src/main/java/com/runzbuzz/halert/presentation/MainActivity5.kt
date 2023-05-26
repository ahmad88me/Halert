//package com.runzbuzz.halert.presentation
//
//
//// Import the necessary libraries
//import android.Manifest
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.runtime.Composable
//import androidx.wear.compose.material.MaterialTheme
//import androidx.wear.compose.material.Scaffold
//import androidx.wear.compose.material.TimeText
//import androidx.wear.ambient.AmbientModeSupport
//import android.content.Context
//import android.content.pm.PackageManager
//import android.os.VibrationEffect
//import android.os.Vibrator
//import android.util.Log
//import androidx.compose.foundation.background
//import androidx.compose.ui.Modifier
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.FragmentActivity
//import androidx.health.services.client.HealthServices
//import androidx.health.services.client.HealthServicesClient
//import androidx.health.services.client.data.DataType
//import com.google.android.gms.wearable.DataApi
//import com.google.android.gms.wearable.DataClient
//import com.google.android.gms.wearable.DataEventBuffer
//
//import android.app.AlarmManager
//import android.app.PendingIntent
//import android.content.BroadcastReceiver
//import android.content.Intent
//import java.util.concurrent.TimeUnit
//
//// Define a constant for the vibration interval in milliseconds
//const val VIBRATION_INTERVAL = 10000L
//
//class MainActivity5 :  FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider {
//
//    // Get the health services client and register a data listener
//    private lateinit var healthServicesClient: HealthServicesClient
//    private lateinit var dataListener: DataApi.DataListener
//
//    private lateinit var ambientController: AmbientModeSupport.AmbientController
//
//    // Action for updating the display in ambient mode, per our custom refresh cycle
////    private const val AMBIENT_UPDATE_ACTION = "com.your.package.action.AMBIENT_UPDATE"
//
//    // Milliseconds between waking processor/screen for updates
//    private val AMBIENT_INTERVAL_MS: Long = TimeUnit.SECONDS.toMillis(20)
//
//    private lateinit var ambientUpdateAlarmManager: AlarmManager
//    private lateinit var ambientUpdatePendingIntent: PendingIntent
//    private lateinit var ambientUpdateBroadcastReceiver: BroadcastReceiver
//
//
//
////    // Create a handler and a runnable for the vibration logic
////    private val handler = Handler(Looper.getMainLooper())
////    private val vibrationRunnable = object : Runnable {
////        override fun run() {
////// Get the vibrator service from the context
////            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
////
////// Create a vibration effect with a duration of 500 milliseconds
////            val vibrationEffect = VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)
////
////// Vibrate the watch with the effect
////            vibrator.vibrate(vibrationEffect)
////            Log.d("Activity5", "Vibrate")
////// Repeat the runnable after the vibration interval
////            handler.postDelayed(this, VIBRATION_INTERVAL)
////        }
////    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        ambientUpdateAlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//
////        ambientUpdatePendingIntent = Intent(AMBIENT_UPDATE_ACTION).let { ambientUpdateIntent ->
////            PendingIntent.getBroadcast(this, 0, ambientUpdateIntent, PendingIntent.FLAG_UPDATE_CURRENT)
////        }
//
//        ambientUpdateBroadcastReceiver = object : BroadcastReceiver() {
//            override fun onReceive(context: Context, intent: Intent) {
//                refreshDisplayAndSetNextUpdate()
//            }
//        }
//
////// Create a data listener that prints the heart rate value to logcat
////        dataListener = object : DataClient.OnDataChangedListener {
////            override fun onDataChanged(dataeventbuffer: DataEventBuffer) {
////                dataeventbuffer.
////                if (data.dataType == DataType.HEART_RATE_BPM) {
////                    val heartRate = data.value.asFloat()
////                    Log.d("HeartRate", "Heart rate: $heartRate bpm")
////                }
////            }
////            override fun onD(data: Data) {
////                if (data.dataType == DataType.HEART_RATE_BPM) {
////                    val heartRate = data.value.asFloat()
////                    Log.d("HeartRate", "Heart rate: $heartRate bpm")
////                }
////            }
////        }
//
//// Register the data listener for heart rate data with a sampling period of 60 seconds
////        healthServicesClient.dataClient.registerDataListener(
////            DataType.HEART_RATE_BPM,
////            SamplingRate.PERIODIC(60_000_000),
////            dataListener)
//
//        ambientController = AmbientModeSupport.attach(this)
//
//        // Check and request the body sensors permission
//        checkBodySensorsPermission()
//
//
////        // Get the health services client
////        val heartRateCallback = HeartMeasureCallback()
////        val healthClient = HealthServices.getClient(this)
////        val measureClient = healthClient.measureClient
////        measureClient.registerMeasureCallback(DataType.HEART_RATE_BPM, heartRateCallback)
//
//// Set the content view using Jetpack Compose
//        setContent {
//// Use the ambient mode support to handle the ambient and active modes
//// Use the Material theme for Wear OS
//            MaterialTheme {
//// Use a scaffold to display the app content and background
//                Scaffold(
//                    modifier = Modifier.background(MaterialTheme.colors.background),
//                    content = {
//// Display a text with the current time
//                        TimeText()
//                    }
//                )
//            }
//        }
//
//
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//// Start the vibration runnable when the app is resumed
////        handler.post(vibrationRunnable)
//    }
//
//    override fun onPause() {
//        super.onPause()
//// Stop the vibration runnable when the app is paused
////      handler.removeCallbacks(vibrationRunnable)
//    }
//
//    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback {
//// Return an ambient callback that handles the ambient and active modes
//        return object : AmbientModeSupport.AmbientCallback() {
//            override fun onEnterAmbient(ambientDetails: Bundle?) {
//                super.onEnterAmbient(ambientDetails)
//// Stop the vibration runnable when entering ambient mode
//                Log.d("Ambient", "Entering Ambient Mode")
//                refreshDisplayAndSetNextUpdate()
//
////                handler.removeCallbacks(vibrationRunnable)
//            }
//
//            override fun onUpdateAmbient() {
//                super.onUpdateAmbient()
//                refreshDisplayAndSetNextUpdate()
//            }
//
//            override fun onExitAmbient() {
//                super.onExitAmbient()
//                Log.d("Ambient", "Leaving Ambient Mode")
//// Start the vibration runnable when exiting ambient mode
////                handler.post(vibrationRunnable)
//                ambientUpdateAlarmManager.cancel(ambientUpdatePendingIntent)
//
//            }
//        }
//    }
//
//
//
//
//
//
//
//
//
//    // Request the permission at runtime if needed
//    private val REQUEST_CODE_BODY_SENSORS = 1
//    private fun checkBodySensorsPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BODY_SENSORS)
//            != PackageManager.PERMISSION_GRANTED) {
//// Permission is not granted
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.BODY_SENSORS)) {
//// Show an explanation to the user
//            } else {
//// No explanation needed, request the permission
//                ActivityCompat.requestPermissions(this,
//                    arrayOf(Manifest.permission.BODY_SENSORS),
//                    REQUEST_CODE_BODY_SENSORS)
//            }
//        } else {
//// Permission has already been granted
//        }
//    }
//
//    // Handle the permission result
//    override fun onRequestPermissionsResult(requestCode: Int,
//                                            permissions: Array<String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            REQUEST_CODE_BODY_SENSORS -> {
//// If request is cancelled, the result arrays are empty.
//                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//// Permission was granted, yay!
//                } else {
//// Permission denied, boo!
//                }
//                return
//            }
//        }
//    }
//
//
//
//
//
//
//
//    private fun refreshDisplayAndSetNextUpdate() {
//        var isAmbient = true
//        if (isAmbient) {
//            // Implement data retrieval and update the screen for ambient mode
//        } else {
//            // Implement data retrieval and update the screen for interactive mode
//        }
//        val timeMs: Long = System.currentTimeMillis()
//        // Schedule a new alarm
//        if (isAmbient) {
//            // Calculate the next trigger time
//            val delayMs: Long = AMBIENT_INTERVAL_MS - timeMs % AMBIENT_INTERVAL_MS
//            val triggerTimeMs: Long = timeMs + delayMs
//            ambientUpdateAlarmManager.setExact(
//                AlarmManager.RTC_WAKEUP,
//                triggerTimeMs,
//                ambientUpdatePendingIntent)
//        } else {
//            // Calculate the next trigger time for interactive mode
//        }
//    }
//
//
//    override fun onDestroy() {
//        ambientUpdateAlarmManager.cancel(ambientUpdatePendingIntent)
//        super.onDestroy()
//    }
//}
