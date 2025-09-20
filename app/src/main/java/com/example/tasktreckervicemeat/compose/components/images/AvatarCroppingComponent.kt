package com.example.tasktreckervicemeat.compose.components.images

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.awaitDragOrCancellation
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitTouchSlopOrCancellation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip

import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.get
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.domain.model.ImageModel
import com.example.tasktreckervicemeat.R
import com.example.tasktreckervicemeat.ui.theme.Black22
import com.example.tasktreckervicemeat.ui.theme.BlueNeon
import com.example.tasktreckervicemeat.ui.theme.Gray31
import com.example.tasktreckervicemeat.ui.theme.Montserrat
import com.example.tasktreckervicemeat.ui.theme.PinkDD
import com.example.tasktreckervicemeat.ui.theme.Yellow26
import kotlinx.coroutines.async
import kotlinx.coroutines.delay




@SuppressLint("UnrememberedMutableState")
@Composable
fun AvatarCroppingComponent(
    uri: Uri,
) {
    // Получение изображения по uri
    val context = LocalContext.current
    val bitmap = context.contentResolver.openInputStream(uri).use { inputStream ->
        BitmapFactory.decodeStream(inputStream)
    }
    
    // Ширина и высота в пикселях
    val bitmapWidthPx = bitmap.width
    val bitmapHeightPx = bitmap.height

    val density = LocalDensity.current

    // Ширина и высота в dp
    val bitmapWidthDp = with(density) { bitmapWidthPx.toDp() }
    val bitmapHeightDp = with(density) { bitmapHeightPx.toDp() }

    // Получение ширины и высоты экрана в dp
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val screenWidthPx = with(density) {screenWidthDp.toPx()}
    val screenHeightPx = with(density) {screenHeightDp.toPx()}



    // Вычисляем соотношение сторон, чтобы понять растягивать изображение по ширине или высоте
    val isHorizontalOrientation = (bitmapWidthPx.toFloat() / bitmapHeightPx) >= (screenWidthPx.toFloat() / screenHeightPx)
    // Размеры Image(bitmap) в зависимости от ориентации
    // Если  горизонтальная, то ширина выставляется по ширине экрана, если наоборот
    // вычисляется по пропорции, float спользуется из-за того, что нельзя перемножать dp на dp
    /*val imageWidth by derivedStateOf { if (isHorizontalOrientation) screenWidthDp else (screenHeightDp * bitmapWidthDp.value.toFloat()) / bitmapHeightDp.value.toFloat() }
    val imageWidthPx = with(density) { imageWidth.toPx() }*/
    val imageWidthPx by derivedStateOf { if (isHorizontalOrientation) screenWidthPx else (screenHeightPx * bitmapWidthPx) / bitmapHeightPx }
    val imageWidth = with(density) { imageWidthPx.toDp() }


    // Если  вертикальная, то высота выставляется по высоте экрана, если наоборот
    // вычисляется по пропорции, float спользуется из-за того, что нельзя перемножать dp на dp
    /*val imageHeight by derivedStateOf { if (!isHorizontalOrientation) screenHeightDp else (screenWidthDp * bitmapHeightDp.value.toFloat()) / bitmapWidthDp.value.toFloat() }
    val imageHeightPx = with(density) { imageHeight.toPx() }*/
    val imageHeightPx by derivedStateOf { if (!isHorizontalOrientation) screenHeightPx else (screenWidthPx * bitmapHeightPx) / bitmapWidthPx }
    val imageHeight = with(density) { imageHeightPx.toDp() }



    // Размер круглого фокуса, если ориентация горизонатальная, то
    // тогда фокус растягивается по высоте изображения
    val initialFocusSize by derivedStateOf { if (isHorizontalOrientation && imageHeight < imageWidth) imageHeight else imageWidth }
    val initialFocusSizePx = with(density) { initialFocusSize.toPx() }

    // Обратная пропорция если изображение больше фокус меньше про оффсет так же
    var mutableImageScale by remember { mutableFloatStateOf(1f) }
    var mutableImageXOffset by remember { mutableFloatStateOf(0f) }
    var mutableImageYOffset by remember { mutableFloatStateOf(0f) }

    // Не понимаю, почему работает, но оно работает (сделал как изначально хотел определять ориентацию - по сути это зхамена isHoriozontalOrientation
    val widthOrHeight by derivedStateOf { if (imageHeight > imageWidth) bitmapWidthPx else bitmapHeightPx  }

    // Cмещение для битмапа c учетом размера фокуса
    val bitmapXOffset by derivedStateOf { (bitmapWidthPx - (widthOrHeight / mutableImageScale).toInt()) / 2 }
    val bitmapYOffset by derivedStateOf { (bitmapHeightPx - (widthOrHeight / mutableImageScale).toInt()) / 2 }

    // Определяем находится ли сейчас изображение в режиме скейла
    var gestureIsOver by remember { mutableStateOf(true) }

    // Определяем вышел ли скейл за границы изображения
    val invalidScale by derivedStateOf { initialFocusSize > (initialFocusSize * mutableImageScale) }

    // False если фокус выше 0 по оси Y и вышел за границы изображения
    val positiveYBorder by derivedStateOf { (bitmapYOffset - ((mutableImageYOffset / mutableImageScale) * (bitmapWidthPx / imageWidthPx))).toInt() >= 0}
    // False если фокус ниже 0 по оси Y и вышел за границы изображения
    val negativeYBorder by derivedStateOf { ((bitmapYOffset - ((mutableImageYOffset / mutableImageScale) * (bitmapWidthPx / imageWidthPx))).toInt() + (widthOrHeight / mutableImageScale).toInt() <= bitmapHeightPx)}
    // False если фокус выше 0 по оси X и вышел за границы изображения
    val positiveXBorder by derivedStateOf { (bitmapXOffset - ((mutableImageXOffset / mutableImageScale) * (bitmapHeightPx / imageHeightPx))).toInt() >= 0}
    // False если фокус ниже 0 по оси X и вышел за границы изображения
    val negativeXBorder by derivedStateOf { ((bitmapXOffset - ((mutableImageXOffset / mutableImageScale) * (bitmapHeightPx / imageHeightPx))).toInt() + (widthOrHeight / mutableImageScale) <= bitmapWidthPx)}

    var allInTheBorder by remember { mutableStateOf(!(positiveYBorder && negativeYBorder && positiveXBorder && negativeXBorder)) }

    val animatedScale by animateFloatAsState(
        targetValue = mutableImageScale,
        animationSpec = if (gestureIsOver && !invalidScale) tween(durationMillis = 500) else tween(durationMillis = 0), // можно spring(), tween(), keyframes()
        label = "imageScale"
    )

    val animatedXOffset by animateFloatAsState(
        targetValue = mutableImageXOffset,
        animationSpec = if (gestureIsOver && !allInTheBorder) tween(durationMillis = 500) else tween(durationMillis = 0), // можно spring(), tween(), keyframes()
        label = "animatedXOffset"
    )

    val animatedYOffset by animateFloatAsState(
        targetValue = mutableImageYOffset,
        animationSpec = if (gestureIsOver && !allInTheBorder) tween(durationMillis = 500) else tween(durationMillis = 0), // можно spring(), tween(), keyframes()
        label = "animatedYOffset"
    )


    LaunchedEffect(gestureIsOver) {
        if (gestureIsOver && !positiveYBorder) {
            mutableImageYOffset = ((imageHeightPx / 2f) * mutableImageScale) - (initialFocusSizePx / 2f)
        }
        if (gestureIsOver && !negativeYBorder) {
            mutableImageYOffset = (initialFocusSizePx / 2f) - ((imageHeightPx / 2f) * mutableImageScale)
        }
        if (gestureIsOver && !positiveXBorder) {
            mutableImageXOffset = ((imageWidthPx / 2f) * mutableImageScale) - (initialFocusSizePx / 2f)
        }
        if (gestureIsOver && !negativeXBorder) {
            mutableImageXOffset = (initialFocusSizePx / 2f) - ((imageWidthPx / 2f) * mutableImageScale)
        }
        if (gestureIsOver && invalidScale) {
            mutableImageScale = 1f
            mutableImageYOffset = 0f
            mutableImageXOffset = 0f
        }
    }
    var onStart by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            awaitPointerEventScope {
                while (true) {
                    val event = awaitPointerEvent()
                    val pointers = event.changes.filter { it.pressed }
                    if (pointers.size >= 1) {
                        gestureIsOver = false
                    } else {
                        gestureIsOver = true
                    }
                }
            }
        }
        .pointerInput(Unit) {
            detectTransformGestures { centroid, pan, gestureZoom, gestureRotate ->
                mutableImageScale = mutableImageScale * gestureZoom
                mutableImageXOffset = mutableImageXOffset + pan.x
                mutableImageYOffset = mutableImageYOffset + pan.y
            }
        },
        contentAlignment = Alignment.Center
    ) {
        Column {
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        scaleY = animatedScale
                        scaleX = animatedScale
                        translationX = animatedXOffset
                        translationY = animatedYOffset

                    }
            ) {
                Image(
                    modifier = Modifier
                        .height(imageHeight)
                        .width(imageWidth),
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = null
                )

            }

        }

        Box(
            modifier = Modifier
                .height(initialFocusSize)
                .width(initialFocusSize)
                .border(width = 2.dp, color = Color.Red)
                .clip(CircleShape)
                .background(Color.Green.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "+", color = Color.Blue)

        }



    }
    Button(onClick = {onStart = !onStart}) { }


    if (!allInTheBorder && onStart) {
        val testImage = Bitmap.createBitmap(
            bitmap,
            // Оффсет вычисляется с учетом скейла и с учетом размера битмана (вычисляется по пропорции)
            (bitmapXOffset - ((mutableImageXOffset / mutableImageScale) * (bitmapHeightPx / imageHeightPx))).toInt(),
            (bitmapYOffset - ((mutableImageYOffset / mutableImageScale) * (bitmapWidthPx / imageWidthPx))).toInt(),
            (widthOrHeight / mutableImageScale).toInt(),
            (widthOrHeight / mutableImageScale).toInt()
        )
        Box(contentAlignment = Alignment.Center) {
            Image(bitmap = testImage.asImageBitmap(), contentDescription = null)
            Text(text = "+", color = Color.Blue)
        }
    }

}