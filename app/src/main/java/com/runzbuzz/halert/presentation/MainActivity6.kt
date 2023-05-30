package com.runzbuzz.halert.presentation


// Import the necessary libraries
import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.ambient.AmbientModeSupport
import android.content.Context
import android.content.pm.PackageManager
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.health.services.client.HealthServices
import androidx.health.services.client.HealthServicesClient
import androidx.health.services.client.data.DataType
import com.google.android.gms.wearable.DataApi
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEventBuffer

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import android.os.VibratorManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.health.services.client.PassiveListenerCallback
import androidx.health.services.client.data.DataPointContainer
import androidx.health.services.client.data.PassiveListenerConfig
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import androidx.work.await
import java.util.concurrent.TimeUnit


//class MainActivity6 : ComponentActivity() {
class MainActivity6 : FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider {

    /*
 * Declare an ambient mode controller, which will be used by
 * the activity to determine if the current mode is ambient.
 */
    lateinit var ambientController: AmbientModeSupport.AmbientController

    // In your main activity, declare the following variables
    //private lateinit var alarmMgr: AlarmManager
    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ambientController = AmbientModeSupport.attach(this)

        setContent {
            WearApp(greetingName = "Hello")
        }


        // In your onCreate method, initialize the alarm manager and the pending intent
        alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(this, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(this, 0, intent, 0)
        }

        // To start the alarm, use the setInexactRepeating method with a 20-second interval
        alarmMgr?.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime(),
            5 * 1000,
            alarmIntent
        )


//        // active
//        val heartRateCallback = HeartMeasureCallback()
//        val healthClient = HealthServices.getClient(this /*context*/)
//        val measureClient = healthClient.measureClient
//        measureClient.registerMeasureCallback(DataType.HEART_RATE_BPM, heartRateCallback)
//
//





// passive test
            val passiveListenerConfig = PassiveListenerConfig.builder()
                .setDataTypes(setOf(DataType.HEART_RATE_BPM))
                .build()
            val healthClient = HealthServices.getClient(this /*context*/)
            val passiveMonitoringClient = healthClient.passiveMonitoringClient
        passiveMonitoringClient.setPassiveListenerServiceAsync(PassiveDataService::class.java,
            passiveListenerConfig
        )
//        val passiveListenerCallback: PassiveListenerCallback = object : PassiveListenerCallback {
//            override fun onNewDataPointsReceived(dataPoints: DataPointContainer) {
//                // TODO: Do something with dataPoints
//                Log.d("PassiveListenerCallback", "Passive Listener Callback")
//            }
//        }
//
//        passiveMonitoringClient.setPassiveListenerCallback(
//            passiveListenerConfig,
//            passiveListenerCallback
//        )






//        // Create an intent to trigger the alarm receiver
//        val intent = Intent(this, AlarmReceiver::class.java)
//// Create a pending intent to wrap the intent
//        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
//// Get an instance of the AlarmManager service
//        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
//// Set a repeating alarm that will fire every 30 seconds
//        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//            SystemClock.elapsedRealtime(),
//            5 * 1000,
//            pendingIntent)
//



//
//        val context = this //getApplicationContext()//this//LocalContext.current
//
//
//        val ve = VibrationEffect.createOneShot(2000, 255)
//        val vibrator2: Vibrator
////        val context = this.getApplicationContext()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            Log.d("Main", "New version")
//
//            val vmanager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
////        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//            vibrator2 = vmanager.defaultVibrator
//        } else{
//            Log.d("Main", "Old version")
//            vibrator2 = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//        }
//        Log.d("Main", "hasVibrator: " + vibrator2.hasVibrator().toString())
//        vibrator2.vibrate(ve)
//
//
//












//        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//
//// Vibrate the device for one second
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//// For Android Oreo and above, use VibrationEffect
//            vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
//            Log.d("main-alarm", "Main 1")
//        } else {
//// For older versions, use the deprecated vibrate method
//            vibrator.vibrate(1000)
//            Log.d("main-alarm", "Main 2")
//        }




    }

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback {
// Return an ambient callback that handles the ambient and active modes
        return object : AmbientModeSupport.AmbientCallback() {
            override fun onEnterAmbient(ambientDetails: Bundle?) {
                Log.d("Ambient", "Entering Ambient Mode")

                super.onEnterAmbient(ambientDetails)
// Stop the vibration runnable when entering ambient mode
//                handler.removeCallbacks(vibrationRunnable)
            }

            override fun onUpdateAmbient() {
                Log.d("Ambient", "Updating Ambient Mode")

                super.onUpdateAmbient()

            }

            override fun onExitAmbient() {
                Log.d("Ambient", "Leaving Ambient Mode")

                super.onExitAmbient()
                Log.d("Ambient", "Leaving Ambient Mode")
            }
        }
    }
}


@Composable
fun WearApp(greetingName: String) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            Log.d("Activity", "Button is clicked")
            Log.d("Click", "Attempt 2")
            val ve = VibrationEffect.createOneShot(2000, 255)
            val vibrator2: Vibrator
//        val context = this.getApplicationContext()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                Log.d("Click", "New version")
                val vmanager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
//        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                vibrator2 = vmanager.defaultVibrator
            } else{
                Log.d("Click", "Old version")
                vibrator2 = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            }
            Log.d("Click", "hasVibrator: " + vibrator2.hasVibrator().toString())
            vibrator2.vibrate(ve)


//            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//
//// Vibrate the device for one second
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//// For Android Oreo and above, use VibrationEffect
//                vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
//                Log.d("click-alarm", "Click 1")
//            } else {
//// For older versions, use the deprecated vibrate method
//                vibrator.vibrate(1000)
//                Log.d("click-alarm", "Click 2")
//            }



        }, modifier= Modifier
            .padding(top = 8.dp)
            .height(height = 40.dp)
            .width(width = 150.dp)){
            Text("Read Heart Rate")
        }
    }

}


    @Composable
fun WearApp2(greetingName: String,alarmMgr: AlarmManager, alarmIntent: PendingIntent ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center
        ) {

                    Button(onClick = {
                        Log.d("Activity", "Button is clicked")

// To start the alarm, use the setInexactRepeating method with a 20-second interval
                        alarmMgr?.setInexactRepeating(
                            AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            SystemClock.elapsedRealtime(),
                            20 * 1000,
                            alarmIntent
                        )
                    }, modifier= Modifier
                        .padding(top = 8.dp)
                        .height(height = 40.dp)
                        .width(width = 150.dp)){
            Text("Read Heart Rate")
        }
        }

}



@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}



