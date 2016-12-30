package com.github.yyyank.libgdx

import com.badlogic.gdx.graphics.g3d.Environment
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * Created by yy_yank on 2016/12/29.
 */
data class ChatApplicationConfig (
        val gameWidth: Float,
        val gameHeight: Float,
        val viewport: FitViewport,
        val skin: Skin
)
