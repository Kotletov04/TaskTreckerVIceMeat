package com.example.tasktreckervicemeat.compose.screens.hubs

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.domain.model.HubModel
import com.example.domain.model.ImageModel
import com.example.domain.model.StageModel
import com.example.tasktreckervicemeat.R
import com.example.tasktreckervicemeat.compose.components.hubs.CreateHubComponent
import com.example.tasktreckervicemeat.compose.components.hubs.HubListItemComponent
import com.example.tasktreckervicemeat.compose.components.hubs.HubsTopBarComponent
import com.example.tasktreckervicemeat.compose.components.images.MediaContainerComponent
import com.example.tasktreckervicemeat.compose.components.screen.BottomBar
import com.example.tasktreckervicemeat.compose.components.screen.TopBar
import com.example.tasktreckervicemeat.ui.theme.Black22

@Preview
@Composable
private fun HubsScreenTest() {

}


@RequiresApi(Build.VERSION_CODES.S)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HubsScreen(viewModel: HubsViewModel) {
    val createComponentIsActive = remember { mutableStateOf(false) }
    val title = remember { mutableStateOf("") }
    val lazyColumnState = rememberLazyListState()
    val density = LocalDensity.current
    val systemTopBarHeight = WindowInsets.statusBars.getTop(density = density)
    val heightTopDp = with(density) { systemTopBarHeight.toDp() } * 2 + 40.dp
    val systemBottomHeight = WindowInsets.navigationBars.getBottom(density = density)
    val heightBottomDp = with(density) { systemBottomHeight.toDp() } * 2 + 15.dp
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.hubs_background),
                contentScale = ContentScale.FillBounds
            ),
        containerColor = Color.Transparent,
        bottomBar = {
            BottomBar(height = heightBottomDp, onClickCreate = {
                viewModel.getImages()
                createComponentIsActive.value = !createComponentIsActive.value
            })
        },
        topBar = {
            TopBar(height = heightTopDp)
        }
    ) {
        LazyColumn(
            state = lazyColumnState,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(heightTopDp))
                HubsTopBarComponent()
            }
            items(
                count = 20,
                key = { index -> index }
            ) { index ->
                HubListItemComponent(
                    hubImageModel = "",
                    title = "Name",
                    category = "Category",
                    amountUsers = 3,
                    adminName = "Kotletov",
                    adminImageModel = ""
                )
            }
            item {
                Spacer(modifier = Modifier.height(heightBottomDp))

            }
        }
    }
}