package fr.xgouchet.gradle

@Suppress("unused")
object Dependencies {

    object Versions {

        const val AndroidPlugin = "3.2.0"

        const val AndroidSupportLibs = "27.1.1"
        const val AndroidX = "1.0.0"
        const val AndroidXArch = "2.0.0-rc01"
        const val AndroidMaven = "2.1"
        const val MultiDex = "2.0.0"
        const val ConstraintLayout = "1.1.2"
        const val MaterialComponents = "1.0.0"
        const val NavigationArchComponent = "1.0.0-alpha06"

        const val OssLicensesPlugin = "0.9.2"
        const val OssLicensesLibrary = "16.0.0"
        const val DataBindingCompiler = "2.3.3"

        const val BuildTimeTracker = "0.11.0"
        const val DependencyVersion = "0.20.0"
        const val Detekt = "1.0.0.RC6-3"
        const val Kotlin = "1.2.70"
        const val RxJava = "2.2.2"
        const val RxAndroid = "2.1.0"
        const val AboutPage = "1.2.4"


        const val Leakcanary = "1.5.4"
        const val Timber = "4.6.0"
        const val Stetho = "1.5.0"
        const val AXml = "v1.0.1"
    }

    object Libraries {

        const val AndroidSupportDesign = "com.android.support:design:${Versions.AndroidSupportLibs}"

        const val AndroidConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.ConstraintLayout}"
        const val AndroidRecyclerView = "androidx.recyclerview:recyclerview:${Versions.AndroidX}"
        const val AndroidAppCompat = "androidx.appcompat:appcompat:${Versions.AndroidX}"
        const val AndroidCardView = "androidx.cardview:cardview:${Versions.AndroidX}"

        @JvmField val AndroidSupport = arrayOf(
                "androidx.annotation:annotation:${Versions.AndroidX}",
                "androidx.appcompat:appcompat:${Versions.AndroidX}",
                "androidx.core:core-ktx:${Versions.AndroidX}"
        )
        const val MaterialComponents = "com.google.android.material:material:${Versions.MaterialComponents}"

        @JvmField val AndroidArchComponents = arrayOf(
                "androidx.lifecycle:lifecycle-extensions:${Versions.AndroidXArch}",
                "androidx.room:room-runtime:${Versions.AndroidXArch}"
        )

        @JvmField val Navigation = arrayOf(
                "android.arch.navigation:navigation-fragment-ktx:${Versions.NavigationArchComponent}",
                "android.arch.navigation:navigation-ui-ktx:${Versions.NavigationArchComponent}"
        )

        const val MultiDex = "androidx.multidex:multidex:${Versions.MultiDex}"

        @JvmField
        val Kotlin = arrayOf(
                "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.Kotlin}",
                "org.jetbrains.kotlin:kotlin-reflect:${Versions.Kotlin}"
        )
        @JvmField
        val Rx = arrayOf(
                "io.reactivex.rxjava2:rxjava:${Versions.RxJava}",
                "io.reactivex.rxjava2:rxandroid:${Versions.RxAndroid}"
        )

        const val OssLicences = "com.google.android.gms:play-services-oss-licenses:${Versions.OssLicensesLibrary}"
        const val AboutPage = "com.github.medyo:android-about-page:${Versions.AboutPage}"
        const val AXml = "com.github.xgouchet:AXML:${Versions.AXml}"

        const val Timber = "com.jakewharton.timber:timber:${Versions.Timber}"
    }

    object ClassPaths {
        const val AndroidPlugin = "com.android.tools.build:gradle:${Versions.AndroidPlugin}"
        const val OssLicencesPlugin = "com.google.gms:oss-licenses:${Versions.OssLicensesPlugin}"
        const val KotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin}"
        const val AndroidMavenPlugin = "com.github.dcendents:android-maven-gradle-plugin:${Versions.AndroidMaven}"
    }

    object PluginNamespaces {
        const val Detetk = "io.gitlab.arturbosch"
        const val DependencyVersion = "com.github.ben-manes"
        const val Kotlin = "org.jetbrains.kotlin"
    }

    object Repositories {
        const val Fabric = "https://maven.fabric.io/public"
        const val Jitpack = "https://jitpack.io"
        const val Gradle = "https://plugins.gradle.org/m2/"
        const val Google = "https://maven.google.com"
    }

    object Processors {
        const val DataBinding = "com.android.databinding:compiler:${Versions.DataBindingCompiler}"

        @JvmField val AnrdoidXArch = arrayOf(
                "androidx.lifecycle:lifecycle-compiler:${Versions.AndroidXArch}",
                "androidx.room:room-compiler:${Versions.AndroidXArch}"
        )
    }
}
