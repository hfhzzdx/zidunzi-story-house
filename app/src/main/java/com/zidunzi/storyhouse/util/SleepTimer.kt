package com.zidunzi.storyhouse.util

import android.os.CountDownTimer

class SleepTimer(private val totalMs: Long, private val onFinish: ()->Unit) {
    private var timer: CountDownTimer? = null
    fun start() {
        timer?.cancel()
        timer = object: CountDownTimer(totalMs, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() { onFinish() }
        }.start()
    }
    fun cancel() { timer?.cancel() }
}
