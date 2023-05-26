//package com.runzbuzz.halert.presentation
//
//class MainActivity3 : ComponentActivity() {
//
//
//// Request the permission at runtime if needed
//private val REQUEST_CODE_BODY_SENSORS = 1
//private fun checkBodySensorsPermission() {
//    if (ContextCompat.checkSelfPermission(this, Manifest.permission.BODY_SENSORS)
//        != PackageManager.PERMISSION_GRANTED) {
//// Permission is not granted
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                Manifest.permission.BODY_SENSORS)) {
//// Show an explanation to the user
//        } else {
//// No explanation needed, request the permission
//            ActivityCompat.requestPermissions(this,
//                arrayOf(Manifest.permission.BODY_SENSORS),
//                REQUEST_CODE_BODY_SENSORS)
//        }
//    } else {
//// Permission has already been granted
//    }
//}
//
//// Handle the permission result
//override fun onRequestPermissionsResult(requestCode: Int,
//                                        permissions: Array<String>, grantResults: IntArray) {
//    when (requestCode) {
//        REQUEST_CODE_BODY_SENSORS -> {
//// If request is cancelled, the result arrays are empty.
//            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//// Permission was granted, yay!
//            } else {
//// Permission denied, boo!
//            }
//            return
//        }
//    }
//}
//
//// Get the health services client and register a data listener
//private lateinit var healthServicesClient: HealthServicesClient
//private lateinit var dataListener: DataListener
//
//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//
//// Check and request the body sensors permission
//    checkBodySensorsPermission()
//
//// Get the health services client
//    healthServicesClient = HealthServices.getClient(this)
//
//// Create a data listener that updates a mutable state with the heart rate value
//    val heartRateState = mutableStateOf(0f)
//    dataListener = object : DataListener {
//        override fun onNewData(data: Data) {
//            if (data.dataType == DataType.HEART_RATE_BPM) {
//                heartRateState.value = data.value.asFloat()
//            }
//        }
//    }
//
//// Register the data listener for heart rate data with a sampling period of 60 seconds
//    healthServicesClient.dataClient.registerDataListener(
//        DataType.HEART_RATE_BPM,
//        SamplingRate.PERIODIC(60_000_000),
//        dataListener)
//
//// Set the content view using Jetpack Compose
//    setContent {
//        HeartRateScreen(heartRateState.value)
//    }
//}
//
//// Unregister the data listener in onDestroy()
//override fun onDestroy() {
//    super.onDestroy()
//    healthServicesClient.dataClient.unregisterDataListener(dataListener)
//}
//
//// Define a composable function that displays the heart rate value
//@Composable
//fun HeartRateScreen(heartRate: Float) {
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Text(text = "Heart rate: $heartRate bpm", fontSize = 24.sp)
//    }
//}
//
//}
