package com.example.nanomedic

//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.nanomedic.ui.theme.NanoMedicTheme

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.nanomedic.ui.theme.NanoMedicTheme

// MainActivity inherits from BaseActivity to utilize its camera permission handling logic
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Collect the camera permission state as a Compose state to automatically update the UI upon change
            val permissionGranted = isCameraPermissionGranted.collectAsState().value

            Box(modifier = Modifier.fillMaxSize()) {
                // Conditional UI rendering based on camera permission state
                if (permissionGranted) {
                    // If permission is granted, display the camera preview
                    CameraScreen()
                } else {
                    // If permission is not granted, display a button to request camera permission
                    Button(
                        onClick = {
                            // Invoke the method from BaseActivity to handle permission request
                            handleCameraPermission()
                        },
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(text = "Start Capture")
                    }
                }
            }
        }
    }
}
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    NanoMedicTheme {
//        Greeting("Android")
//    }
//}