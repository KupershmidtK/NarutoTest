buildscript {
    val agp_version by extra("8.1.3")
}
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "7.4.0" apply false
    id ("com.google.dagger.hilt.android") version "2.45" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10" apply false
}