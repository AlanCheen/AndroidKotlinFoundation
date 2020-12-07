package me.yifeiyuan.foundation

import android.app.ActivityManager
import android.content.Context
import android.os.Process
import android.text.TextUtils
import java.io.BufferedReader
import java.io.FileReader

/**
 * Created by 程序亦非猿 on 2020/12/7.
 */
object Processes {

    /**
     * 判断进程是否是主进程
     */
    @JvmStatic
    fun isMainProcess(context: Context): Boolean {
        try {
            val packageName: String = context.packageName
            val processName: String? = getProcessName(context)
            return !(!TextUtils.isEmpty(packageName) && !TextUtils.isEmpty(processName)
                    && !TextUtils.equals(packageName, processName))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return true // 默认当主进程处理
    }

    @JvmStatic
    var sProcessName: String? = null

    /**
     * 获取进程名字
     */
    @JvmStatic
    fun getProcessName(context: Context): String? {

        if (sProcessName != null) {
            return sProcessName!!
        }

        val pid = Process.myPid()
        var cmdlineReader: BufferedReader? = null
        try {
            cmdlineReader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            var c: Int
            val processName = StringBuilder()
            while (cmdlineReader.read().also { c = it } > 0) {
                processName.append(c.toChar())
            }
            sProcessName = processName.toString()
            return sProcessName
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
            try {
                cmdlineReader?.close()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (appProcess in am.runningAppProcesses) {
            if (appProcess.pid == pid) {
                sProcessName = appProcess.processName
            }
        }
        return sProcessName
    }
}