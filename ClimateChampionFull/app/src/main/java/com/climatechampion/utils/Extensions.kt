package com.climatechampion.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

// ─── View Extensions ──────────────────────────────────────────────────────────

fun View.show() { visibility = View.VISIBLE }
fun View.hide() { visibility = View.GONE }
fun View.invisible() { visibility = View.INVISIBLE }

fun View.showSnackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}

// ─── Fragment Extensions ──────────────────────────────────────────────────────

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

// ─── String Extensions ────────────────────────────────────────────────────────

fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean = length >= 8

// ─── Int Extensions ───────────────────────────────────────────────────────────

fun Int.toFormattedPoints(): String = when {
    this >= 1000 -> String.format("%.1fK", this / 1000.0)
    else -> this.toString()
}

fun Int.toLevelString(): String = when {
    this >= Constants.LEVEL_EXPERT_THRESHOLD -> Constants.LEVEL_EXPERT
    this >= Constants.LEVEL_INTERMEDIATE_THRESHOLD -> Constants.LEVEL_INTERMEDIATE
    else -> Constants.LEVEL_BEGINNER
}

fun Int.toProgressPercent(max: Int): Int {
    if (max == 0) return 0
    return ((this.toFloat() / max.toFloat()) * 100).toInt().coerceIn(0, 100)
}

// ─── Context Extensions ───────────────────────────────────────────────────────

fun Context.dpToPx(dp: Int): Int {
    return (dp * resources.displayMetrics.density).toInt()
}
