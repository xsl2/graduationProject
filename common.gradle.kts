//通用配置文件
//例如：对于我们应用大部分module都要引用appcompat组件
//还有配置compileOptions选项，像这些就可以单独放到一个文件
//然后再需要的build.gradle文件中引入
//结构还是要和正常build.gradle文件保存一致，例如：compileOptions应用在android方法下面
//那这个文件这边也要保持这种结构
//提示：虽然这些gradle文件有很多种写法，但我们推荐还是大体和官方推荐的结构保持一致
//避免逻辑过于复杂，或者以后不兼容了迁移麻烦等情况


//未启用，尝试过不成功
android {
    //编译版本，旧版项目是compileSdkVersion
    //有些旧版本，还需要配置buildToolsVersion
    compileSdk = 34

    defaultConfig {
        //最小版本，低于该版本的手机无法运行，和安装
        //旧版项目是minSdkVersion
        minSdk = 24

        //目标版本，可以理解为是基于那个版本开发
        //例如：基于31，那么就可以使用31版本里面的新特性
        //如果基于20，那就只能使用20里面的特性，但最终也可以安装到31版本的手机
        //旧版项目是targetSdkVersion
//        targetSdk rootProject.ext.targetSdk
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}