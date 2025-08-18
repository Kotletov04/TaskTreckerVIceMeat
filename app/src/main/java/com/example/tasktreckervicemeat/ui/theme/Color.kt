package com.example.tasktreckervicemeat.ui.theme

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.LottieCompositionSpec

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Black22 = Color(0xFF222222)
val Black19 = Color(0xFF191919)
val Gray31 = Color(0xFF313131)

val BlueNeon = Color(0xFF34FEF4)
val Gray4B = Color(0xFF4B4B4B)
val OrangeFF = Color(0xFFFF5C1C)
val PinkFF = Color(0xFFFF5A9A)
val OrangeFF9 = Color(0xFFFF976E)

val PinkDD = Color(0xFFFF00DD)
val Yellow26 = Color(0xFFFFF826)


class TEST(): ContentProvider() {
    override fun onCreate(): Boolean {
        TODO("Not yet implemented")
    }

    override fun query(
        p0: Uri,
        p1: Array<out String?>?,
        p2: String?,
        p3: Array<out String?>?,
        p4: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(
        p0: Uri,
        p1: String?,
        p2: Array<out String?>?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        p0: Uri,
        p1: ContentValues?,
        p2: String?,
        p3: Array<out String?>?
    ): Int {
        TODO("Not yet implemented")
    }

}