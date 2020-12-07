package me.yifeiyuan.foundation

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.io.Closeable

/**
 * Created by 程序亦非猿 on 2020/12/7.
 */

fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    if (isMainThread()) {
        Toast.makeText(this, text, duration).show()
    } else {
        Looper.prepare()
        Toast.makeText(this, text, duration).show()
        Looper.loop();
    }
}

fun Context.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    if (isMainThread()) {
        Toast.makeText(this, resId, duration).show()
    } else {
        Looper.prepare()
        Toast.makeText(this, resId, duration).show()
        Looper.loop();
    }
}

fun Fragment.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    if (!isAdded) {
        return
    }
    if (isMainThread()) {
        Toast.makeText(activity, text, duration).show()
    } else {
        Looper.prepare()
        Toast.makeText(activity, text, duration).show()
        Looper.loop();
    }
}

fun Fragment.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    if (!isAdded) {
        return
    }
    if (isMainThread()) {
        Toast.makeText(activity, resId, duration).show()
    } else {
        Looper.prepare()
        Toast.makeText(activity, resId, duration).show()
        Looper.loop();
    }
}

fun Any.isMainThread() = Looper.getMainLooper() == Looper.myLooper()

fun View.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, text, duration).show()
}

fun View.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, resId, duration).show()
}

inline fun View.doOnClick(crossinline action: (view: View) -> Unit) {
    setOnClickListener {
        action(it)
    }
}

fun ViewGroup.inflate(resId: Int, attachToRoot: Boolean = true): View {
    return LayoutInflater.from(context).inflate(resId, this, attachToRoot)
}

fun ImageView.recycle() {
    try {
        (drawable as BitmapDrawable).let {
            setImageResource(0)
            setImageDrawable(null)
            it.bitmap.recycle()
            it.callback = null
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Closeable?.closeQuietly(){
    this?.let {
        try {
            it.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}