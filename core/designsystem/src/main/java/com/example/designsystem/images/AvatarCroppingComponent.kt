package com.example.designsystem.images

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import com.example.designsystem.R
import com.example.designsystem.theme.Black22
import com.example.designsystem.theme.BlueNeon
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
@Composable
fun AvatarCroppingComponent(
    uri: Uri,
    croppedBitmap: (Bitmap) -> Unit,
    avatarCropperIsActive: (Boolean) -> Unit,
    onClickComplete: () -> Unit,
) {
    // Получение изображения по uri
    val context = LocalContext.current
    var bitmap by remember {
        mutableStateOf(
            context.contentResolver.openInputStream(uri).use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        )
    }

    // Const
    val zRotate = remember { mutableStateOf(90f) }
    val animatedRotation = remember { Animatable(0f)}
    val animatedScaleRotation = remember { Animatable(1f) }

    // Ширина и высота в пикселях
    val bitmapWidthPx = bitmap.width
    val bitmapHeightPx = bitmap.height

    val density = LocalDensity.current

    // Получение ширины и высоты экрана в dp
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val screenWidthPx = with(density) {screenWidthDp.toPx()}
    val screenHeightPx = with(density) {screenHeightDp.toPx()}

    var onStart by remember { mutableStateOf(false)}

    // Вычисляем соотношение сторон, чтобы понять растягивать изображение по ширине или высоте
    val isHorizontalOrientation = (bitmapWidthPx.toFloat() / bitmapHeightPx) >= (screenWidthPx.toFloat() / screenHeightPx)
    // Размеры Image(bitmap) в зависимости от ориентации
    // Если  горизонтальная, то ширина выставляется по ширине экрана, если наоборот
    // вычисляется по пропорции, float спользуется из-за того, что нельзя перемножать dp на dp
    val imageWidthPx by derivedStateOf { if (isHorizontalOrientation) screenWidthPx else (screenHeightPx * bitmapWidthPx) / bitmapHeightPx }
    val imageWidth = with(density) { imageWidthPx.toDp() }


    // Если  вертикальная, то высота выставляется по высоте экрана, если наоборот
    // вычисляется по пропорции, float спользуется из-за того, что нельзя перемножать dp на dp
    val imageHeightPx by derivedStateOf { if (!isHorizontalOrientation) screenHeightPx else (screenWidthPx * bitmapHeightPx) / bitmapWidthPx }
    val imageHeight = with(density) { imageHeightPx.toDp() }


    // Размер круглого фокуса, если ориентация горизонатальная, то
    // тогда фокус растягивается по высоте изображения
    val initialFocusSize by derivedStateOf { if (isHorizontalOrientation && imageHeight < imageWidth) imageHeight * animatedScaleRotation.value else imageWidth * animatedScaleRotation.value}
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

    var isRotated by remember { mutableStateOf(false)}

    val animatedScale by animateFloatAsState(
        targetValue = mutableImageScale,
        animationSpec = if (gestureIsOver && !invalidScale) tween(durationMillis = 500) else tween(durationMillis = 0),
        label = "imageScale"
    )

    val animatedXOffset by animateFloatAsState(
        targetValue = mutableImageXOffset,
        animationSpec = if (gestureIsOver && !allInTheBorder) tween(durationMillis = 500) else tween(durationMillis = 0),
        label = "animatedXOffset"
    )

    val animatedYOffset by animateFloatAsState(
        targetValue = mutableImageYOffset,
        animationSpec = if (gestureIsOver && !allInTheBorder) tween(durationMillis = 500) else tween(durationMillis = 0),
        label = "animatedYOffset"
    )


    LaunchedEffect(isRotated) {

        if (isRotated) {

            awaitAll(
                async {
                    animatedRotation.animateTo(
                        targetValue = 90f,
                        animationSpec = tween(durationMillis = 300)
                    )
                },
                async {
                    val bitmapRatio = (bitmapWidthPx / bitmapHeightPx.toFloat())
                    val rotationScale = mutableImageScale * bitmapRatio
                    animatedScaleRotation.animateTo(
                        targetValue = rotationScale,
                        animationSpec = tween(durationMillis = 300)
                    )
                }
            )
            bitmap = getNewBitmap(
                bitmap = bitmap,
                xOffset = 0,
                yOffset = 0,
                degrees = zRotate.value,
            )
            animatedRotation.snapTo(0f)
            animatedScaleRotation.snapTo(1f)
            isRotated = false

        }
    }


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
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Black22)
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

        Box(
            modifier = Modifier
                .graphicsLayer {
                    scaleY = animatedScale
                    scaleX = animatedScale
                    translationX = animatedXOffset
                    translationY = animatedYOffset
                    if (isRotated) {
                        rotationZ = animatedRotation.value
                        scaleY = animatedScaleRotation.value
                        scaleX = animatedScaleRotation.value
                    }

                }
        ) {

            Image(
                modifier = Modifier
                    .height(imageHeight)
                    .width(imageWidth),
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                filterQuality = FilterQuality.Low
            )
            Text(text = animatedRotation.value.toString())
        }
        Canvas(modifier = Modifier
            .matchParentSize()
            .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)) {
            drawRect(color = Black22.copy(alpha = 0.7f))
            drawCircle(color = Color.Transparent, radius = initialFocusSizePx / 2f, center = center, blendMode = BlendMode.Clear)
        }

        // Ниже находится прочий контент



        // Нижнее меню с кнопками
        val systemBottomBarHeightPx = WindowInsets.navigationBars.getBottom(density = density)
        val systemBottomBarHeightDp = with(density) {systemBottomBarHeightPx.toDp()}
        Row(
            modifier = Modifier
                .padding(bottom = systemBottomBarHeightDp + 10.dp)
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(40.dp)
                        .clickable {
                            isRotated = true
                        },
                    painter = painterResource(R.drawable.rotate_icon),
                    tint = Color.White,
                    contentDescription = null
                )
                Icon(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(40.dp),
                    painter = painterResource(R.drawable.vice_versa_icon),
                    tint = Color.White,
                    contentDescription = null
                )
            }
            Icon(
                modifier = Modifier
                    .padding(10.dp)
                    .size(50.dp)
                    .clickable {
                        onStart = !onStart
                    },
                painter = painterResource(R.drawable.confirm_icon),
                tint = BlueNeon,
                contentDescription = null
            )
        }
    }


    if (onStart) {
        val x = (bitmapXOffset - ((mutableImageXOffset / mutableImageScale) * (bitmapHeightPx / imageHeightPx))).toInt()
        val y = (bitmapYOffset - ((mutableImageYOffset / mutableImageScale) * (bitmapWidthPx / imageWidthPx))).toInt()
        val width = (widthOrHeight / mutableImageScale).toInt()
        val height = (widthOrHeight / mutableImageScale).toInt()
        val newBitmap = Bitmap.createBitmap(
            bitmap,
            x,
            y,
            width,
            height

        )
        avatarCropperIsActive(false)
        croppedBitmap(newBitmap)
        onClickComplete.invoke()
    }

}


private fun getNewBitmap(
    bitmap: Bitmap,
    xOffset: Int,
    yOffset: Int,
    degrees: Float
): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(degrees)
    return Bitmap.createBitmap(
        bitmap,
        xOffset,
        yOffset,
        bitmap.width,
        bitmap.height,
        matrix,
        true
    )
}
