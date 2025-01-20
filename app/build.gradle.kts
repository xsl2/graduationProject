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
    buildFeatures {
        buildConfig = true
    }
    // 配置不同的环境
    flavorDimensions("default")  // 添加 flavor dimension

    // 配置不同的环境
    productFlavors {
        // 本地开发环境
        create("local") {
            buildConfigField("String", "ENDPOINT", "\"http://192.168.209.1:8080/\"")
            buildConfigField("String", "RESOURCE_ENDPOINT", "\"http://cours e-music-dev.ixuea.com/%s\"")
            dimension = "default"
        }

        // 开发环境
        create("dev") {
            buildConfigField("String", "ENDPOINT", "\"https://news-api-course.ixuea.com/\"")
            buildConfigField("String", "RESOURCE_ENDPOINT", "\"http://course-music-dev.ixuea.com/%s\"")
            dimension = "default"
        }

        // 正式环境
        create("prod") {
            buildConfigField("String", "ENDPOINT", "\"https://news-api-prod-course.ixuea.com/\"")
            buildConfigField("String", "RESOURCE_ENDPOINT", "\"http://course-music.ixuea.com/%s\"")
            dimension = "default"
        }
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

    //圆形指示器
    //https://github.com/ongakuer/CircleIndicator
    implementation("me.relex:circleindicator:2.1.6")

    //region 请求网络相关
    //提示：region这种语法是最新的，推荐使用这种，也更容易阅读，不建议在同一个文件同时使用
    //因为可能会显示出错
    //okhttp
    //https://github.com/square/okhttp
    implementation("com.squareup.okhttp3:okhttp:4.10.0")

    //用来打印okhttp请求日志
    //当然也可以自定义
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    //retrofit
    //https://github.com/square/retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    //使用gson解析json
    //https://github.com/google/gson
    implementation ("com.google.code.gson:gson:2.9.1")

    //适配retrofit使用gson解析
    //版本要和retrofit一样
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //endregion
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    implementation(libs.androidx.room.common)
    //apache common lang3工具包
    //提供了StringUtils等这样的类
    //http://commons.apache.org/proper/commons-lang/
    implementation ("org.apache.commons:commons-lang3:3.8")



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}