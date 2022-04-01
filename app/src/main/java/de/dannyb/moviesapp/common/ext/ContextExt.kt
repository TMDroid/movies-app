package de.dannyb.moviesapp.common.ext

import android.R
import android.content.Context
import android.util.TypedValue

fun Context.getPrimaryDarkColor(): Int {
    val value = TypedValue()
    theme.resolveAttribute(R.attr.colorPrimaryDark, value, true)
    return value.data
}
