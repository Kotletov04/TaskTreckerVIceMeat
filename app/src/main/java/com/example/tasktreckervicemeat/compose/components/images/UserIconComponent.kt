package com.example.tasktreckervicemeat.compose.components.images

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.size.Size
import com.example.tasktreckervicemeat.R
import com.example.tasktreckervicemeat.ui.theme.Black19
import com.example.tasktreckervicemeat.ui.theme.Gray31
import com.example.tasktreckervicemeat.ui.theme.PinkDD
import com.example.tasktreckervicemeat.ui.theme.Yellow26


@Composable
@Preview
fun TestImage() {
    UserIconComponent(size = 100.dp, model = "", borderWidth = 1.dp, shape = RoundedCornerShape(15.dp))
}


@Composable
fun UserIconComponent(
    size: Dp,
    isActive: Boolean = true,
    model: String,
    shape: Shape = CircleShape,
    borderWidth: Dp
) {

    Box(
        modifier = Modifier
            .clip(shape = shape)
            .size(size)
            .background(
                brush = Brush.horizontalGradient(
                    colors = if (isActive) listOf(Yellow26, PinkDD) else listOf(Gray31, Black19)
                )
            )
            .padding(borderWidth),
        contentAlignment = Alignment.Center


    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(model)
                .size(Size.ORIGINAL)
                .build()
        )
        val imageState by painter.state.collectAsState()
        when (imageState) {
            is AsyncImagePainter.State.Empty,
            is AsyncImagePainter.State.Error -> {
                Image(
                    modifier = Modifier.clip(shape = shape).fillMaxWidth(),
                    painter = painterResource(R.drawable.img),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator()
            }
            is AsyncImagePainter.State.Success -> {
                Image(painter = painter, contentDescription = null)
            }
        }

    }

}