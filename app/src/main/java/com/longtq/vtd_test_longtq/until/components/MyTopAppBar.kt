package com.longtq.vtd_test_longtq.until.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(text: String, onBackClick: (() -> Unit)? = null, isShowIconBack: Boolean = false) {
    TopAppBar(
        title = {
            Text(text = text)
        },

        actions = {
            if (isShowIconBack) {
                IconButton(onClick = {
                    if (onBackClick != null) {
                        onBackClick()
                    }
                }) {
                    Icon(Icons.Filled.Close, contentDescription = null)
                }
            }
        },
        modifier = Modifier.shadow(
            elevation = 5.dp,
            spotColor = Color.DarkGray,
        )
    )
}