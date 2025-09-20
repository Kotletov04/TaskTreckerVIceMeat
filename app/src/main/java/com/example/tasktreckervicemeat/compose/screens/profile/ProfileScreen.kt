package com.example.tasktreckervicemeat.compose.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.ProfileCustomFieldsModel
import com.example.tasktreckervicemeat.R
import com.example.tasktreckervicemeat.compose.components.images.MediaContainerComponent
import com.example.tasktreckervicemeat.compose.components.images.UserIconComponent
import com.example.tasktreckervicemeat.compose.components.profile.UserProfileInfoComponent
import com.example.tasktreckervicemeat.compose.components.screen.TopBar
import com.example.tasktreckervicemeat.ui.theme.Black22
import com.example.tasktreckervicemeat.ui.theme.Gray4B
import com.example.tasktreckervicemeat.ui.theme.Montserrat
import com.example.tasktreckervicemeat.ui.theme.PinkDD
import com.example.tasktreckervicemeat.ui.theme.Yellow26


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    val density = LocalDensity.current
    val systemTopBarHeight = WindowInsets.statusBars.getTop(density = density)
    val topBarHeightDp = with(density) { systemTopBarHeight.toDp() } * 2 + 40.dp

    val popupState = remember { mutableStateOf(false) }
    val mediaContainerIsStart = remember { mutableStateOf(false) }
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        viewModel.getProfileInfo()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                height = topBarHeightDp,
                backgroundColor = Color.Transparent,
                lineBackground = Brush.horizontalGradient(listOf(Color.Transparent, Color.Transparent)),
                content = {
                    TopBarProfileContent(
                        onClickDots = {
                            popupState.value = true
                        },
                        onClickCamera = {
                            viewModel.getImages()
                            mediaContainerIsStart.value = true
                            popupState.value = false
                        },
                        onClickEdit = {
                            viewModel.startProfileEditMod()
                            popupState.value = false
                        },
                        popupState = popupState
                    )
                }
            )
        },
        containerColor = Black22,
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TopProfileScreen(topBarHeightDp = topBarHeightDp)
                Text(
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = Montserrat,
                    color = Color.White,
                    text = "Информация"
                )
            }
            items(state.profileInfo?: emptyList<ProfileCustomFieldsModel>()) {
                UserProfileInfoComponent(it.fields, title = it.title, editMode = state.editMode!!)
            }

        }
    }
    if (!state.testData.isNullOrEmpty() && mediaContainerIsStart.value) {
        MediaContainerComponent(
            onStart = mediaContainerIsStart,
            onClickBack = { mediaContainerIsStart.value = false },
            images = state.testData
        )
    }

}


@Composable
private fun TopBarProfileContent(onClickDots: () -> Unit, popupState: MutableState<Boolean>, onClickEdit: () -> Unit, onClickCamera: () -> Unit) {
    Icon(
        modifier = Modifier
            .width(30.dp)
            .clickable(enabled = true, onClick = { }),
        painter = painterResource(R.drawable.arrow_back_icon),
        contentDescription = null,
        tint = Color.White
    )
    Box() {
        ProfileDropdownMenu(popupState = popupState, onClickCamera = onClickCamera, onClickEdit = onClickEdit)
        Icon(
            modifier = Modifier
                .width(30.dp)
                .clickable(enabled = true, onClick = { onClickDots.invoke() }),
            painter = painterResource(R.drawable.dots_icon),
            contentDescription = null,
            tint = Color.White
        )

    }



}

@Composable
private fun TopProfileScreen(topBarHeightDp: Dp) {
    Column {
        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(R.drawable.profile_background_image),
                    contentScale = ContentScale.FillHeight
                )
                .fillMaxWidth()
                .height(topBarHeightDp + 150.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Icon(painter = painterResource(R.drawable.m), contentDescription = null, tint = Black22)
            Row(
                modifier = Modifier
                    .padding(25.dp)
            ) {
                UserIconComponent(size = 100.dp, model = "", shape = CircleShape, borderWidth = 2.dp)
                Column(modifier = Modifier.padding(start = 20.dp)) {
                    Text(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = Montserrat,
                        color = Color.White,
                        text = "Kotletov"
                    )
                    Text(
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 12.sp,
                        fontFamily = Montserrat,
                        color = Color.White,
                        text = "aminovfap@gmail.com",
                    )
                    Text(
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 14.sp,
                        fontFamily = Montserrat,
                        color = Color.White,
                        text = "был(а) в 4:52",
                    )

                }
            }

        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(brush = Brush.horizontalGradient(colors = listOf(PinkDD, Yellow26)),))
    }
}



@Composable
private fun ProfileDropdownMenu(
    popupState: MutableState<Boolean>,
    onClickEdit: () -> Unit,
    onClickCamera: () -> Unit
) {
    DropdownMenu(
        shape = RoundedCornerShape(15.dp),
        containerColor = Black22,
        offset = DpOffset(y = (-25).dp, x = 0.dp),
        expanded = popupState.value,
        onDismissRequest = {popupState.value = false}
    ) {
        DropdownMenuItem(
            text = {
                Text(text = "Редактировать")
                   },
            onClick = onClickEdit,
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(15.dp),
                    painter = painterResource(R.drawable.editicon),
                    tint = Color.White,
                    contentDescription = null
                ) }
        )
        Box(modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(1.dp)
            .background(Gray4B)
            .align(Alignment.CenterHorizontally))
        DropdownMenuItem(
            text = {
                Text(
                    text = "Изменить фото"
                )
                   },
            onClick = onClickCamera,
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(15.dp),
                    painter = painterResource(R.drawable.camera_icon),
                    tint = Color.White,
                    contentDescription = null
                )
            }
        )

    }
}