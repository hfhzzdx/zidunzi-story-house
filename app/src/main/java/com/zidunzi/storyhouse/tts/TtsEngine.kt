package com.zidunzi.storyhouse.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

class TtsEngine(context: Context) : TextToSpeech.OnInitListener {
    private val tts = TextToSpeech(context, this)
    var ready = false
        private set

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.SIMPLIFIED_CHINESE
            ready = true
        }
    }

    fun speak(text: String) {
        if (!ready) return
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "story")
    }

    fun shutdown() { tts.shutdown() }
}
