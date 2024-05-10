package com.longtq.vtd_test_longtq

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.longtq.vtd_test_longtq.navigation.AppNavigation
import com.longtq.vtd_test_longtq.ui.theme.VTD_test_longtqTheme
import com.longtq.vtd_test_longtq.util.Event
import com.longtq.vtd_test_longtq.util.EventBus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val lifecycleOwner = LocalLifecycleOwner.current.lifecycle
            LaunchedEffect(key1 = lifecycleOwner) {
                EventBus.events.collect { event ->
                    when (event) {
                        is Event.Toast -> {
                            Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
            VTD_test_longtqTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

