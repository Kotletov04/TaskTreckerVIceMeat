package com.example.hubs

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.designsystem.MeatViceDrawComponents
import com.example.designsystem.MeatViceIcons
import com.example.designsystem.hubs.CreateHubComponent
import com.example.designsystem.hubs.HubListItemComponent
import com.example.designsystem.hubs.HubsTopBarComponent
import com.example.designsystem.images.UserIconComponent
import com.example.designsystem.screen.CustomButton
import com.example.designsystem.screen.TopBar
import com.example.designsystem.theme.Montserrat
import com.example.domain.model.HubModel
import com.example.domain.model.UserModel
import kotlinx.coroutines.launch





@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HubsScreen(
    viewModel: HubsViewModel = hiltViewModel(),
    onClickProfile: () -> Unit,
    onClickLogout: () -> Unit
) {

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
                        icon = { Icon(painter = painterResource(MeatViceIcons.Close), contentDescription = null, tint = Color.White) },
                        badge = { Text("20") }, // Placeholder
                        onClick = { /* Handle click */ }
                    )
                    NavigationDrawerItem(
                        label = { Text("Help and feedback") },
                        selected = false,
                        icon = { Icon(painter = painterResource(MeatViceIcons.Close), contentDescription = null, tint = Color.White) },
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
                    painter = painterResource(MeatViceDrawComponents.ColorCirclesBackground),
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
            painter = painterResource(MeatViceIcons.More),
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
            onClickProfile()
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
            shape = CircleShape,
            borderWidth = 0.dp,
            onClickEnabled = true,
            onClick = {onClickProfile.invoke()}
        )
    }
}
