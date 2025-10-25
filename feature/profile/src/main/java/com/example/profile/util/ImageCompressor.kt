package com.example.profile.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

class ImageCompressor(

) {
    suspend fun compressImageByUri(
        contentUri: Uri,
        // Порог сжатия
        compressionThreshold: Long,
        context: Context
    ): ByteArray? {
        // Запускаем корутину - IO база для файлов
        return withContext(Dispatchers.IO) {
            // Определяем медиа тип файла, например: image/png
            val mimeType = context.contentResolver.getType(contentUri)
            // Читаем байты, получаем массив байтов
            val inputBytes =
                context.contentResolver.openInputStream(contentUri)?.use { inputStream ->
                    inputStream.readBytes()
                } ?: return@withContext null

            ensureActive()

            withContext(Dispatchers.Default) {
                // Получаем изображение по байтам
                val bitmap = BitmapFactory.decodeByteArray(inputBytes, 0, inputBytes.size)

                ensureActive()

                // Соотносим тип файла с форматом компрессии
                val compressFormat = when (mimeType) {
                    "image/png" -> Bitmap.CompressFormat.PNG
                    "image/jpeg" -> Bitmap.CompressFormat.JPEG
                    "image/webp" -> if (Build.VERSION.SDK_INT >= 30) {
                        Bitmap.CompressFormat.WEBP_LOSSLESS
                    } else {
                        Bitmap.CompressFormat.WEBP
                    }

                    else -> Bitmap.CompressFormat.JPEG
                }


                // compressFormat != Bitmap.CompressFormat.PNG - PNG уникален, так как например игнорит quality

                var outputBytes: ByteArray
                var quality = 100

                do {
                    ByteArrayOutputStream().use { outputStream ->
                        bitmap.compress(compressFormat, quality, outputStream)
                        outputBytes = outputStream.toByteArray()
                        quality = quality - (quality * 0.1).roundToInt()
                    }
                } while (isActive && outputBytes.size > compressionThreshold && quality > 5 && compressFormat != Bitmap.CompressFormat.PNG)


                // Возврат всего нахой
                outputBytes
            }
        }


    }

    suspend fun compressImageByBitmap(
        bitmap: Bitmap,
        compressionThreshold: Long,
        mimeType: String? = "image/jpeg"
    ): ByteArray? {

        return withContext(Dispatchers.Default) {
            // Получаем изображение по байтам

            ensureActive()

            // Соотносим тип файла с форматом компрессии
            val compressFormat = when (mimeType) {
                "image/png" -> Bitmap.CompressFormat.PNG
                "image/jpeg" -> Bitmap.CompressFormat.JPEG
                "image/webp" -> if (Build.VERSION.SDK_INT >= 30) {
                    Bitmap.CompressFormat.WEBP_LOSSLESS
                } else {
                    Bitmap.CompressFormat.WEBP
                }

                else -> Bitmap.CompressFormat.JPEG
            }


            // compressFormat != Bitmap.CompressFormat.PNG - PNG уникален, так как например игнорит quality
            var outputBytes: ByteArray
            var quality = 100

            do {
                ByteArrayOutputStream().use { outputStream ->
                    bitmap.compress(compressFormat, quality, outputStream)
                    outputBytes = outputStream.toByteArray()
                    quality = quality - (quality * 0.1).roundToInt()
                }
            } while (isActive && outputBytes.size > compressionThreshold && quality > 5 && compressFormat != Bitmap.CompressFormat.PNG)


            // Возврат всего нахой
            outputBytes
        }


    }

}