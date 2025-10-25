package com.example.convention_meat_vice.project

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ManagedVirtualDevice
import org.gradle.kotlin.dsl.*


/*

Функция нужна для автотестов на эмуляторах

*/

internal fun configureDevicesManager(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {

    // Тестовые телефоны
    val pixel4 = DeviceConfig(deviceName = "Pixel 4", api = 30, systemImageSource = "aosp-atd")
    val pixel6 = DeviceConfig(deviceName = "Pixel 6", api = 31, systemImageSource = "aosp")
    val pixelC = DeviceConfig(deviceName = "Pixel C", api = 30, systemImageSource = "aosp-atd")

    val _allDevices = listOf(
        pixel4,
        pixel6,
        pixelC
    )
    val ciDevices = listOf(
        pixel4,
        pixelC
    )

    commonExtension.testOptions {
        managedDevices {
            allDevices {
                _allDevices.forEach { deviceConfig ->
                    // Аналогичен create(), но не вызывает ошибку, если устройство уже создано
                    maybeCreate(deviceConfig.taskName, ManagedVirtualDevice::class.java).apply {
                        device = deviceConfig.deviceName
                        apiLevel = deviceConfig.api
                        systemImageSource = deviceConfig.systemImageSource
                    }
                }
            }
            groups {
                maybeCreate("ci").apply {
                    ciDevices.forEach { deviceConfig ->
                        targetDevices.add(allDevices[deviceConfig.taskName])
                    }
                }
            }


        }

    }



}


private data class DeviceConfig(
    val api: Int,
    val deviceName: String,
    val systemImageSource: String
) {
    val taskName = buildString {
        append(deviceName.lowercase().replace(" ", ""))
        append("api")
        append(api.toString())
        append(systemImageSource.replace("-", ""))
    }
}