毕设开始

# 1.31进行思路总结-对应Git-10 实现首页TabBar框架

已经实现安装应用后权限申请，启动界面显示，接着是引导界面，跳过注册进入主界面功能。

基本框架搭建完成，但是后端还未构思，Android中是MVVM架构

![image-20250131141517287](https://cdn.jsdelivr.net/gh/xsl2/images/img/202501311416446.png)

# superui

superui为项目通用工具类，与具体项目无关，只是存储一些可能用到的工具实现，工具类用object实现，objext表示单列模式的对象，它是一个不可实例化的类，即只有一个实例存在，不需要使用 `new` 关键字来创建。这个实例在整个应用中唯一。

### **`object` 的工作方式**：

在 Kotlin 中，`object` 声明不仅定义了一个类，还自动创建了该类的单例实例。`object` 是 **线程安全的**，并且只会在第一次访问时进行初始化。其主要的好处是 **简洁性**，尤其是在不需要传递上下文或参数时非常方便。

# schoolhelper

应用主体

## activity

activity为所有Activity父类，继承AppCompatActivity。

`onPostCreate()` 是 `Activity` 生命周期的一部分。它在 `onCreate()` 完成后被调用，但它的调用顺序比 `onResume()` 要晚一些。里面调用了 `initViews()`、`initDatum()` 和 `initListeners()`，确保在 Activity 创建后立即进行视图、数据和监听器的初始化。

#### BaseCommonActivity

继承BaseActivity，实现启动界面，启动界面并关闭当前界面方法

#### BaseViewModelActivity

继承BaseLogicActivity，实现binding绑定，使用官方ViewBinding技术，在项目中启用 `ViewBinding`，首先需要在 `build.gradle` 文件中启用它android {    ...    viewBinding {        enabled = true    } }

在这里将Binding封装在类中，在创建Activity继承BaseViewModelActivity时确定继承的ViewBinding的子类绑定给binding，binding在通过反射调用ActivityxxxBinding的函数inflate（layoutInflater）进行ViewBinding 绑定布局。

setContentView(binding.root)开启内容视图。

## cmoponent/splash

启动界面，应用启动后进入该界面，该界面是过渡界面，设定1s延迟，在该界面申请权限。接着进入主界面或引导界面

```
private fun prepareNext() {
    Log.d(TAG, "prepareNext: ")
    if(PreferenceUtil.isShowGuide())
    {
        startActivityAfterFinishThis(GuideActivity::class.java)
        return
    }
    startActivityAfterFinishThis(MainActivity::class.java)
}
```

## component/guide

引导界面，有登录/注册，立即体验按钮，点击立即体验进入MainActivity界面，否则调用注册登录界面。

## ViewModel

`ViewModel` 中通常会使用 `LiveData` 来包装数据。`LiveData` 允许 `Activity` 或 `Fragment` 订阅并观察数据，且只有当组件处于活跃状态（如 `onStart()` 和 `onResume()`）时，`LiveData` 才会向观察者（如 `Activity` 或 `Fragment`）发送数据更新。

`LiveData` 会自动管理数据的生命周期，当 `Activity` 或 `Fragment` 被销毁时，`LiveData` 会停止发送数据更新，避免了不必要的内存泄漏或访问空的视图。