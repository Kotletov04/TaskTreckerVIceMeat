package com.example.tasktreckervicemeat.compose.components.images

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.draw.clip
import com.example.tasktreckervicemeat.compose.screens.hubs.HubsScreen
import com.example.tasktreckervicemeat.ui.theme.Black22
import com.example.tasktreckervicemeat.ui.theme.BlueNeon
import com.example.tasktreckervicemeat.ui.theme.Gray31
import com.example.tasktreckervicemeat.ui.theme.Gray61
import kotlinx.coroutines.delay

@Preview
@Composable
fun MediaContainerTest() {
    val test = remember { mutableStateOf(false) }
    Box(contentAlignment = Alignment.Center) {
        HubsScreen()
        Button(onClick = {test.value = !test.value}) { Text("text")}
        if (test.value) {
            MediaContainerComponent(onStart = test, onClickBack = {})
        }

    }

}





@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun MediaContainerComponent(onStart: MutableState<Boolean>, onClickBack: () -> Unit) {
    val containerInStartScroll = remember { mutableStateOf(true) }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val lazyGridState = rememberLazyGridState()
    val topBarHeight = 100.dp
    val topBarIsActive = remember {
        derivedStateOf {
            lazyGridState.firstVisibleItemIndex > 0
        }
    }
    val mediaIsActive = remember {
        derivedStateOf {
            !(lazyGridState.firstVisibleItemScrollOffset < 1000 && lazyGridState.firstVisibleItemIndex == 0 && !containerInStartScroll.value)
        }
    }
    LaunchedEffect(key1 = Unit)  {
        lazyGridState.animateScrollBy(value = 1000f, animationSpec = tween(500))
        containerInStartScroll.value = !containerInStartScroll.value
    }
    LaunchedEffect(key1 = mediaIsActive.value) {
        if (!mediaIsActive.value) {
            delay(100)
            lazyGridState.animateScrollBy(value = -1000f, animationSpec = tween(500))
            onStart.value = false
        }

    }
    Box() {
        AnimatedVisibility(
            enter = slideInVertically(),
            exit = shrinkVertically(),
            visible = topBarIsActive.value
        ) {
            TopBarMediaContainer(onClickBack = onClickBack, height = topBarHeight)
        }

        Column(modifier = Modifier.padding(top = topBarHeight).fillMaxSize()) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight),
                state = lazyGridState,
                columns = GridCells.Fixed(count = 3),
                userScrollEnabled = mediaIsActive.value
            ) {

                items(3) {
                    Spacer(modifier = Modifier.height(screenHeight).fillMaxWidth())
                }
                item(span = { GridItemSpan(maxLineSpan)}) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(25.dp)
                            .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                            .background(Gray31),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(modifier = Modifier.width(30.dp).height(2.dp).background(BlueNeon).clip(RoundedCornerShape(30.dp)))
                    }
                }
                items(50) {
                    Box(modifier = Modifier.fillMaxSize().background(Gray31), contentAlignment = Alignment.Center) {
                        Row(modifier = Modifier.padding(3.dp).size(120.dp).background(Color.Red)) {
                            Text(text ="te")
                            Spacer(modifier = Modifier.width(50.dp))
                            Text(text = "")
                        }
                    }

                }

            }
        }

    }

}
