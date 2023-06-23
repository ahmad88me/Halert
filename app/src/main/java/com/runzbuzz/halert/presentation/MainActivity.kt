package com.runzbuzz.halert.presentation


// Import the necessary libraries

import android.Manifest
import android.Manifest.permission.BODY_SENSORS
import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.health.services.client.HealthServices
import androidx.health.services.client.data.DataType
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.runzbuzz.halert.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch


class MainActivity : FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider {
    /*
 * Declare an ambient mode controller, which will be used by
 * the activity to determine if the current mode is ambient.
 */
    lateinit var ambientController: AmbientModeSupport.AmbientController
    var latest_heart_bps = 0
    var latest_read = ""
    // In your main activity, declare the following variables
    //private lateinit var alarmMgr: AlarmManager
    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent






    fun getLatestHeartBPS(): Int {
        return latest_heart_bps
    }

    fun getLatestRead(): String{
        return latest_read
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val TAG = "Main"
        super.onCreate(savedInstanceState)

        val viewModel: DiceRollViewModel by viewModels()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    // Update UI elements
                }
            }
        }

        ambientController = AmbientModeSupport.attach(this)
        this.setTurnScreenOn(true)
        setContent {
            WearApp()
        }

        requestPermissions()

        // In your onCreate method, initialize the alarm manager and the pending intent
        alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(this, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(this, 0, intent, 0)
        }

        alarmMgr?.setRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime(),
            60 * 1000,
            alarmIntent
        )

//        // active
        val heartRateCallback = HeartMeasureCallback()
        val healthClient = HealthServices.getClient(this /*context*/)
        val measureClient = healthClient.measureClient
        heartRateCallback.context = this
        heartRateCallback.viewModel = viewModel
        measureClient.registerMeasureCallback(DataType.HEART_RATE_BPM, heartRateCallback)
        createNotificationChannel(this)
    }
    
    private fun requestPermissions(){
        val TAG="Main-request-permissions"
        Log.d(TAG, "requesting permissions...")
        ActivityCompat.requestPermissions(this,
            listOf(Manifest.permission.BODY_SENSORS, Manifest.permission.POST_NOTIFICATIONS).toTypedArray(), 1)
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
fun WearApp() {
    val context = LocalContext.current
    val viewModel: DiceRollViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text("Read Heart Rate $uiState")
//        Button(
//            onClick = {
//                Log.d("Activity", "Button is clicked")
//                Log.d("Click", "Attempt 2")
//                val ve = VibrationEffect.createOneShot(2000, 255)
//                val vibrator2: Vibrator
////        val context = this.getApplicationContext()
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//                    Log.d("Click", "New version")
//                    val vmanager =
//                        context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
////        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//                    vibrator2 = vmanager.defaultVibrator
//                } else {
//                    Log.d("Click", "Old version")
//                    vibrator2 = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//                }
//                Log.d("Click", "hasVibrator: " + vibrator2.hasVibrator().toString())
//                vibrator2.vibrate(ve)
//
////                createNotificationSample(context)
//
//                createNotification5(context)
//
//
//            }, modifier = Modifier
//                .padding(top = 8.dp)
//                .height(height = 40.dp)
//                .width(width = 150.dp)
//        ) {
//            Text("Read Heart Rate")
//        }
    }

}


@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}


fun createNotification5(context: Context){
    val TAG="Notification5"
    val CHANNEL_ID="Halert"
    val notificationId=1

    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle("A title")
        .setContentText("Notification Description")
        .setAutoCancel(true)
        .setSmallIcon(androidx.media.R.drawable.notification_bg)
//        .setOngoing(true)
//        .extend(NotificationCompat.WearableExtender().setContentIcon(androidx.media.R.drawable.notification_bg))
    Log.d(TAG, "Created the builder")
    var notification = builder.build()
    NotificationManagerCompat.from(context).notify(0, notification);
    Log.d(TAG, "The notification should show")

//    cancelAllNotifications(context)

}

private fun createNotificationChannel(context: Context) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    val TAG = "Notification Channel"
    val CHANNEL_ID="Halert"
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = CHANNEL_ID//"name"
        val descriptionText = "the halert channel"
//        val importance = NotificationManager.IMPORTANCE_DEFAULT
//        val importance = NotificationManager.IMPORTANCE_LOW
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(notificationManager.areNotificationsEnabled()){
            Log.d(TAG, "Notifications are enabled")
        }
        else{
            Log.d(TAG, "Notifications are not")
        }
        notificationManager.createNotificationChannel(channel)
    }
    Log.d("Notification", "Notification Channel is created")
}

fun cancelAllNotifications(context: Context){
    val TAG="Main-cancel-notifications"
    Log.d(TAG, "will attempt to cancel all notifications")
    val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.cancelAll()
    Log.d(TAG, "No notification should be showing now")
}


class DiceRollViewModel : ViewModel() {
    // Expose screen UI state
    private val _uiState = MutableStateFlow(0)
    val uiState: StateFlow<Int> = _uiState.asStateFlow()

    // Handle business logic
    fun rollDice(newval: Int) {
        _uiState.value = newval
    }
}