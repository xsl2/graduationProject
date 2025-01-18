plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}
//apply("../common.gradle.kts")
android {
    namespace = "com.example.schoolhelper"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.schoolhelper"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding {
        enable=true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //UI框架，主要是用他的工具类，也可以单独拷贝出来
    //https://qmuiteam.com/android/get-started
    implementation("com.qmuiteam:qmui:2.1.0")
    //权限框架
    //https://github.com/guolindev/PermissionX
    implementation("com.guolindev.permissionx:permissionx:1.7.1")
    //腾讯开源的高性能keyValue存储，用来替代系统的SharedPreferences
    //https://github.com/Tencent/MMKV
    implementation("com.tencent:mmkv-static:1.2.16")

    implementation(project(":super-k"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}