package com.github.yyyank.libgdx

import com.badlogic.gdx.Gdx
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by yy_yank on 2016/10/29.
 */
object Logger {
    private fun currentTimeString(): String = SimpleDateFormat().apply {
        applyPattern("yyyy/MM/dd HH:mm:ss")
    }.format(Date())

    fun debug(message: String) {
        Gdx.app.debug("[${currentTimeString()}][flurry-of-blows][DEBUG]", message)
    }

    fun log(message: String) {
        Gdx.app.log("[${currentTimeString()}][flurry-of-blows][INFO]", message)
    }

    fun error(message: String) {
        Gdx.app.error("[${currentTimeString()}][flurry-of-blows][ERROR]", message)
    }
}