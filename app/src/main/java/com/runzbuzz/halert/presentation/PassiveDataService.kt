//package com.runzbuzz.halert.presentation
//
//import android.util.Log
//import androidx.health.services.client.PassiveListenerService
//import androidx.health.services.client.data.*
//import kotlinx.coroutines.runBlocking
//
//class PassiveDataService : PassiveListenerService() {
//
//    override fun onNewDataPointsReceived(dataPoints: DataPointContainer) {
//            val heart_bpm = dataPoints.getData(DataType.HEART_RATE_BPM).last().value.toString()
//                Log.d("PassiveDataService", "The new received data point: {$heart_bpm}")
//    }
//
//    override fun onHealthEventReceived(event: HealthEvent) {
//        runBlocking {
//            Log.i("PassiveDataService", "event type: ")
//            Log.i("PassiveDataService", event.toString())
//            Log.i("PassiveDataService", event.type.toString())
//        }
//    }
//
//}
