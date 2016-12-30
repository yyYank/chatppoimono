package com.github.yyyank.libgdx

import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.github.yyyank.libgdx.domain.Position
import com.badlogic.gdx.scenes.scene2d.Actor





/**
 * Created by yy_yank on 2016/10/16.
 */
fun Stage.register(actor: Actor, pos: Position) {
    actor.let {
        it.setPosition(pos.x, pos.y)
        this.addActor(it)
    }
}

fun Stage.register(actor: Actor, pos: Position = Position.NONE, vararg listeners: EventListener) {
    actor.let {
        val self = it
        self.setPosition(pos.x, pos.y)
        listeners.forEach { self.addListener(it) }
        this.addActor(self)
    }
}