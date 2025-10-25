package com.example.designsystem.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.R
import com.example.designsystem.theme.Gray31
import com.example.designsystem.theme.Montserrat
import com.example.designsystem.theme.OrangeFF9

@Preview
@Composable
private fun TestUserProfileInfoComponent() {
    /*UserProfileInfoComponent()*/
}



@Composable
fun UserProfileInfoComponent(infoMap: Map<String, String>, title: String, editMode: Boolean = false) {
    Box(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
            .fillMaxWidth()
            .border(width = 1.dp, color = Gray31, shape = RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {
        FlowColumn(modifier = Modifier.fillMaxWidth().padding(start = 25.dp, end = 25.dp, bottom = 10.dp), horizontalArrangement = Arrangement.End) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp,
                text = title
            )
            infoMap.keys.forEach { key ->
                Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Row(verticalAlignment = Alignment.CenterVertically, ) {
                        if(editMode) {
                            Icon(
                                modifier = Modifier.padding(end = 5.dp),
                                painter = painterResource(R.drawable.close_icon),
                                tint = OrangeFF9,
                                contentDescription = null)
                        }

                        Text(
                            lineHeight = 14.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 14.sp,
                            text = "${key}:"
                        )
                    }
                    Text(
                        lineHeight = 14.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 14.sp,
                        text = infoMap[key].toString()
                    )
                }
            }
            if(editMode) {
                Icon(
                    painter = painterResource(R.drawable.iconadd),
                    tint = Color.White,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(top = 5.dp, bottom = 5.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

        }

    }
}