package com.runzbuzz.halert.presentation

// Import the necessary packages
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationChannel.DEFAULT_CHANNEL_ID
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.PowerManager
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.runzbuzz.halert.R


//class AlarmReceiver : WakefulBroadcastReceiver() {
// Define a BroadcastReceiver class that handles the alarm events
class AlarmReceiver : BroadcastReceiver() {

    private val TAG="AlarmReceiver"

    private var notificationId=0
//    lateinit var activity: Activity

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
        Log.d(TAG, "Alarm Receiver")


        val ve = VibrationEffect.createOneShot(1000, 255)
        val vibrator: Vibrator
//        val context = this.getApplicationContext()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Log.d(TAG, "New version")

            val vmanager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
//        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator = vmanager.defaultVibrator
        } else{
            Log.d(TAG, "Old version")
            vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        Log.d(TAG, "hasVibrator: " + vibrator.hasVibrator().toString())
//        Log.d("HWorker", "Stopped the Vibration for now.")
//        vibrator.vibrate(ve)


        createNotification5(context)


//        val wakeLock: PowerManager.WakeLock =
//            (context.getSystemService(Context.POWER_SERVICE) as PowerManager).run {
//                newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyApp::MyWakelockTag").apply {
//                    acquire()
//                }
//            }
//        wakeLock.release()
//
//
//
//        val wl = (context.getSystemService(Context.POWER_SERVICE) as PowerManager)
//        .newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Halert:AlarmReceiver")
//        wl.acquire()
//
//
//        //Log.d(TAG, "going to sleep for 5 seconds")
//        // to sleep
//        //TimeUnit.SECONDS.sleep(5L)
//        //Log.d(TAG, "walking up")
//
////        Toast.makeText(context, "Screen on ... ", Toast.LENGTH_SHORT).show()
//
//        //createNotificationChannel(context, intent)
//
//
//        wl.release()


//        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
//        mWakeLock = pm.newWakeLock((PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "YourServie");
//        mWakeLock.acquire();
//        [...]
//        mWakeLock.release();
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


    private fun createNotificationChannel(context: Context, intent: Intent) {
        Log.d(TAG, "createNotificationChannel")
//        val CHANNEL_ID = DEFAULT_CHANNEL_ID
        val CHANNEL_ID = "HalertChannel"
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d(TAG, "creating notification channel")

            val name = "The Name"//getString(R.string.channel_name)
            val descriptionText = "Some Descr"//getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            Log.d(TAG, "channel creation")

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



//        Log.d(TAG, "creating pending intent")
//        val pendingIntent = TaskStackBuilder.create(activity).run {
//        addNextIntentWithParentStack(intent)
//        getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
//        }



        Log.d(TAG, "creating a builder")

        var builder = NotificationCompat.Builder(context, CHANNEL_ID)
//            .setSmallIcon(R.drawable.notification_icon)
            .setSmallIcon(R.drawable.ic_noti)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
//            .setStyle(NotificationCompat.BigTextStyle()
//                .bigText("Much longer text that cannot fit one line..."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .extend(
                NotificationCompat.WearableExtender()
                    .setBridgeTag("tagOne")
            )

        Log.d(TAG, "will notify {$notificationId}")
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
            notificationId += 1
            Log.d(TAG, "notification ID: {$notificationId}")
        }
    }
}
