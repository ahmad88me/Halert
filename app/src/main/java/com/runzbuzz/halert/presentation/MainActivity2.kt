//package com.runzbuzz.halert.presentation
//
//
//// Import the necessary libraries
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
//import android.os.VibrationEffect
//import android.os.Vibrator
//import android.util.Log
//import androidx.compose.foundation.background
//import androidx.compose.ui.Modifier
//import androidx.fragment.app.FragmentActivity
//
//// Define a constant for the vibration interval in milliseconds
//const val VIBRATION_INTERVAL = 10000L
//
//class MainActivity2 : FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider {
//
//    // Create a handler and a runnable for the vibration logic
//    private val handler = Handler(Looper.getMainLooper())
//    private val vibrationRunnable = object : Runnable {
//        override fun run() {
//// Get the vibrator service from the context
//            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//
//// Create a vibration effect with a duration of 500 milliseconds
//            val vibrationEffect = VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)
//
//// Vibrate the watch with the effect
//            vibrator.vibrate(vibrationEffect)
//            Log.d("Activity2", "Vibrate")
//// Repeat the runnable after the vibration interval
//            handler.postDelayed(this, VIBRATION_INTERVAL)
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//// Set the content view using Jetpack Compose
//        setContent {
//// Use the ambient mode support to handle the ambient and active modes
//            val ambientController = AmbientModeSupport.attach(this)
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
//    }
//
//    override fun onResume() {
//        super.onResume()
//// Start the vibration runnable when the app is resumed
//        handler.post(vibrationRunnable)
//    }
//
//    override fun onPause() {
//        super.onPause()
//// Stop the vibration runnable when the app is paused
//        handler.removeCallbacks(vibrationRunnable)
//    }
//
//    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback {
//// Return an ambient callback that handles the ambient and active modes
//        return object : AmbientModeSupport.AmbientCallback() {
//            override fun onEnterAmbient(ambientDetails: Bundle?) {
//                super.onEnterAmbient(ambientDetails)
//// Stop the vibration runnable when entering ambient mode
//                handler.removeCallbacks(vibrationRunnable)
//            }
//
//            override fun onExitAmbient() {
//                super.onExitAmbient()
//// Start the vibration runnable when exiting ambient mode
//                handler.post(vibrationRunnable)
//            }
//        }
//    }
//}
