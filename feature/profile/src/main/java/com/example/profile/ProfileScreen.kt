package com.example.profile

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.designsystem.MeatViceDrawComponents
import com.example.designsystem.MeatViceIcons
import com.example.designsystem.MeatViceVectorComponents
import com.example.designsystem.animation.LoadingLottieAnimationComponent
import com.example.designsystem.images.MediaContainerComponent
import com.example.designsystem.images.MediaImage
import com.example.designsystem.images.UserIconComponent
import com.example.designsystem.profile.UserProfileInfoComponent
import com.example.designsystem.screen.ErrorAlert
import com.example.designsystem.screen.TopBar
import com.example.designsystem.theme.Black22
import com.example.designsystem.theme.Gray4B
import com.example.designsystem.theme.Montserrat
import com.example.designsystem.theme.OrangeFF5
import com.example.designsystem.theme.PinkDD
import com.example.designsystem.theme.Yellow26
import com.example.domain.model.ProfileCustomFieldsModel
import com.example.profile.state.ProfileState



@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val mainState = viewModel.mainState.value
    val userState = mainState.userInfoState
    val avatarState = mainState.avatarState

    val newAvatar = remember { mutableStateOf<Bitmap?>(null) }
    val mimeType = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getProfileInfo()
        viewModel.getCurrentUserInfo()
    }

    LaunchedEffect(userState?.userInfo) {
        if (userState!!.userInfo != null) {
            viewModel.getAvatar(
                userId = userState.userInfo.id,
                bucket = "vicemeat"
            )
        }

    }

    LaunchedEffect(newAvatar.value) {
        if (newAvatar.value != null) {
            viewModel.putNewAvatar(
                userState!!.userInfo!!.id,
                newAvatar = newAvatar.value,
                mimeType = mimeType.value
            )
        }
    }
    val mediaContainerIsStart = remember { mutableStateOf(false) }
    ProfileMain(
        viewModel = viewModel,
        changeStartMediaContainer = { newValue -> mediaContainerIsStart.value = newValue },
        startMediaContainer = mediaContainerIsStart.value
    )
}




@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun ProfileMain(
    viewModel: ProfileViewModel,
    changeStartMediaContainer: (Boolean) -> Unit,
    startMediaContainer: Boolean,
) {
    val density = LocalDensity.current
    val systemTopBarHeight = WindowInsets.statusBars.getTop(density = density)
    val topBarHeightDp = with(density) { systemTopBarHeight.toDp() } * 2 + 40.dp

    val popupState = remember { mutableStateOf(false) }
    val mediaContai1qnerIsStart = remember { mutableStateOf(false) }

    val mainState = viewModel.mainState.value
    val userState = mainState.userInfoState
    val avatarState = mainState.avatarState

    val newAvatar = remember { mutableStateOf<Bitmap?>(null) }

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
                            changeStartMediaContainer(true)
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
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TopProfileScreen(topBarHeightDp = topBarHeightDp, state = mainState)
                Text(
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = Montserrat,
                    color = Color.White,
                    text = "Информация"
                )
            }
            items(mainState.profileInfo?: emptyList<ProfileCustomFieldsModel>()) {
                UserProfileInfoComponent(it.fields, title = it.title, editMode = mainState.editMode!!)
            }

        }
        if (!mainState.error.isNullOrBlank()) {
            ErrorAlert(text = mainState.error)
        }

    }

    if (mainState.localImages != null && startMediaContainer) {
        MediaContainerComponent(
            onStart = changeStartMediaContainer,
            onClickBack = { changeStartMediaContainer(false) },
            images = mainState.localImages.map { MediaImage(id = it.id, data = it.data, uri = it.uri, name = it.name, date = it.date) },
            croppedBitmap = { croppedBitmap -> newAvatar.value = croppedBitmap },
            onClickComplete = { }
        )
    }

}


@Composable
private fun TopBarProfileContent(onClickDots: () -> Unit, popupState: MutableState<Boolean>, onClickEdit: () -> Unit, onClickCamera: () -> Unit, ) {
    Icon(
        modifier = Modifier
            .width(30.dp)
            .clickable(enabled = true, onClick = { }),
        painter = painterResource(MeatViceIcons.ArrowBackIcon),
        contentDescription = null,
        tint = Color.White
    )
    Box() {
        ProfileDropdownMenu(popupState = popupState, onClickCamera = onClickCamera, onClickEdit = onClickEdit)
        Icon(
            modifier = Modifier
                .width(30.dp)
                .clickable(enabled = true, onClick = { onClickDots.invoke() }),
            painter = painterResource(MeatViceIcons.Dots),
            contentDescription = null,
            tint = Color.White
        )

    }

}

@Composable
private fun TopProfileScreen(topBarHeightDp: Dp, state: ProfileState) {

    val avatar = state.avatarState?.avatar?.asImageBitmap()
    val avatarIsLoading = state.avatarState?.isLoading
    val avatarIsError = state.avatarState?.error

    val username = state.userInfoState?.userInfo?.username?: "..."
    val email = state.userInfoState?.userInfo?.email?: "..."

    var animateCharMColor = animateColorAsState(
        targetValue = if (!avatarIsError.isNullOrBlank()) OrangeFF5 else Black22
    )
    var animateLineColor = animateColorAsState(
        targetValue = if (!avatarIsError.isNullOrBlank()) OrangeFF5 else PinkDD
    )
    Column {
        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(MeatViceDrawComponents.BackgroundProfile),
                    contentScale = ContentScale.FillHeight
                )
                .fillMaxWidth()
                .height(topBarHeightDp + 150.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Icon(
                painter = painterResource(MeatViceVectorComponents.M),
                contentDescription = null,
                tint = animateCharMColor.value
            )
            Row(
                modifier = Modifier
                    .padding(25.dp)
            ) {
                UserIconComponent(
                    size = 100.dp,
                    bitmap = avatar,
                    shape = CircleShape,
                    borderWidth = 2.dp,
                    isLoading = avatarIsLoading!!,
                    isError = !avatarIsError.isNullOrBlank(),
                    loadingComponent = { LoadingLottieAnimationComponent() }
                )
                Column(modifier = Modifier.padding(start = 20.dp)) {
                    Text(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = Montserrat,
                        color = Color.White,
                        text = username
                    )
                    Text(
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 12.sp,
                        fontFamily = Montserrat,
                        color = Color.White,
                        text = email
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
            .background(
                brush = Brush.horizontalGradient(
                    colors =
                        listOf(animateLineColor.value, Yellow26)),
                )
        )
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
                    painter = painterResource(MeatViceIcons.Edit),
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
                    painter = painterResource(MeatViceIcons.Camera),
                    tint = Color.White,
                    contentDescription = null
                )
            }
        )

    }
}