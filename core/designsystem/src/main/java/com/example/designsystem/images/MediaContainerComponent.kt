package com.example.designsystem.images

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.designsystem.R
import com.example.designsystem.theme.Black22
import com.example.designsystem.theme.BlueNeon
import com.example.designsystem.theme.Gray31
import com.example.designsystem.theme.Gray61
import com.example.designsystem.theme.Montserrat
import com.example.designsystem.theme.PinkDD
import com.example.designsystem.theme.Yellow26
import kotlinx.coroutines.delay

data class ImageModel(
    val id: Long,
    val name: String,
    val date: String,
    val data: String,
    val uri: String
)
@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun MediaContainerComponent(
    onStart: MutableState<Boolean>,
    onClickBack: () -> Unit,
    images: List<ImageModel>,
    multipleChoiceMode: Boolean = false,
    croppedBitmap: (Bitmap) -> Unit,
    onClickComplete: () -> Unit
    ) {

    val containerInStartScroll = remember { mutableStateOf(true) }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
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
    var customGridCells by remember { mutableStateOf(3f) }
    val mutableImageSize = (900 / customGridCells).toInt()

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
        var avatarIsSelected by remember { mutableStateOf(false)}
        var avatarUri by remember { mutableStateOf("") }
        Column(modifier = Modifier
            .padding(top = topBarHeight)
            .fillMaxSize()) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight)
                    .pointerInput(Unit) {
                        detectTransformGestures { centroid, pan, gestureZoom, gestureRotate ->
                            customGridCells =
                                (customGridCells * (1 / gestureZoom)).coerceIn(3f, 10f)
                            println(gestureZoom)
                        }
                    },
                state = lazyGridState,
                columns = GridCells.Fixed(customGridCells.toInt()),
                userScrollEnabled = mediaIsActive.value
            ) {

                items(customGridCells.toInt()) {
                    Spacer(modifier = Modifier
                        .height(screenHeight)
                        .fillMaxWidth())
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
                        Box(modifier = Modifier
                            .width(30.dp)
                            .height(2.dp)
                            .background(BlueNeon)
                            .clip(RoundedCornerShape(30.dp)))
                    }
                }

                items(items = images, key = {it.id}) { imageModel ->
                    var imageIsSelected by remember { mutableStateOf(false)}
                    var selectedColor by remember { mutableStateOf(Color.White) }

                    Box(modifier = Modifier
                        .size(screenWidth / customGridCells)
                        .background(Gray61)
                        .padding(3.dp), contentAlignment = Alignment.Center) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(imageModel.uri.toUri())
                                .crossfade(true)
                                .size(mutableImageSize, mutableImageSize)
                                .memoryCachePolicy(CachePolicy.ENABLED)
                                .build(),
                            placeholder = painterResource(R.drawable.iconbannyai),
                            error = painterResource(R.drawable.iconbannyai),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = RoundedCornerShape(10.dp))
                                .border(
                                    width = 1.dp,
                                    color = if (imageIsSelected) BlueNeon else Color.Transparent,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .pointerInput(Unit) {
                                    if (multipleChoiceMode) {
                                        detectTapGestures(
                                            onLongPress = {
                                                imageIsSelected = !imageIsSelected
                                                selectedColor = BlueNeon
                                            },
                                        )
                                    } else {
                                        detectTapGestures(
                                            onTap = {
                                                avatarIsSelected = !avatarIsSelected
                                                avatarUri = imageModel.uri
                                            }
                                        )
                                    }


                                }
                        )
                        if (multipleChoiceMode) {
                            Box(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .size(20.dp)
                                    .border(
                                        width = 1.dp,
                                        color = if (imageIsSelected) BlueNeon else Color.White,
                                        shape = CircleShape
                                    )
                                    .clip(shape = CircleShape)
                                    .align(Alignment.TopEnd)
                                    .clickable { imageIsSelected = !imageIsSelected },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.open_icon),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(10.dp),
                                    tint = if (imageIsSelected) BlueNeon else Color.Transparent
                                )
                            }
                        }

                    }

                }

            }
        }
        AnimatedVisibility(
            enter = slideInVertically(),
            exit = shrinkVertically(),
            visible = topBarIsActive.value
        ) {
            TopBarMediaContainer(onClickBack = onClickBack, height = topBarHeight)
        }
        AnimatedVisibility(
            visible = avatarIsSelected
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AvatarCroppingComponent(avatarUri.toUri(), croppedBitmap = croppedBitmap, avatarCropperIsActive = { isActive -> avatarIsSelected = isActive }, onClickComplete = onClickComplete)
            }
        }
    }

}


@Composable
private fun TopBarMediaContainer(onClickBack: () -> Unit, height: Dp = 65.dp) {
    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(Black22)
            .padding(20.dp), verticalAlignment = Alignment.Bottom) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.clickable(onClick = onClickBack)) {
                Image(
                    painter = painterResource(R.drawable.arrow_back_icon),
                    contentDescription = null,
                    modifier = Modifier)
            }
            Text(
                modifier = Modifier.padding(start = 15.dp),
                color = Color.White,
                lineHeight = 16.sp,
                fontSize = 16.sp,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Light,
                text = "Галерея"
            )
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(brush = Brush.horizontalGradient(colors = listOf(PinkDD, Yellow26))))
    }

}