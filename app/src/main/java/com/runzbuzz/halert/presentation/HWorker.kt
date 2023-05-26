//package com.runzbuzz.halert.presentation
//
//import android.content.Context
//import android.os.Build
//import android.os.VibrationAttributes
//import android.os.VibrationEffect
//import android.os.Vibrator
//import android.os.VibratorManager
//import android.util.Log
//import androidx.annotation.RequiresApi
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.work.Worker
//import androidx.work.WorkerParameters
//import java.time.LocalDate
//
//class HWorker(appContext: Context, workerParams: WorkerParameters):
//    Worker(appContext, workerParams) {
////    private var vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
////    var last_timestamp=0L;
//    override fun doWork(): Result {
//
//    Log.d("HWorker", "Doing the work")
//    Log.d("HWorker", "Code vibrator value is stored.")
//
//    val ve = VibrationEffect.createOneShot(2000, 255)
//    val vibrator: Vibrator
//    val context = this.getApplicationContext()
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//        Log.d("HWorker", "New version")
//
//        val vmanager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
////        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//        vibrator = vmanager.defaultVibrator
//    }
//    else{
//        Log.d("HWorker", "Old version")
//        vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//    }
//    Log.d("Check if has vibrator", "hasVibrator: " + vibrator.hasVibrator().toString())
//    vibrator.vibrate(ve)
//
////        val currentInstant: java.time.Instant = java.time.Instant.now()
////        val currentTimeStamp: Long = currentInstant.toEpochMilli()
////
////        if (last_timestamp > 0){
////            val diff = (last_timestamp - currentTimeStamp) / 60
////            Log.d("HWorker", "************** waited:  " + diff.toString() + " seconds")
////        }
////        else{
////            Log.d("HWorker", currentTimeStamp.toString())
////            Log.d("HWorker", last_timestamp.toString())
////        }
////
////        last_timestamp = currentTimeStamp
//
//        // Indicate whether the work finished successfully with the Result
//        return Result.success()
////        return Result.retry()
//
//    }
//}
