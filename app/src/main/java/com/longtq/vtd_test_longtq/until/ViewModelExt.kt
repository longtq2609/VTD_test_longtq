package com.longtq.vtd_test_longtq.until

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longtq.vtd_test_longtq.util.EventBus
import kotlinx.coroutines.launch

fun ViewModel.sendEvent(event: Any) {
    viewModelScope.launch {
        EventBus.sendEvent(event)
    }
}