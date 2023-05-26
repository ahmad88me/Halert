//package com.runzbuzz.halert.presentation
//
//// Import the necessary libraries
//import android.content.Context
//import android.os.VibrationEffect
//import android.os.Vibrator
//import androidx.work.Worker
//import androidx.work.WorkerParameters
//
//// Define a constant for the vibration interval in milliseconds
////const val VIBRATION_INTERVAL = 20000L
//
//// Define a worker class that extends Worker
//class VibrationWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
//
//    // Override the doWork() method to perform the background task
//    override fun doWork(): Result {
//// Get the vibrator service from the application context
//        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//
//// Create a vibration effect with a duration of 500 milliseconds
//        val vibrationEffect = VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE)
//
//// Vibrate the watch with the effect
//        vibrator.vibrate(vibrationEffect)
//
//// Return success and reschedule the worker after the vibration interval
//        return Result.success()//#.setMinimumLatency(VIBRATION_INTERVAL)
//    }
//}