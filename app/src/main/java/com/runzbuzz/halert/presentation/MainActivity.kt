/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.runzbuzz.halert.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.health.services.client.HealthServices
import androidx.health.services.client.MeasureCallback
import androidx.health.services.client.data.DataType
import androidx.health.services.client.MeasureClient
import androidx.health.services.client.data.Availability
import androidx.health.services.client.data.DataPointContainer
import androidx.health.services.client.data.DataTypeAvailability
import androidx.health.services.client.data.DeltaDataType
import androidx.health.services.client.data.PassiveListenerConfig
import androidx.lifecycle.lifecycleScope
import androidx.wear.ambient.AmbientModeSupport

import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.runzbuzz.halert.R
import com.runzbuzz.halert.presentation.theme.HalertTheme
import kotlinx.coroutines.launch

class MainActivity : FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider {

//class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//            val healthClient = HealthServices.getClient(this /*context*/)
//            val measureClient = healthClient.measureClient
//            lifecycleScope.launch {
//                val capabilities = measureClient.getCapabilitiesAsync().await()
//                val supportsHeartRate =
//                    DataType.HEART_RATE_BPM in capabilities.supportedDataTypesMeasure
//            }

//            val heartRateCallback = object : MeasureCallback {
//                override fun onAvailabilityChanged(dataType: DeltaDataType<*, *>, availability: Availability) {
//                    if (availability is DataTypeAvailability) {
//                        Log.d("Health", "Availability is changed")
//                        Log.d("Health", availability.toString())
//
//                        // Handle availability change.
//                    }
//                }
//
//                override fun onDataReceived(data: DataPointContainer) {
//                    // Inspect data points.
//                    Log.d("Health", "Data is received")
//                    Log.d("Health", data.toString())
//                    Log.d("Health", data.getData(DataType.HEART_RATE_BPM).last().toString())
//                    Log.d("Health", data.getData(DataType.HEART_RATE_BPM).last().dataType.toString())
//                    Log.d("Health", data.getData(DataType.HEART_RATE_BPM).last().value.toString())
//                }
//
//                override fun onRegistered() {
//                    super.onRegistered()
//                    Log.d("Health", "Registered")
//                }
//
//                override fun onRegistrationFailed(throwable: Throwable) {
//                    super.onRegistrationFailed(throwable)
//                    Log.d("Health", "Registration failed")
//                }
//            }

            val heartRateCallback = HeartMeasureCallback()
            val healthClient = HealthServices.getClient(this /*context*/)
            val measureClient = healthClient.measureClient
            measureClient.registerMeasureCallback(DataType.Companion.HEART_RATE_BPM, heartRateCallback)


//            AmbientModeSupport.attach(this)



//            val passiveListenerConfig = PassiveListenerConfig.builder()
//                .setDataTypes(setOf(DataType.HEART_RATE_BPM))
//                .build()
//            val healthClient = HealthServices.getClient(this /*context*/)
//            val passiveMonitoringClient = healthClient.passiveMonitoringClient
//            passiveMonitoringClient.setPassiveListenerServiceAsync()

            WearApp("Android")
        }
    }

//    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback {
//        TODO("Not yet implemented")
//    }
    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback = MyAmbientCallback()

}

@Composable
fun WearApp(greetingName: String) {
    HalertTheme {
        /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
         * version of LazyColumn for wear devices with some added features. For more information,
         * see d.android.com/wear/compose.
         */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center
        ) {
            //Greeting(greetingName = greetingName)
            HeartCol(modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                )
        }
    }
}

@Composable
fun HeartCol(modifier: Modifier){
    Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.Center){
        var heart_rate by remember { mutableStateOf(0) }
        Text("The Heart Rate is ${heart_rate} ")
        Button(onClick = { heart_rate++}, modifier=Modifier.padding(top = 8.dp).height(height=40.dp).width(width=150.dp)){
            Text("Read Heart Rate")
        }
        Button(onClick = {

                         }, modifier=Modifier.padding(top = 8.dp).height(height=40.dp).width(width=150.dp)){
            Text("Access Health ")
        }
    }

}





@Composable
fun Greeting(greetingName: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = stringResource(R.string.hello_world, greetingName)
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}