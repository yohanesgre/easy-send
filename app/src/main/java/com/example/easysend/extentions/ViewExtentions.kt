package com.example.easysend.extentions

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.example.easysend.App

fun dpToPx(dp: Float): Int {
    return dpToPx(dp, App.Instance.resources)
}
private fun dpToPx(dp: Float, resources: Resources): Int {
    val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)
    return px.toInt()
}

fun getColorCompat(resId: Int) = ContextCompat.getColor(App.Instance, resId)

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}

fun View.setGone() {
    visibility = View.GONE
}
