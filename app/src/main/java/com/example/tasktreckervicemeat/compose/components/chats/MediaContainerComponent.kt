package com.example.tasktreckervicemeat.compose.components.chats

import android.R.attr.maxHeight
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberOverscrollEffect
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.tasktreckervicemeat.ui.theme.Black22
import com.example.tasktreckervicemeat.ui.theme.BlueNeon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.overscroll
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity

@Preview
@Composable
fun MediaContainerTest() {
    MediaContainerComponent()
}





@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun MediaContainerComponent() {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val lazyGridState = rememberLazyGridState()
    val scrollState = rememberScrollState()
    val scrollToDp = 400.dp
    val scrollToPx = with(LocalDensity.current) { scrollToDp.toPx() }

    val heightDpAnimation = animateDpAsState(
        targetValue = 400.dp
    )

    /*LaunchedEffect(key1 = Unit)  {
        scrollState.animateScrollTo(value = scrollToPx.toInt())
    }*/

    Box() {
        Column(modifier = Modifier.fillMaxSize().background(BlueNeon)) {

            LazyVerticalGrid(
                modifier = Modifier.height(screenHeight),
                state = lazyGridState,
                columns = GridCells.Fixed(count = 3)
            ) {
                items(3) {
                    Spacer(modifier = Modifier.height(heightDpAnimation.value).fillMaxWidth().background(Color.Green))
                }

                items(50) {
                    Row(modifier = Modifier.size(100.dp).background(Color.Red)) {
                        Text(text = "topBarIsActive.value.toString()")
                        Spacer(modifier = Modifier.width(50.dp))
                        Text(text = "(scrollState.maxValue - topBarHeightPx.toInt()).toString()")
                    }
                }
            }
        }

    }

}
