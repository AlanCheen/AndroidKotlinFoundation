package me.yifeiyuan.foundation

import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by 程序亦非猿 on 2020/12/7.
 */
object Threads {

    /**
     * 是否是主线程
     */
    @JvmStatic
    fun isMainThread(): Boolean {
        return Looper.myLooper() == Looper.getMainLooper()
    }

    @JvmStatic
    fun getSingleThreadExecutor():Executor{
        return Executors.newSingleThreadExecutor()
    }

}