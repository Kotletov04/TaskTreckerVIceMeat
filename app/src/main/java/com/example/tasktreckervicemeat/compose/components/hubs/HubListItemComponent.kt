package com.example.tasktreckervicemeat.compose.components.hubs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktreckervicemeat.R
import com.example.tasktreckervicemeat.compose.components.images.UserIconComponent
import com.example.tasktreckervicemeat.ui.theme.Black22
import com.example.tasktreckervicemeat.ui.theme.Gray31
import com.example.tasktreckervicemeat.ui.theme.Gray4B
import com.example.tasktreckervicemeat.ui.theme.Montserrat

@Composable
@Preview
private fun Test() {
    HubListItemComponent(
        hubImageModel = "TEST",
        title = "TEST",
        category = "test",
        amountUsers = 3,
        adminName = "Test",
        adminImageModel = ""
    )
}




@Composable
fun HubListItemComponent(
    hubImageModel: String,
    title: String,
    category: String,
    amountUsers: Int,
    adminName: String,
    adminImageModel: String
) {
    val descriptionIsOpen = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .animateContentSize()
            .border(width = 1.dp, color = Gray4B, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(color = Black22)
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Row(
            modifier = Modifier.align(Alignment.BottomEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserIconComponent(size = 20.dp, model = adminImageModel, borderWidth = 0.dp)
            Column(modifier = Modifier.padding(start = 3.dp)) {
                Text(
                    lineHeight = 10.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 10.sp,
                    text = "Admin"
                )
                Text(
                    lineHeight = 10.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Light,
                    color = Color.White,
                    fontSize = 10.sp,
                    text = adminName
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row() {
                    UserIconComponent(
                        size = 60.dp,
                        model = hubImageModel,
                        shape = RoundedCornerShape(10.dp),
                        borderWidth = 1.dp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Row {
                            Text(
                                textAlign = TextAlign.Center,
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 16.sp,
                                text = title
                            )
                        }
                        Text(
                            lineHeight = 10.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Light,
                            color = Color.White,
                            fontSize = 10.sp,
                            text = category)
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(
                            modifier = Modifier
                                .height(20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                modifier = Modifier.size(10.dp),
                                painter = painterResource(R.drawable.person_icon),
                                contentDescription = null,
                                tint = Color.White
                            )
                            Text(
                                lineHeight = 10.sp,
                                modifier = Modifier.padding(start = 5.dp),
                                fontSize = 10.sp,
                                color = Color.White,
                                text = amountUsers.toString()
                            )
                        }
                    }

                }
                AnimatedVisibility(visible = descriptionIsOpen.value) {
                    Column {
                        Text(
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier,
                            fontSize = 10.sp,
                            color = Color.White,
                            text = "Description:"
                        )
                        Text(
                            modifier = Modifier.padding().width(300.dp),
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Light,
                            lineHeight = 15.sp,
                            fontSize = 10.sp,
                            color = Color.White,
                            text = "Lorem Ipsum is simply dummy text of the printingthe printing vffffffff"
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                    }




                }

            }
            val rotate = animateFloatAsState(
                targetValue = if (descriptionIsOpen.value) 0f else -90f,
                animationSpec = tween(
                    durationMillis = 300,
                ), label = "rotation"
            )
            Box {
                Icon(
                    painter = painterResource(R.drawable.visibleicon),
                    modifier = Modifier.align(Alignment.TopEnd)
                        .graphicsLayer {
                            rotationZ = rotate.value
                        }
                        .clickable{ descriptionIsOpen.value = !descriptionIsOpen.value },
                    tint = Color.White,
                    contentDescription = null
                )
            }

        }
    }

}