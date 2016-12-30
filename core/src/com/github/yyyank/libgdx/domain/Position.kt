package com.github.yyyank.libgdx.domain

/**
 * Created by yy_yank on 2016/10/18.
 */
class Position(val x: Float, val y: Float) {
    companion object  {
        val NONE = Position(0f, 0f)
    }
}

class MovablePosition(var x: Int, var y: Int) {

    fun move(addX: Int, addY: Int) {
        this.x = x + addX
        this.y = y + addY
    }
}