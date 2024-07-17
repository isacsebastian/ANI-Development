package com.example.tesis1.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tesis1.ApiService
import com.example.tesis1.components.CircularCard
import com.example.tesis1.components.SquareCard
import com.example.tesis1.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import kotlin.math.min

@Composable
fun MeetingScreen(navController: NavHostController, meetingTitle: String, meetingDescription: String) {
    val coroutineScope = rememberCoroutineScope()
    val elapsedTime = remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    var mediaRecorder by remember { mutableStateOf<MediaRecorder?>(null) }
    var audioFile by remember { mutableStateOf<File?>(null) }
    var transcription by remember { mutableStateOf("") }
    var isRecording by remember { mutableStateOf(false) }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            startRecording(context) { recorder, file ->
                mediaRecorder = recorder
                audioFile = file
                isRecording = true
                Handler(Looper.getMainLooper()).postDelayed({
                    stopRecording(mediaRecorder)
                    isRecording = false
                    audioFile?.let { file ->
                        uploadAudio(file) { transcriptionText ->
                            transcription = transcriptionText
                        }
                    }
                }, 5000)
            }
        } else {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            while (true) {
                delay(1000)
                elapsedTime.intValue++
            }
        }
    }

    val minutes = elapsedTime.intValue / 60
    val seconds = elapsedTime.intValue % 60
    val participants = listOf("Participant 1", "Participant 2", "Participant 3", "Participant 4", "Participant 5",
        "Participant 6", "Participant 7", "Participant 8", "Participant 9", "Participant 10", "Participant 11",
        "Participant 12", "Participant 13", "Participant 14", "Participant 15", "Participant 16", "Participant 17",
        "Participant 18", "Participant 19", "Participant 20")

    Surface(
        color = surfaceDimLight,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 96.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                BackArrow(
                    modifier = Modifier.padding(bottom = 16.dp),
                    onClick = { navController.navigateUp() }
                )
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "$minutes min $seconds sec",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = surfaceContainerDark
                )
                Spacer(modifier = Modifier.weight(1f))

                ThreeDotsIcon(
                    modifier = Modifier.padding(bottom = 16.dp),
                    onClick = { /* Handle three dots icon click */ }
                )
            }
            Text(
                text = meetingTitle,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp),
                color = surfaceContainerDark
            )
            Text(
                text = meetingDescription,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp),
                color = surfaceContainerDark
            )

            val rows = (participants.size + 1) / 3 + 1

            repeat(rows) { rowIndex ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (rowIndex == 0) {
                        Spacer(modifier = Modifier.width(5.dp))
                        CircularCard(title = "ANI", modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(8.dp))
                        if (participants.isNotEmpty()) {
                            SquareCard(title = participants[0], modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        if (participants.size > 1) {
                            SquareCard(title = participants[1], modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    } else {
                        val startParticipantIndex = (rowIndex - 1) * 3 + 2
                        val endParticipantIndex = min(startParticipantIndex + 3, participants.size)
                        for (i in startParticipantIndex until endParticipantIndex) {
                            Spacer(modifier = Modifier.width(8.dp))
                            SquareCard(title = participants[i], modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(72.dp)) // Space for fixed button
        }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .clickable {
                        if (ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.RECORD_AUDIO
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                        } else {
                            startRecording(context) { recorder, file ->
                                mediaRecorder = recorder
                                audioFile = file
                                isRecording = true
                                Handler(Looper.getMainLooper()).postDelayed({
                                    stopRecording(mediaRecorder)
                                    isRecording = false
                                    audioFile?.let { file ->
                                        uploadAudio(file) { transcriptionText ->
                                            transcription = transcriptionText
                                        }
                                    }
                                }, 5000)
                            }
                        }
                    }
                    .background(
                        color = primaryLight,
                        shape = RoundedCornerShape(30.dp))
                    .padding(start = 32.dp, top = 24.dp, end = 40.dp, bottom = 24.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Mic,
                    contentDescription = "Voice Icon",
                    tint = onPrimaryLight
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = if (isRecording) "Recording..." else "Speak",
                    style = MaterialTheme.typography.labelLarge,
                    color = onPrimaryLight
                )
            }
        }
    }
}

fun startRecording(context: Context, onStarted: (MediaRecorder, File) -> Unit) {
    val file = File.createTempFile("audio", ".3gp", context.cacheDir)
    val recorder = MediaRecorder().apply {
        setAudioSource(MediaRecorder.AudioSource.MIC)
        setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        setOutputFile(file.absolutePath)
        prepare()
        start()
    }
    onStarted(recorder, file)
}

fun stopRecording(mediaRecorder: MediaRecorder?) {
    mediaRecorder?.apply {
        try {
            stop()
        } catch (e: RuntimeException) {
            // handle stop() failed case
        }
        release()
    }
}

fun uploadAudio(file: File, onTranscriptionFetched: (String) -> Unit) {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.56.1:8000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()

    val apiService = retrofit.create(ApiService::class.java)
    val requestBody = RequestBody.create("audio/3gp".toMediaTypeOrNull(), file)
    val body = MultipartBody.Part.createFormData("audio", file.name, requestBody)

    apiService.uploadAudio(body).enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                val jsonResponse = response.body()?.string()?.let { JSONObject(it) }
                val transcription = jsonResponse?.getString("text") ?: "No transcription found"
                onTranscriptionFetched(transcription)
            } else {
                onTranscriptionFetched("Failed to upload audio")
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            onTranscriptionFetched("Error: ${t.message}")
        }
    })
}

@Composable
fun BackArrow(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Icon(
        imageVector = Icons.Filled.ArrowBack,
        contentDescription = "Back",
        tint = surfaceContainerDark,
        modifier = modifier.clickable(onClick = onClick)
    )
}

@Composable
fun ThreeDotsIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Icon(
        imageVector = Icons.Filled.MoreVert,
        contentDescription = "More",
        tint = surfaceContainerDark,
        modifier = modifier.clickable(onClick = onClick)
    )
}

@Preview
@Composable
fun MeetingScreenPreview() {
    val navController = rememberNavController()
    MeetingScreen(navController, "Meeting Title", "Meeting Description")
}
