
buildscript {
    repositories {
        mavenCentral()
        google()
//        jcenter()
    }

    dependencies {
        val nav_version = "2.7.7"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")

    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id ("androidx.navigation.safeargs.kotlin") version "2.7.7" apply false
    id ("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}