package com.example.designsystem.images

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil3.Bitmap
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.size.Size
import com.example.designsystem.R
import com.example.designsystem.theme.Black19
import com.example.designsystem.theme.Black22
import com.example.designsystem.theme.BlueNeon
import com.example.designsystem.theme.Gray31
import com.example.designsystem.theme.OrangeFF
import com.example.designsystem.theme.PinkDD
import com.example.designsystem.theme.Yellow26




@Composable
fun UserIconComponent(
    size: Dp,
    isActive: Boolean = true,
    bitmap: ImageBitmap? = ImageBitmap.imageResource(R.drawable.hubs_background),
    shape: Shape = CircleShape,
    borderWidth: Dp,
    onClickEnabled: Boolean = false,
    onClick: () -> Unit = {},
    isError: Boolean = false,
    isLoading: Boolean = false,
    loadingComponent: @Composable () -> Unit = { CircularProgressIndicator() }
) {
    var startBorderColor by remember { mutableStateOf(Yellow26) }
    var endBorderColor by remember { mutableStateOf(PinkDD) }
    if (!isActive) {
        startBorderColor = Gray31
        endBorderColor = Black19
    }
    var startAnimatedBorderColor = animateColorAsState(
        targetValue = startBorderColor
    )
    var endAnimatedBorderColor = animateColorAsState(
        targetValue = if (isError) OrangeFF else endBorderColor
    )

    Box(
        modifier = Modifier
            .clip(shape = shape)
            .size(size)
            .border(
                width = borderWidth,
                brush = Brush.horizontalGradient(
                    colors = listOf(startAnimatedBorderColor.value, endAnimatedBorderColor.value)
                ),
                shape = shape
            )
            .background(
                color = Black22
            )
            .clickable(enabled = onClickEnabled, onClick = onClick),
        contentAlignment = Alignment.Center


    ) {
        when {
            isError -> {
                Icon(
                    painter = painterResource(R.drawable.person_icon),
                    contentDescription = null,
                    tint = OrangeFF
                )

            }
            isLoading -> {
                loadingComponent.invoke()
            }
            else -> {
                Image(
                    modifier = Modifier.clip(shape = shape).fillMaxWidth(),
                    bitmap = bitmap?:  ImageBitmap.imageResource(R.drawable.hubs_background),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

        }

    }

}