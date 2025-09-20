package com.example.tasktreckervicemeat.compose.components.hubs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.tasktreckervicemeat.ui.theme.Gray4B
import com.example.tasktreckervicemeat.ui.theme.Montserrat
import com.example.tasktreckervicemeat.ui.theme.OrangeFF9

@Composable
@Preview
private fun Test() {
    HubListItemComponent(
        hubImageModel = "TEST",
        title = "TEST",
        category = "test",
        amountUsers = 3,
        adminName = "Test",
        adminImageModel = "",
        hubIsOpen = true
    )
}




@Composable
fun HubListItemComponent(
    hubImageModel: String,
    title: String,
    category: String,
    amountUsers: Int,
    adminName: String,
    adminImageModel: String,
    hubIsOpen: Boolean
) {
    val descriptionIsOpen = remember { mutableStateOf(false) }
    val drawable = if (hubIsOpen) {R.drawable.closed_icon} else {R.drawable.open_icon}
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
                        shape = CircleShape,
                        borderWidth = 1.dp
                    )
                    Column(modifier = Modifier.padding(start = 10.dp)) {
                        Row(modifier = Modifier.height(16.dp)) {
                            Text(
                                lineHeight = 16.sp,
                                textAlign = TextAlign.Center,
                                fontFamily = Montserrat,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 16.sp,
                                text = title
                            )

                            Icon(
                                modifier = Modifier.padding(start = 10.dp).size(13.dp),
                                painter = painterResource(drawable),
                                tint = OrangeFF9,
                                contentDescription = null
                            )
                            Icon(
                                modifier = Modifier.padding(start = 10.dp).size(13.dp),
                                painter = painterResource(R.drawable.favorite_icon),
                                tint = Color.White,
                                contentDescription = null
                            )
                        }
                        Text(
                            modifier = Modifier.padding(top = 5.dp),
                            lineHeight = 10.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Light,
                            color = Color.White,
                            fontSize = 10.sp,
                            text = category)
                        Row(
                            modifier = Modifier
                                .height(20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                modifier = Modifier.size(13.dp),
                                painter = painterResource(R.drawable.person_icon),
                                contentDescription = null,
                                tint = Color.White
                            )
                            Text(
                                lineHeight = 13.sp,
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
                            modifier = Modifier.padding(bottom = 40.dp).width(300.dp),
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Light,
                            lineHeight = 15.sp,
                            fontSize = 10.sp,
                            color = Color.White,
                            text = "Lorem Ipsum is simply dummy text of the printingthe printing vffffffff"
                        )

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