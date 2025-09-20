package com.example.tasktreckervicemeat.compose.screens.hubs

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.HubModel
import com.example.domain.model.UserModel
import com.example.tasktreckervicemeat.R
import com.example.tasktreckervicemeat.compose.components.hubs.CreateHubComponent
import com.example.tasktreckervicemeat.compose.components.screen.CustomButton
import com.example.tasktreckervicemeat.compose.components.hubs.HubListItemComponent
import com.example.tasktreckervicemeat.compose.components.hubs.HubsTopBarComponent
import com.example.tasktreckervicemeat.compose.components.images.UserIconComponent
import com.example.tasktreckervicemeat.compose.components.screen.TopBar
import com.example.tasktreckervicemeat.ui.theme.Montserrat
import kotlinx.coroutines.launch

@Preview
@Composable
private fun HubsScreenTest() {

}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HubsScreen(viewModel: HubsViewModel, onClickProfile: () -> Unit, onClickLogout: () -> Unit) {
    val createComponentIsActive = remember { mutableStateOf(false) }
    val lazyColumnState = rememberLazyListState()
    val density = LocalDensity.current
    val systemTopBarHeight = WindowInsets.statusBars.getTop(density = density)
    val heightTopDp = with(density) { systemTopBarHeight.toDp() } * 2 + 40.dp
    val systemBottomHeight = WindowInsets.navigationBars.getBottom(density = density)
    val heightBottomDp = with(density) { systemBottomHeight.toDp() } * 2 + 15.dp
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    var createHubComponentIsActive by remember { mutableStateOf(false) }
    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        viewModel.getCurrentUser()
        viewModel.getAllHubs()
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    NavigationDrawerItem(
                        label = { Text("Settings") },
                        selected = false,
                        icon = { Icon(painter = painterResource(R.drawable.close_icon), contentDescription = null, tint = Color.White) },
                        badge = { Text("20") }, // Placeholder
                        onClick = { /* Handle click */ }
                    )
                    NavigationDrawerItem(
                        label = { Text("Help and feedback") },
                        selected = false,
                        icon = { Icon(painter = painterResource(R.drawable.close_icon), contentDescription = null, tint = Color.White) },
                        onClick = { /* Handle click */ },
                    )
                    CustomButton(
                        onClick = {onClickLogout.invoke()},
                        text = "Выйти"
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
       /*

        CONTENT HUBS

        */

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(R.drawable.hubs_background),
                    contentScale = ContentScale.FillBounds
                ),
            containerColor = Color.Transparent,
            topBar = {
                TopBar(
                    height = heightTopDp,
                    content = {
                        TopBarContent(
                            user = state.currentUser,
                            onClickProfile = onClickProfile,
                            onClickShowModal = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            }
                        )
                    }
                )
            }
        ) {
            LazyColumn(
                state = lazyColumnState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp),
                contentPadding = PaddingValues(
                    top = heightTopDp,
                    bottom = heightBottomDp
                )
            ) {
                item {
                    val animatePadding by animateDpAsState(targetValue = 0.dp)
                    HubsTopBarComponent(onClickCreate = {createHubComponentIsActive = !createHubComponentIsActive})
                    AnimatedVisibility(
                        enter = fadeIn() + expandIn(expandFrom = Alignment.BottomCenter),
                        exit = fadeOut() + shrinkOut(shrinkTowards = Alignment.TopCenter),
                        modifier = Modifier.padding(top = if (createHubComponentIsActive) 5.dp else animatePadding),
                        visible = createHubComponentIsActive
                    ) {
                        CreateHubComponent(
                            onClickComplete = {
                                createHubComponentIsActive = false
                                viewModel.createNewHub()
                                viewModel.cleanCreateHubFields()
                                              },
                            onChangeSwitch = {
                                viewModel.hubIsOpen.value = !viewModel.hubIsOpen.value
                                             },
                            onTitleValueChange = { newTitle -> viewModel.hubTitle.value = newTitle },
                            titleValue = viewModel.hubTitle.value,
                            switchIsActive = viewModel.hubIsOpen.value

                        )
                    }
                }

                items(state.hubs?: emptyList<HubModel>()) { hub ->
                    HubListItemComponent(
                        hubImageModel = hub.hubImage.toString(),
                        title = hub.title,
                        category = hub.category?: "Без категории",
                        amountUsers = hub.users?.size ?: 123,
                        adminName = hub.adminName.toString(),
                        adminImageModel = "",
                        hubIsOpen = hub.isOpen
                    )
                }

            }
        }

    }
}


@Composable
private fun TopBarContent(user: UserModel?, onClickShowModal: () -> Unit, onClickProfile: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .width(30.dp)
                .clickable(enabled = true, onClick = { onClickShowModal.invoke() }),
            painter = painterResource(R.drawable.more_icon),
            contentDescription = null,
            tint = Color.White
        )
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = "Хабы",
            fontWeight = FontWeight.ExtraLight,
            fontSize = 20.sp,
            fontFamily = Montserrat,
            color = Color.White
        )
    }
    Row(
        modifier = Modifier.clickable {
            onClickProfile.invoke()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(end = 20.dp),
            text = user?.username?: "null",
            fontWeight = FontWeight.ExtraLight,
            fontSize = 10.sp,
            fontFamily = Montserrat,
            color = Color.White
        )
        UserIconComponent(
            size = 30.dp,
            isActive = false,
            model = "",
            shape = CircleShape,
            borderWidth = 0.dp,
            onClickEnabled = true,
            onClick = {onClickProfile.invoke()}
        )
    }
}
