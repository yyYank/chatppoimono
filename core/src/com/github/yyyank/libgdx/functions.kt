package com.github.yyyank.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Array
import com.github.yyyank.libgdx.ChatApplication

/**
 * Created by yy_yank on 2016/10/16.
 */
fun moveTo(screen: ScreenAdapter, game: ChatApplication, stage: Stage) {
    val fadeOut = Actions.fadeOut(0.5f)
    val toGameScreen = Actions.run(Runnable {
        game.screen = screen
    })
    stage.addAction(Actions.sequence(fadeOut, toGameScreen))
}

fun frameByFrame(vararg fileNames: String): Array<TextureRegion> = Array<TextureRegion>().apply {
    fileNames.map { TextureRegion(Texture(Gdx.files.internal(it))) }.forEach { add(it) }
}


