package com.example.lastterm.util

import com.example.lastterm.R

object IconPicker{
    val icons = arrayOf(
        R.drawable.ic_math,
        R.drawable.flag,
        R.drawable.ring,
        R.drawable.ic_baseline_add_24,
        R.drawable.suben,
        R.drawable.flag,

    )
    var currentIcon = 0

    fun getIcon(): Int{
        return icons[currentIcon++ % icons.size]
    }
}