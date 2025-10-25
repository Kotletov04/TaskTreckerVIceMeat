package com.example.data.repository

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.os.Build
import android.provider.ContactsContract
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.amazonaws.ClientConfiguration
import com.amazonaws.Protocol
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.Region
import com.example.domain.model.ImageModel
import com.example.domain.repository.MediaRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import javax.inject.Inject


class MediaRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val amazonS3Client: AmazonS3Client
): MediaRepository {

    override suspend fun getImages(): List<ImageModel> {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED
        )
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
        val arrayList = arrayListOf<ImageModel>()
        context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val dateColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val date = cursor.getString(dateColumn)
                val uri =
                    ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                arrayList.add(
                    ImageModel(
                        id = id,
                        name = name,
                        data = "data",
                        date = date,
                        uri = uri.toString()
                    )
                )
            }
        }

        return arrayList
    }

    override suspend fun getAvatarImage(bucket: String, userId: String): ByteArray {
        return withContext(Dispatchers.IO) {
            amazonS3Client.getObject(GetObjectRequest(bucket, "$userId.jpg")).objectContent.use { inputStream ->
                val outputStream = ByteArrayOutputStream()
                inputStream.copyTo(outputStream)
                outputStream.toByteArray()
            }
        }
    }

    override suspend fun putAvatarImage(bucket: String, userId: String, avatar: ByteArray, mimeType: String) {
        withContext(Dispatchers.IO) {
            val inputStream = ByteArrayInputStream(avatar)
            val metadata = ObjectMetadata().apply {
                contentLength = avatar.size.toLong()
                contentType = mimeType
            }
            amazonS3Client.putObject(PutObjectRequest(bucket, "$userId.jpg", inputStream, metadata))
        }
    }


}
