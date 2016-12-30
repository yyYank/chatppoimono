package com.github.yyyank.libgdx.domain

/**
 * Created by yy_yank on 2016/10/29.
 */
interface PreviousScreenIntent<T : Any> {
    fun receive(): T
}

class FlurryOfBlowsScoreIntent(private val score: Int) : PreviousScreenIntent<Int> {
    override fun receive(): Int = score
}

class ProcessingFobScreenIntent(private val score: Int) : PreviousScreenIntent<Int> {
    override fun receive(): Int = score
}
