//////package com.example.nanomedic
//////
//////import android.net.Uri
//////import androidx.camera.view.LifecycleCameraController
//////import androidx.camera.view.PreviewView
//////import androidx.compose.foundation.Image
//////import androidx.compose.foundation.layout.Box
//////import androidx.compose.foundation.layout.fillMaxSize
//////import androidx.compose.foundation.layout.width
//////import androidx.compose.material3.Button
//////import androidx.compose.material3.Text
//////import androidx.compose.runtime.Composable
//////import androidx.compose.runtime.mutableStateOf
//////import androidx.compose.runtime.remember
//////import androidx.compose.ui.Alignment
//////import androidx.compose.ui.Modifier
//////import androidx.compose.ui.layout.ContentScale
//////import androidx.compose.ui.platform.LocalContext
//////import androidx.compose.ui.platform.LocalLifecycleOwner
//////import androidx.compose.ui.unit.dp
//////import androidx.compose.ui.viewinterop.AndroidView
//////import coil.compose.rememberAsyncImagePainter
//////import com.example.nanomedic.CameraFileUtils.takePicture
//////import java.util.concurrent.Executors
//////
//////@Composable
//////fun CameraScreen() {
//////    val context = LocalContext.current
//////    val lifecycleOwner = LocalLifecycleOwner.current
//////    // Executor for background tasks, specifically for taking pictures in this context
//////    val executor = remember { Executors.newSingleThreadExecutor() }
//////    // State to hold the URI of the captured image. Initially null, updated after image capture
//////    val capturedImageUri = remember { mutableStateOf<Uri?>(null) }
//////
//////    // Camera controller tied to the lifecycle of this composable
//////    val cameraController = remember {
//////        LifecycleCameraController(context).apply {
//////            bindToLifecycle(lifecycleOwner) // Binds the camera to the lifecycle of the lifecycleOwner
//////        }
//////    }
//////
//////    Box {
//////        // PreviewView for the camera feed. Configured to fill the screen and display the camera output
//////        AndroidView(
//////            modifier = Modifier.fillMaxSize(),
//////            factory = { ctx ->
//////                PreviewView(ctx).apply {
//////                    scaleType = PreviewView.ScaleType.FILL_START
//////                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
//////                    controller = cameraController // Attach the lifecycle-aware camera controller.
//////                }
//////            },
//////            onRelease = {
//////                // Called when the PreviewView is removed from the composable hierarchy
//////                cameraController.unbind() // Unbinds the camera to free up resources
//////            }
//////        )
//////
//////        // Button that triggers the image capture process
//////        Button(
//////            onClick = {
//////                // Calls a utility function to take a picture, handling success and error scenarios
//////                takePicture(cameraController, context, executor, { uri ->
//////                    capturedImageUri.value = uri // Update state with the URI of the captured image on success
//////                }, { exception ->
//////                    // Error handling logic for image capture failures
//////                })
//////            },
//////            modifier = Modifier.align(Alignment.BottomCenter)
//////        ) {
//////            Text(text = "Take Picture")
//////        }
//////
//////        // Displays the captured image if available
//////        capturedImageUri.value?.let { uri ->
//////            Image(
//////                // Asynchronously loads and displays the image from the URI
//////                painter = rememberAsyncImagePainter(uri),
//////                contentDescription = null,
//////                modifier = Modifier
//////                    .width(80.dp)
//////                    .align(Alignment.BottomEnd),
//////                contentScale = ContentScale.Crop
//////            )
//////        }
//////    }
//////}
////
////package com.example.nanomedic
////
////import android.net.Uri
////import androidx.camera.view.LifecycleCameraController
////import androidx.camera.view.PreviewView
////import androidx.compose.foundation.Image
////import androidx.compose.foundation.layout.Box
////import androidx.compose.foundation.layout.fillMaxSize
////import androidx.compose.foundation.layout.width
////import androidx.compose.material3.Button
////import androidx.compose.material3.Text
////import androidx.compose.runtime.Composable
////import androidx.compose.runtime.mutableStateOf
////import androidx.compose.runtime.remember
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.layout.ContentScale
////import androidx.compose.ui.platform.LocalContext
////import androidx.compose.ui.platform.LocalLifecycleOwner
////import androidx.compose.ui.unit.dp
////import androidx.compose.ui.viewinterop.AndroidView
////import coil.compose.rememberAsyncImagePainter
////import com.example.nanomedic.CameraFileUtils.takePicture
////import java.util.concurrent.Executors
////
////@Composable
////fun CameraScreen() {
////    val context = LocalContext.current
////    val lifecycleOwner = LocalLifecycleOwner.current
////    // Executor for background tasks, specifically for taking pictures in this context
////    val executor = remember { Executors.newSingleThreadExecutor() }
////    // State to hold the URI of the captured image. Initially null, updated after image capture
////    val capturedImageUri = remember { mutableStateOf<Uri?>(null) }
////
////    // Camera controller tied to the lifecycle of this composable
////    val cameraController = remember {
////        LifecycleCameraController(context).apply {
////            bindToLifecycle(lifecycleOwner) // Binds the camera to the lifecycle of the lifecycleOwner
////        }
////    }
////
////    Box {
////        // PreviewView for the camera feed. Configured to fill the screen and display the camera output
////        AndroidView(
////            modifier = Modifier.fillMaxSize(),
////            factory = { ctx ->
////                PreviewView(ctx).apply {
////                    // Changed to FILL_CENTER for proper centering
////                    scaleType = PreviewView.ScaleType.FILL_CENTER
////                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
////                    controller = cameraController // Attach the lifecycle-aware camera controller.
////                }
////            },
////            onRelease = {
////                // Called when the PreviewView is removed from the composable hierarchy
////                cameraController.unbind() // Unbinds the camera to free up resources
////            }
////        )
////
////        // Button that triggers the image capture process
////        Button(
////            onClick = {
////                // Calls a utility function to take a picture, handling success and error scenarios
////                takePicture(cameraController, context, executor, { uri ->
////                    capturedImageUri.value = uri // Update state with the URI of the captured image on success
////                }, { exception ->
////                    // Error handling logic for image capture failures
////                })
////            },
////            modifier = Modifier.align(Alignment.BottomCenter)
////        ) {
////            Text(text = "Take Picture")
////        }
////
////        // Displays the captured image if available
////        capturedImageUri.value?.let { uri ->
////            Image(
////                // Asynchronously loads and displays the image from the URI
////                painter = rememberAsyncImagePainter(uri),
////                contentDescription = null,
////                modifier = Modifier
////                    .width(80.dp)
////                    .align(Alignment.BottomEnd),
////                contentScale = ContentScale.Crop
////            )
////        }
////    }
////}
//
//package com.example.nanomedic
//
//import android.net.Uri
//import androidx.camera.view.LifecycleCameraController
//import androidx.camera.view.PreviewView
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.width
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.viewinterop.AndroidView
//import coil.compose.rememberAsyncImagePainter
//import com.example.nanomedic.CameraFileUtils.takePicture
//import java.util.concurrent.Executors
//import android.util.Log
//
//@Composable
//fun CameraScreen() {
//    val context = LocalContext.current
//
//    val lifecycleOwner = LocalLifecycleOwner.current
//    // Executor for background tasks, specifically for taking pictures in this context
//    val executor = remember { Executors.newSingleThreadExecutor() }
//    // State to hold the URI of the captured image. Initially null, updated after image capture
//    val capturedImageUri = remember { mutableStateOf<Uri?>(null) }
//
//    // Camera controller tied to the lifecycle of this composable
//    val cameraController = remember {
//        LifecycleCameraController(context).apply {
//            bindToLifecycle(lifecycleOwner) // Binds the camera to the lifecycle of the lifecycleOwner
//        }
//
//    }
//
//
//    Box {
//        // PreviewView for the camera feed. Configured to fill the screen and display the camera output
//        AndroidView(
//            modifier = Modifier.fillMaxSize(),
//            factory = { ctx ->
//                PreviewView(ctx).apply {
//                    // Changed to FILL_CENTER for proper centering
////                    scaleType = PreviewView.ScaleType.FILL_CENTER
//                    scaleType = PreviewView.ScaleType.FILL_END
////                    scaleType = PreviewView.ScaleType.FIT_START
//
//
//                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
//                    controller = cameraController // Attach the lifecycle-aware camera controller.
//                }
//            },
//            onRelease = {
//                // Called when the PreviewView is removed from the composable hierarchy
//                cameraController.unbind() // Unbinds the camera to free up resources
//            }
//        )
//
//        // Button that triggers the image capture process
//        Button(
//            onClick = {
//                // Calls a utility function to take a picture, handling success and error scenarios
//                takePicture(cameraController, context, executor, { uri ->
//                    capturedImageUri.value = uri // Update state with the URI of the captured image on success
//                }, { exception ->
//                    // Error handling logic for image capture failures
//                })
//            },
//            modifier = Modifier.align(Alignment.BottomCenter)
//        ) {
//            Text(text = "Take Picture")
//        }
//
//        // Displays the captured image if available
//        capturedImageUri.value?.let { uri ->
//            Image(
//                // Asynchronously loads and displays the image from the URI
//                painter = rememberAsyncImagePainter(uri),
//                contentDescription = null,
//                modifier = Modifier
//                    .width(80.dp)
//                    .align(Alignment.BottomEnd),
//                contentScale = ContentScale.Crop
//            )
//        }
//    }
//}

//package com.example.nanomedic
//
//import android.net.Uri
//import androidx.camera.core.CameraSelector
//import androidx.camera.view.LifecycleCameraController
//import androidx.camera.view.PreviewView
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.viewinterop.AndroidView
//import coil.compose.rememberAsyncImagePainter
//import com.example.nanomedic.CameraFileUtils.takePicture
//import java.util.concurrent.Executors
//
//@Composable
//fun CameraScreen() {
//    val context = LocalContext.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val executor = remember { Executors.newSingleThreadExecutor() }
//    val capturedImageUri = remember { mutableStateOf<Uri?>(null) }
//
//    // Manual adjustment controls
//    var xOffset by remember { mutableFloatStateOf(0f) }        // Left/Right movement
//    var yOffset by remember { mutableFloatStateOf(0f) }        // Up/Down movement
//    var scaleX by remember { mutableFloatStateOf(1f) }         // Horizontal scaling
//    var scaleY by remember { mutableFloatStateOf(1f) }         // Vertical scaling
//    var rotation by remember { mutableFloatStateOf(0f) }       // Rotation
//    var showControls by remember { mutableStateOf(true) }      // Toggle controls visibility
//
//    // Camera selection
//    var currentCamera by remember { mutableStateOf(CameraSelector.DEFAULT_FRONT_CAMERA) }
//
//    val cameraController = remember {
//        LifecycleCameraController(context).apply {
//            cameraSelector = currentCamera
//            bindToLifecycle(lifecycleOwner)
//        }
//    }
//
//    LaunchedEffect(currentCamera) {
//        cameraController.cameraSelector = currentCamera
//    }
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        // Camera preview with manual transformations
//        AndroidView(
//            modifier = Modifier
//                .fillMaxSize()
//                .offset(x = xOffset.dp, y = yOffset.dp)  // Move the entire preview
//                .graphicsLayer(
//                    scaleX = scaleX,           // Stretch/compress horizontally
//                    scaleY = scaleY,           // Stretch/compress vertically
//                    rotationZ = rotation       // Rotate the preview
//                ),
//            factory = { ctx ->
//                PreviewView(ctx).apply {
//                    scaleType = PreviewView.ScaleType.FILL_CENTER
//                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
//                    controller = cameraController
//                }
//            },
//            onRelease = {
//                cameraController.unbind()
//            }
//        )
//
//        // Center guide lines (to help with alignment)
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            // Vertical center line
//            Box(
//                modifier = Modifier
//                    .width(2.dp)
//                    .fillMaxHeight()
//                    .background(Color.Red.copy(alpha = 0.5f))
//                    .align(Alignment.Center)
//            )
//            // Horizontal center line
//            Box(
//                modifier = Modifier
//                    .height(2.dp)
//                    .fillMaxWidth()
//                    .background(Color.Red.copy(alpha = 0.5f))
//                    .align(Alignment.Center)
//            )
//        }
//
//        // Control panel (collapsible)
//        if (showControls) {
//            Card(
//                modifier = Modifier
//                    .align(Alignment.TopStart)
//                    .padding(8.dp)
//                    .widthIn(max = 300.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color.Black.copy(alpha = 0.8f)
//                )
//            ) {
//                Column(
//                    modifier = Modifier.padding(12.dp)
//                ) {
//                    Text(
//                        "Camera Alignment",
//                        color = Color.White,
//                        fontSize = 14.sp
//                    )
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    // Camera selector
//                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                        Button(
//                            onClick = { currentCamera = CameraSelector.DEFAULT_FRONT_CAMERA },
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = if (currentCamera == CameraSelector.DEFAULT_FRONT_CAMERA)
//                                    MaterialTheme.colorScheme.primary else Color.Gray
//                            )
//                        ) {
//                            Text("Front", fontSize = 12.sp)
//                        }
//                        Button(
//                            onClick = { currentCamera = CameraSelector.DEFAULT_BACK_CAMERA },
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = if (currentCamera == CameraSelector.DEFAULT_BACK_CAMERA)
//                                    MaterialTheme.colorScheme.primary else Color.Gray
//                            )
//                        ) {
//                            Text("Back", fontSize = 12.sp)
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    // X Offset (Left/Right)
//                    Text("Left/Right: ${xOffset.toInt()}dp", color = Color.White, fontSize = 12.sp)
//                    Slider(
//                        value = xOffset,
//                        onValueChange = { xOffset = it },
//                        valueRange = -200f..200f,
//                        colors = SliderDefaults.colors(thumbColor = Color.White)
//                    )
//
//                    // Y Offset (Up/Down)
//                    Text("Up/Down: ${yOffset.toInt()}dp", color = Color.White, fontSize = 12.sp)
//                    Slider(
//                        value = yOffset,
//                        onValueChange = { yOffset = it },
//                        valueRange = -200f..200f,
//                        colors = SliderDefaults.colors(thumbColor = Color.White)
//                    )
//
//                    // Scale X (Width)
//                    Text("Width: ${String.format("%.2f", scaleX)}", color = Color.White, fontSize = 12.sp)
//                    Slider(
//                        value = scaleX,
//                        onValueChange = { scaleX = it },
//                        valueRange = 0.5f..3f,
//                        colors = SliderDefaults.colors(thumbColor = Color.White)
//                    )
//
//                    // Scale Y (Height)
//                    Text("Height: ${String.format("%.2f", scaleY)}", color = Color.White, fontSize = 12.sp)
//                    Slider(
//                        value = scaleY,
//                        onValueChange = { yOffset = it },
//                        valueRange = 0.5f..3f,
//                        colors = SliderDefaults.colors(thumbColor = Color.White)
//                    )
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    // Quick actions
//                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                        Button(
//                            onClick = {
//                                xOffset = 0f
//                                yOffset = 0f
//                                scaleX = 1f
//                                scaleY = 1f
//                                rotation = 0f
//                            }
//                        ) {
//                            Text("Reset", fontSize = 12.sp)
//                        }
//
//                        Button(
//                            onClick = {
//                                // Common fix for laptop webcams
//                                xOffset = -30f
//                                scaleX = 1.2f
//                                scaleY = 1.2f
//                            }
//                        ) {
//                            Text("Auto-Fix", fontSize = 12.sp)
//                        }
//                    }
//
//                    // Show current values
//                    Text(
//                        "x=${xOffset.toInt()}, y=${yOffset.toInt()}, w=${String.format("%.1f", scaleX)}, h=${String.format("%.1f", scaleY)}",
//                        color = Color.Yellow,
//                        fontSize = 10.sp
//                    )
//                }
//            }
//        }
//
//        // Toggle controls button
//        Button(
//            onClick = { showControls = !showControls },
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .padding(8.dp)
//        ) {
//            Text(if (showControls) "Hide" else "Show")
//        }
//
//        // Take picture button
//        Button(
//            onClick = {
//                takePicture(cameraController, context, executor, { uri ->
//                    capturedImageUri.value = uri
//                }, { exception ->
//                    // Error handling
//                })
//            },
//            modifier = Modifier.align(Alignment.BottomCenter)
//        ) {
//            Text("Take Picture")
//        }
//
//        // Display captured image
//        capturedImageUri.value?.let { uri ->
//            Image(
//                painter = rememberAsyncImagePainter(uri),
//                contentDescription = null,
//                modifier = Modifier
//                    .width(80.dp)
//                    .align(Alignment.BottomEnd)
//                    .border(2.dp, Color.White)
//                    .clip(RoundedCornerShape(8.dp)),
//                contentScale = ContentScale.Crop
//            )
//        }
//    }
//}

//package com.example.nanomedic
//
//import android.net.Uri
//import androidx.camera.core.CameraSelector
//import androidx.camera.view.LifecycleCameraController
//import androidx.camera.view.PreviewView
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.viewinterop.AndroidView
//import coil.compose.rememberAsyncImagePainter
//import com.example.nanomedic.CameraFileUtils.takePicture
//import java.util.concurrent.Executors
//
//@Composable
//fun CameraScreen() {
//    val context = LocalContext.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val executor = remember { Executors.newSingleThreadExecutor() }
//    val capturedImageUri = remember { mutableStateOf<Uri?>(null) }
//
//    // Manual adjustment controls
//    var xOffset by remember { mutableFloatStateOf(0f) }        // Left/Right movement
//    var yOffset by remember { mutableFloatStateOf(0f) }        // Up/Down movement
//    var scaleX by remember { mutableFloatStateOf(1f) }         // Horizontal scaling
//    var scaleY by remember { mutableFloatStateOf(1f) }         // Vertical scaling
//    var rotation by remember { mutableFloatStateOf(0f) }       // Rotation
//    var showControls by remember { mutableStateOf(true) }      // Toggle controls visibility
//
//    // Camera selection
//    var currentCamera by remember { mutableStateOf(CameraSelector.DEFAULT_FRONT_CAMERA) }
//
//    val cameraController = remember {
//        LifecycleCameraController(context).apply {
//            cameraSelector = currentCamera
//            bindToLifecycle(lifecycleOwner)
//        }
//    }
//
//    LaunchedEffect(currentCamera) {
//        cameraController.cameraSelector = currentCamera
//    }
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        // Camera preview with manual transformations
//        AndroidView(
//            modifier = Modifier
//                .fillMaxSize()
//                .offset(x = xOffset.dp, y = yOffset.dp)  // Move the entire preview
//                .graphicsLayer(
//                    scaleX = scaleX,           // Stretch/compress horizontally
//                    scaleY = scaleY,           // Stretch/compress vertically
//                    rotationZ = rotation       // Rotate the preview
//                ),
//            factory = { ctx ->
//                PreviewView(ctx).apply {
//                    scaleType = PreviewView.ScaleType.FILL_CENTER
//                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
//                    controller = cameraController
//                }
//            },
//            onRelease = {
//                cameraController.unbind()
//            }
//        )
//
//        // Center guide lines (to help with alignment)
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            // Vertical center line
//            Box(
//                modifier = Modifier
//                    .width(2.dp)
//                    .fillMaxHeight()
//                    .background(Color.Red.copy(alpha = 0.5f))
//                    .align(Alignment.Center)
//            )
//            // Horizontal center line
//            Box(
//                modifier = Modifier
//                    .height(2.dp)
//                    .fillMaxWidth()
//                    .background(Color.Red.copy(alpha = 0.5f))
//                    .align(Alignment.Center)
//            )
//        }
//
//        // Control panel (collapsible)
//        if (showControls) {
//            Card(
//                modifier = Modifier
//                    .align(Alignment.TopStart)
//                    .padding(8.dp)
//                    .widthIn(max = 300.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color.Black.copy(alpha = 0.8f)
//                )
//            ) {
//                Column(
//                    modifier = Modifier.padding(12.dp)
//                ) {
//                    Text(
//                        "Camera Alignment",
//                        color = Color.White,
//                        fontSize = 14.sp
//                    )
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    // Camera selector
//                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                        Button(
//                            onClick = { currentCamera = CameraSelector.DEFAULT_FRONT_CAMERA },
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = if (currentCamera == CameraSelector.DEFAULT_FRONT_CAMERA)
//                                    MaterialTheme.colorScheme.primary else Color.Gray
//                            )
//                        ) {
//                            Text("Front", fontSize = 12.sp)
//                        }
//                        Button(
//                            onClick = { currentCamera = CameraSelector.DEFAULT_BACK_CAMERA },
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = if (currentCamera == CameraSelector.DEFAULT_BACK_CAMERA)
//                                    MaterialTheme.colorScheme.primary else Color.Gray
//                            )
//                        ) {
//                            Text("Back", fontSize = 12.sp)
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    // X Offset (Left/Right) - More precise range
//                    Text("Left/Right: ${String.format("%.1f", xOffset)}dp", color = Color.White, fontSize = 12.sp)
//                    Slider(
//                        value = xOffset,
//                        onValueChange = { xOffset = it },
//                        valueRange = -100f..100f,  // Smaller range for more precision
//                        steps = 200,  // More steps for fine control
//                        colors = SliderDefaults.colors(thumbColor = Color.White)
//                    )
//
//                    // Fine adjustment buttons for horizontal
//                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
//                        Button(
//                            onClick = { xOffset -= 5f },
//                            modifier = Modifier.size(40.dp, 30.dp)
//                        ) {
//                            Text("<<", fontSize = 10.sp)
//                        }
//                        Button(
//                            onClick = { xOffset -= 1f },
//                            modifier = Modifier.size(40.dp, 30.dp)
//                        ) {
//                            Text("<", fontSize = 10.sp)
//                        }
//                        Button(
//                            onClick = { xOffset += 1f },
//                            modifier = Modifier.size(40.dp, 30.dp)
//                        ) {
//                            Text(">", fontSize = 10.sp)
//                        }
//                        Button(
//                            onClick = { xOffset += 5f },
//                            modifier = Modifier.size(40.dp, 30.dp)
//                        ) {
//                            Text(">>", fontSize = 10.sp)
//                        }
//                    }
//
//                    // Y Offset (Up/Down)
//                    Text("Up/Down: ${yOffset.toInt()}dp", color = Color.White, fontSize = 12.sp)
//                    Slider(
//                        value = yOffset,
//                        onValueChange = { yOffset = it },
//                        valueRange = -200f..200f,
//                        colors = SliderDefaults.colors(thumbColor = Color.White)
//                    )
//
//                    // Scale X (Width)
//                    Text("Width: ${String.format("%.2f", scaleX)}", color = Color.White, fontSize = 12.sp)
//                    Slider(
//                        value = scaleX,
//                        onValueChange = { scaleX = it },
//                        valueRange = 0.5f..3f,
//                        colors = SliderDefaults.colors(thumbColor = Color.White)
//                    )
//
//                    // Scale Y (Height)
//                    Text("Height: ${String.format("%.2f", scaleY)}", color = Color.White, fontSize = 12.sp)
//                    Slider(
//                        value = scaleY,
//                        onValueChange = { yOffset = it },
//                        valueRange = 0.5f..3f,
//                        colors = SliderDefaults.colors(thumbColor = Color.White)
//                    )
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    // Quick actions
//                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                        Button(
//                            onClick = {
//                                xOffset = 0f
//                                yOffset = 0f
//                                scaleX = 1f
//                                scaleY = 1f
//                                rotation = 0f
//                            }
//                        ) {
//                            Text("Reset", fontSize = 12.sp)
//                        }
//
//                        Button(
//                            onClick = {
//                                // Better auto-fix for your specific case
//                                xOffset = -50f  // Start with more left adjustment
//                                yOffset = 0f
//                                scaleX = 1.1f
//                                scaleY = 1.1f
//                            }
//                        ) {
//                            Text("Auto-Fix", fontSize = 12.sp)
//                        }
//                    }
//
//                    // Show current values
//                    Text(
//                        "x=${xOffset.toInt()}, y=${yOffset.toInt()}, w=${String.format("%.1f", scaleX)}, h=${String.format("%.1f", scaleY)}",
//                        color = Color.Yellow,
//                        fontSize = 10.sp
//                    )
//                }
//            }
//        }
//
//        // Toggle controls button
//        Button(
//            onClick = { showControls = !showControls },
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .padding(8.dp)
//        ) {
//            Text(if (showControls) "Hide" else "Show")
//        }
//
//        // Take picture button
//        Button(
//            onClick = {
//                takePicture(cameraController, context, executor, { uri ->
//                    capturedImageUri.value = uri
//                }, { exception ->
//                    // Error handling
//                })
//            },
//            modifier = Modifier.align(Alignment.BottomCenter)
//        ) {
//            Text("Take Picture")
//        }
//
//        // Display captured image
//        capturedImageUri.value?.let { uri ->
//            Image(
//                painter = rememberAsyncImagePainter(uri),
//                contentDescription = null,
//                modifier = Modifier
//                    .width(80.dp)
//                    .align(Alignment.BottomEnd)
//                    .border(2.dp, Color.White)
//                    .clip(RoundedCornerShape(8.dp)),
//                contentScale = ContentScale.Crop
//            )
//        }
//    }
//}

//package com.example.nanomedic
//
//import android.net.Uri
//import androidx.camera.core.CameraSelector
//import androidx.camera.view.LifecycleCameraController
//import androidx.camera.view.PreviewView
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clipToBounds
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.viewinterop.AndroidView
//import coil.compose.rememberAsyncImagePainter
//import com.example.nanomedic.CameraFileUtils.takePicture
//import java.util.concurrent.Executors
//
//@Composable
//fun CameraScreen() {
//    val context = LocalContext.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val executor = remember { Executors.newSingleThreadExecutor() }
//    val capturedImageUri = remember { mutableStateOf<Uri?>(null) }
//
//    // Camera viewport controls
//    var cameraWidth by remember { mutableFloatStateOf(1.0f) }
//    var cameraHeight by remember { mutableFloatStateOf(1.0f) }
//    var cameraOffsetX by remember { mutableFloatStateOf(0f) }
//    var cameraOffsetY by remember { mutableFloatStateOf(0f) }
//    var showControls by remember { mutableStateOf(true) }
//
//    // Camera selection
//    var currentCamera by remember { mutableStateOf(CameraSelector.DEFAULT_FRONT_CAMERA) }
//
//    val cameraController = remember {
//        LifecycleCameraController(context).apply {
//            cameraSelector = currentCamera
//            bindToLifecycle(lifecycleOwner)
//        }
//    }
//
//    LaunchedEffect(currentCamera) {
//        cameraController.cameraSelector = currentCamera
//    }
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        // Camera container with clipping - this crops the actual camera view
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .clipToBounds()  // This ensures the camera is actually cropped
//        ) {
//            AndroidView(
//                modifier = Modifier
//                    .fillMaxWidth(cameraWidth)
//                    .fillMaxHeight(cameraHeight)
//                    .offset(x = cameraOffsetX.dp, y = cameraOffsetY.dp),
//                factory = { ctx ->
//                    PreviewView(ctx).apply {
//                        scaleType = PreviewView.ScaleType.FILL_CENTER
//                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
//                        controller = cameraController
//                    }
//                },
//                onRelease = {
//                    cameraController.unbind()
//                }
//            )
//        }
//
//        // Center guide lines
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            // Vertical center line
//            Box(
//                modifier = Modifier
//                    .width(2.dp)
//                    .fillMaxHeight()
//                    .background(Color.Red.copy(alpha = 0.7f))
//                    .align(Alignment.Center)
//            )
//            // Horizontal center line
//            Box(
//                modifier = Modifier
//                    .height(2.dp)
//                    .fillMaxWidth()
//                    .background(Color.Red.copy(alpha = 0.7f))
//                    .align(Alignment.Center)
//            )
//        }
//
//        // Control panel
//        if (showControls) {
//            Card(
//                modifier = Modifier
//                    .align(Alignment.TopStart)
//                    .padding(8.dp)
//                    .widthIn(max = 320.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color.Black.copy(alpha = 0.85f)
//                )
//            ) {
//                Column(
//                    modifier = Modifier.padding(12.dp)
//                ) {
//                    Text(
//                        "Camera Viewport Control",
//                        color = Color.White,
//                        fontSize = 14.sp
//                    )
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    // Camera selector
//                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                        Button(
//                            onClick = { currentCamera = CameraSelector.DEFAULT_FRONT_CAMERA },
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = if (currentCamera == CameraSelector.DEFAULT_FRONT_CAMERA)
//                                    MaterialTheme.colorScheme.primary else Color.Gray
//                            )
//                        ) {
//                            Text("Front", fontSize = 12.sp)
//                        }
//                        Button(
//                            onClick = { currentCamera = CameraSelector.DEFAULT_BACK_CAMERA },
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = if (currentCamera == CameraSelector.DEFAULT_BACK_CAMERA)
//                                    MaterialTheme.colorScheme.primary else Color.Gray
//                            )
//                        ) {
//                            Text("Back", fontSize = 12.sp)
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    // Horizontal Position
//                    Text("Horizontal Shift: ${cameraOffsetX.toInt()}dp", color = Color.White, fontSize = 12.sp)
//                    Slider(
//                        value = cameraOffsetX,
//                        onValueChange = { cameraOffsetX = it },
//                        valueRange = -200f..200f,
//                        colors = SliderDefaults.colors(thumbColor = Color.White)
//                    )
//
//                    // Vertical Position
//                    Text("Vertical Shift: ${cameraOffsetY.toInt()}dp", color = Color.White, fontSize = 12.sp)
//                    Slider(
//                        value = cameraOffsetY,
//                        onValueChange = { cameraOffsetY = it },
//                        valueRange = -200f..200f,
//                        colors = SliderDefaults.colors(thumbColor = Color.White)
//                    )
//
//                    // Camera Width (Zoom/Crop horizontally)
//                    Text("Width Zoom: ${String.format("%.2f", cameraWidth)}", color = Color.White, fontSize = 12.sp)
//                    Slider(
//                        value = cameraWidth,
//                        onValueChange = { cameraWidth = it },
//                        valueRange = 0.5f..2.0f,
//                        colors = SliderDefaults.colors(thumbColor = Color.White)
//                    )
//
//                    // Camera Height (Zoom/Crop vertically)
//                    Text("Height Zoom: ${String.format("%.2f", cameraHeight)}", color = Color.White, fontSize = 12.sp)
//                    Slider(
//                        value = cameraHeight,
//                        onValueChange = { cameraHeight = it },
//                        valueRange = 0.5f..2.0f,
//                        colors = SliderDefaults.colors(thumbColor = Color.White)
//                    )
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    // Preset buttons
//                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                        Button(
//                            onClick = {
//                                cameraOffsetX = 0f
//                                cameraOffsetY = 0f
//                                cameraWidth = 1.0f
//                                cameraHeight = 1.0f
//                            }
//                        ) {
//                            Text("Reset", fontSize = 12.sp)
//                        }
//
//                        Button(
//                            onClick = {
//                                // Center-crop approach
//                                cameraOffsetX = -100f
//                                cameraOffsetY = 0f
//                                cameraWidth = 1.5f
//                                cameraHeight = 1.2f
//                            }
//                        ) {
//                            Text("Center-Crop", fontSize = 12.sp)
//                        }
//
//                        Button(
//                            onClick = {
//                                // Webcam fix approach
//                                cameraOffsetX = -50f
//                                cameraOffsetY = 0f
//                                cameraWidth = 1.3f
//                                cameraHeight = 1.0f
//                            }
//                        ) {
//                            Text("Webcam-Fix", fontSize = 12.sp)
//                        }
//                    }
//
//                    // Show current values
//                    Text(
//                        "Values: x=${cameraOffsetX.toInt()}, y=${cameraOffsetY.toInt()}, w=${String.format("%.2f", cameraWidth)}, h=${String.format("%.2f", cameraHeight)}",
//                        color = Color.Yellow,
//                        fontSize = 10.sp
//                    )
//                }
//            }
//        }
//
//        // Toggle controls button
//        Button(
//            onClick = { showControls = !showControls },
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .padding(8.dp)
//        ) {
//            Text(if (showControls) "Hide" else "Show")
//        }
//
//        // Take picture button
//        Button(
//            onClick = {
//                takePicture(cameraController, context, executor, { uri ->
//                    capturedImageUri.value = uri
//                }, { exception ->
//                    // Error handling
//                })
//            },
//            modifier = Modifier.align(Alignment.BottomCenter)
//        ) {
//            Text("Take Picture")
//        }
//
//        // Display captured image
//        capturedImageUri.value?.let { uri ->
//            Image(
//                painter = rememberAsyncImagePainter(uri),
//                contentDescription = null,
//                modifier = Modifier
//                    .width(80.dp)
//                    .align(Alignment.BottomEnd)
//                    .background(Color.White, RoundedCornerShape(8.dp))
//                    .padding(2.dp),
//                contentScale = ContentScale.Crop
//            )
//        }
//    }
//}

package com.example.nanomedic

import android.net.Uri
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import com.example.nanomedic.CameraFileUtils.takePicture
import java.util.concurrent.Executors

@Composable
fun CameraScreen() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    // Executor for background tasks, specifically for taking pictures in this context
    val executor = remember { Executors.newSingleThreadExecutor() }
    // State to hold the URI of the captured image. Initially null, updated after image capture
    val capturedImageUri = remember { mutableStateOf<Uri?>(null) }

    // Camera controller tied to the lifecycle of this composable
    val cameraController = remember {
        LifecycleCameraController(context).apply {
            bindToLifecycle(lifecycleOwner) // Binds the camera to the lifecycle of the lifecycleOwner
        }
    }

    Box {
        // PreviewView for the camera feed. Configured to fill the screen and display the camera output
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                PreviewView(ctx).apply {
                    scaleType = PreviewView.ScaleType.FILL_START
                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    controller = cameraController // Attach the lifecycle-aware camera controller.
                }
            },
            onRelease = {
                // Called when the PreviewView is removed from the composable hierarchy
                cameraController.unbind() // Unbinds the camera to free up resources
            }
        )

        // Button that triggers the image capture process
        Button(
            onClick = {
                // Calls a utility function to take a picture, handling success and error scenarios
                takePicture(cameraController, context, executor, { uri ->
                    capturedImageUri.value = uri // Update state with the URI of the captured image on success
                }, { exception ->
                    // Error handling logic for image capture failures
                })
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "Take Picture")
        }

        // Displays the captured image if available
        capturedImageUri.value?.let { uri ->
            Image(
                // Asynchronously loads and displays the image from the URI
                painter = rememberAsyncImagePainter(uri),
                contentDescription = null,
                modifier = Modifier
                    .width(80.dp)
                    .align(Alignment.BottomEnd),
                contentScale = ContentScale.Crop
            )
        }
    }
}