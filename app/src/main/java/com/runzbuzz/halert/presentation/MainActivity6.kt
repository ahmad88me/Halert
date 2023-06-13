package com.runzbuzz.halert.presentation


// Import the necessary libraries

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.FragmentActivity
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.runzbuzz.halert.R


//class MainActivity6 : ComponentActivity() {
class MainActivity6 : FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider {

    /*
 * Declare an ambient mode controller, which will be used by
 * the activity to determine if the current mode is ambient.
 */
    lateinit var ambientController: AmbientModeSupport.AmbientController

    private var notificationId = 10009123

    // In your main activity, declare the following variables
    //private lateinit var alarmMgr: AlarmManager
    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ambientController = AmbientModeSupport.attach(this)
        this.setTurnScreenOn(true)
        setContent {
            WearApp(greetingName = "Hello", this)
        }

        // In your onCreate method, initialize the alarm manager and the pending intent
        alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(this, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(this, 0, intent, 0)
        }

        // To start the alarm, use the setInexactRepeating method with a 20-second interval
        alarmMgr?.setRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime(),
            5 * 1000,
            alarmIntent
        )

//                    alarmMgr?.setInexactRepeating(
//            AlarmManager.ELAPSED_REALTIME_WAKEUP,
//            SystemClock.elapsedRealtime(),
//            10 * 1000,
//            alarmIntent
//        )


//        // active
//        val heartRateCallback = HeartMeasureCallback()
//        val healthClient = HealthServices.getClient(this /*context*/)
//        val measureClient = healthClient.measureClient
//        heartRateCallback.context = this
//        measureClient.registerMeasureCallback(DataType.HEART_RATE_BPM, heartRateCallback)
//


// passive test
//           val passiveListenerConfig = PassiveListenerConfig.builder()
//                .setDataTypes(setOf(DataType.HEART_RATE_BPM))
//                .build()
////            val healthClient = HealthServices.getClient(this *//*context*//*)
//            val passiveMonitoringClient = healthClient.passiveMonitoringClient
//        // service
//        passiveMonitoringClient.setPassiveListenerServiceAsync(PassiveDataService::class.java,
//            passiveListenerConfig
//        )


        // call back

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


//        createNotificationChannel2(this)
//        createNotificationSample(this)
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


//    fun createNotificationChannel(context: Context) {
//        val TAG = "MainActivity"
//        Log.d(TAG, "createNotificationChannel")
////        val CHANNEL_ID = NotificationChannel.DEFAULT_CHANNEL_ID
//        val CHANNEL_ID = "HalertChannel"
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            Log.d(TAG, "creating notification channel")
//
//            val name = "The Name"//getString(R.string.channel_name)
//            val descriptionText = "Some Descr"//getString(R.string.channel_description)
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//
//            Log.d(TAG, "channel creation {$CHANNEL_ID}")
//
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                description = descriptionText
//            }
////            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
////                description = descriptionText
////            }
//            Log.d(TAG, "channel registration")
//
//            // Register the channel with the system
//            val notificationManager: NotificationManager =
//                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            Log.d(TAG, "Adding Channel to the Manager")
//
//            notificationManager.createNotificationChannel(channel)
//        }
//
//
//        Log.d(TAG, "creating pending intent")
//        val pendingIntent = TaskStackBuilder.create(this).run {
//            addNextIntentWithParentStack(intent)
//            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
//        }
//
//        Log.d(TAG, "creating a builder")
//
//
//        var builder = NotificationCompat.Builder(context, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_noti)
////            .setSmallIcon(androidx.core.R.drawable.notification_bg)
//            .setContentTitle("My notification")
//            .setContentText("Much longer text that cannot fit one line...")
////            .setStyle(NotificationCompat.BigTextStyle()
////                .bigText("Much longer text that cannot fit one line..."))
////            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setContentIntent(pendingIntent)
//            .setPriority(NotificationCompat.PRIORITY_MAX)
//            .setAutoCancel(true)
//
//        Log.d(TAG, "will notify {$notificationId}")
//        with(NotificationManagerCompat.from(context)) {
//            // notificationId is a unique int for each notification that you must define
//            notify(notificationId, builder.build())
//            notificationId += 1
//            Log.d(TAG, "notification ID: {$notificationId}")
//
//        }
//
//    }
}


@Composable
fun WearApp(greetingName: String, ac: FragmentActivity? = null) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
                Log.d("Activity", "Button is clicked")
                Log.d("Click", "Attempt 2")
                val ve = VibrationEffect.createOneShot(2000, 255)
                val vibrator2: Vibrator
//        val context = this.getApplicationContext()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    Log.d("Click", "New version")
                    val vmanager =
                        context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
//        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibrator2 = vmanager.defaultVibrator
                } else {
                    Log.d("Click", "Old version")
                    vibrator2 = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                }
                Log.d("Click", "hasVibrator: " + vibrator2.hasVibrator().toString())
                vibrator2.vibrate(ve)

                createNotificationSample(context)


                createNotificationChannel2(context)
//                createNotificationSample(this)

                //createNotificationChannel(context)


//                ac.createNotificationChannel(context)

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


            }, modifier = Modifier
                .padding(top = 8.dp)
                .height(height = 40.dp)
                .width(width = 150.dp)
        ) {
            Text("Read Heart Rate")
        }
    }

}


@Composable
fun WearApp2(greetingName: String, alarmMgr: AlarmManager, alarmIntent: PendingIntent, context: Context) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {
                Log.d("Activity", "Button is clicked")
//                createNotificationChannel(context)
// To start the alarm, use the setInexactRepeating method with a 20-second interval
                alarmMgr?.setInexactRepeating(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime(),
                    20 * 1000,
                    alarmIntent
                )
            }, modifier = Modifier
                .padding(top = 8.dp)
                .height(height = 40.dp)
                .width(width = 150.dp)
        ) {
            Text("Read Heart Rate")
        }
    }

}


@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}



fun createNotificationChannel1(context: Context) {
    val TAG = "External from MainActivity"
    val notificationId=1
    Log.d(TAG, "createNotificationChannel2")
//        val CHANNEL_ID = NotificationChannel.DEFAULT_CHANNEL_ID
    val CHANNEL_ID = "HalertChannel2"
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Log.d(TAG, "creating notification channel")

        val name = "The Name"//getString(R.string.channel_name)
        val descriptionText = "Some Descr"//getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        Log.d(TAG, "channel creation {$CHANNEL_ID}")

        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        Log.d(TAG, "channel registration")

        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        Log.d(TAG, "Adding Channel to the Manager")

        notificationManager.createNotificationChannel(channel)
    }


//    Log.d(TAG, "creating pending intent")
//    val pendingIntent = TaskStackBuilder.create(activity).run {
//        addNextIntentWithParentStack(intent)
//        getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
//    }

    Log.d(TAG, "creating a builder")







    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_noti)
//            .setSmallIcon(androidx.core.R.drawable.notification_bg)
        .setContentTitle("My notification")
        .setContentText("Much longer text that cannot fit one line...")
//            .setStyle(NotificationCompat.BigTextStyle()
//                .bigText("Much longer text that cannot fit one line..."))
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//        .setContentIntent(pendingIntent)
        .setPriority(NotificationCompat.PRIORITY_MAX)
        .setAutoCancel(true)

    Log.d(TAG, "will notify {$notificationId}")
    with(NotificationManagerCompat.from(context)) {
        // notificationId is a unique int for each notification that you must define
        notify(notificationId, builder.build())
//        notificationId += 1
        Log.d(TAG, "notification ID: {$notificationId}")

    }

}


fun createNotificationChannel2(context: Context) {
    val TAG = "External from MainActivity"
    val notificationId=10
    Log.d(TAG, "createNotificationChannel2")
    val CHANNEL_ID = "HalertChannel2"
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Log.d(TAG, "creating notification channel")
        val name = "The Name"//getString(R.string.channel_name)
        val descriptionText = "Some Descr"//getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        Log.d(TAG, "channel creation {$CHANNEL_ID}")

        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        Log.d(TAG, "channel registration")

        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        Log.d(TAG, "Adding Channel to the Manager")

        notificationManager.createNotificationChannel(channel)
    }


//    Log.d(TAG, "creating pending intent")
//    val pendingIntent = TaskStackBuilder.create(activity).run {
//        addNextIntentWithParentStack(intent)
//        getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
//    }

    Log.d(TAG, "creating a builder")


    val notification: Notification = NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle("New mail from " )
        .setContentText("Some subject")
        .setSmallIcon(com.runzbuzz.halert.R.drawable.ic_noti)
        .setLocalOnly(true)
        .extend(
            NotificationCompat.WearableExtender()
               // .setContentIcon(com.runzbuzz.halert.R.drawable.ic_noti)
        )
        .build()
    NotificationManagerCompat.from(context).notify(0, notification)


//    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
//        .setSmallIcon(R.drawable.ic_noti)
////            .setSmallIcon(androidx.core.R.drawable.notification_bg)
//        .setContentTitle("My notification")
//        .setContentText("Much longer text that cannot fit one line...")
////            .setStyle(NotificationCompat.BigTextStyle()
////                .bigText("Much longer text that cannot fit one line..."))
////            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
////        .setContentIntent(pendingIntent)
//        .setPriority(NotificationCompat.PRIORITY_MAX)
//        .setAutoCancel(true)
//
//    Log.d(TAG, "will notify {$notificationId}")
//    with(NotificationManagerCompat.from(context)) {
//        // notificationId is a unique int for each notification that you must define
//        notify(notificationId, builder.build())
////        notificationId += 1
//        Log.d(TAG, "notification ID: {$notificationId}")
//
//    }

}









fun createNotificationSample(context: Context) {
// Create a notification channel with ID "my_channel"
    val channel = NotificationChannel("my_channel", "My Channel", NotificationManager.IMPORTANCE_DEFAULT)
// Register the channel with the system
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
// Build the notification with a title, text, icon, and channel ID
    val builder = NotificationCompat.Builder(context, "my_channel")
        .setContentTitle("My Notification")
        .setContentText("Hello World!")
        .setSmallIcon(com.runzbuzz.halert.R.drawable.ic_noti)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
// Issue the notification with an ID of 1
    NotificationManagerCompat.from(context).notify(1, builder.build())
}