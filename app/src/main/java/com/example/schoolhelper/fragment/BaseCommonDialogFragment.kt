package com.example.schoolhelper.fragment

abstract class BaseCommonDialogFragment:BaseDialogFragment() {

        /*findViewById() 是 Activity 和 View 中的一个方法，用于查找视图控件。Fragment 本身并不直接持有视图，
        它的视图是由 onCreateView() 方法创建并返回的。因此，Fragment 必须通过 getView() 或 requireView() 获取其视图，
        然后才能在该视图上调用 findViewById()。
        getView() 在视图尚未创建时会返回 null，因此在某些情况下直接调用 getView() 可能会导致空指针异常。为了避免这种情况，requireView() 会确保视图已经被创建，并且返回一个非空的视图。
        findViewById() 不能直接调用的原因：
        Fragment 并不是 Activity 或 View，它没有直接的视图层次结构。
        Fragment 的视图是在 onCreateView() 中由布局文件生成的，并且视图的创建是一个延迟的过程。在视图创建之前直接调用 findViewById() 是不安全的，因为它会尝试查找不存在的视图，导致崩溃或异常。
        */
//    fun <T :View?> findViewById(@IdRes id:Int):T{
//        return requireView().findViewById(id)
//    }
}