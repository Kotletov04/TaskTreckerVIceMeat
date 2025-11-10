// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.firebase) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}
tasks.register("CreateFile") {
    description = "Creates a sample text file."
    doLast {
        val outputFile = project.layout.buildDirectory.file("generated/myFileeeee.txt").get().asFile
        outputFile.parentFile.mkdirs() // Ensure the parent directories exist
        outputFile.writeText("This is the content of my generated file.")
        println("File created: ${outputFile.absolutePath}")
    }
}
tasks.register("printTextFile") {
    description = "Читает содержимое файла text.txt и выводит его в консоль"
    group = "utility"

    doLast {
        val file = file("build/generated/myFileeeee.txt") // можно заменить на свой путь
        println(file.exists())

    }
}
tasks.register("checkGeneratedDir") {
    description = "Проверяет, существует ли директория build/generated"
    group = "utility"

    doLast {
        val dir = file("build/generated") // путь к директории
        if (dir.exists() && dir.isDirectory) {
            println("true ${dir.absolutePath}")
        } else {
            println("false ${dir.absolutePath}")
        }
    }
}
