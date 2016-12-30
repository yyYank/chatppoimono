package com.github.yyyank.libgdx

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Skin

/**
 * Created by yy_yank on 2016/10/17.
 */
sealed class AssetInitializer() {

    abstract fun initialize(skin: Skin, am: AssetManager)

    val params = TextureLoader.TextureParameter().let {
        it.minFilter = Texture.TextureFilter.Linear
        it.magFilter = Texture.TextureFilter.Linear
        it
    }

    object ScreenSkin : AssetInitializer() {
        override fun initialize(skin: Skin, am: AssetManager) {
            val backgroundAsset = textureDesc("scene/background.png", params)
            val sendUpAsset = textureDesc("scene/sendUp.png", params)
            val sendDownAsset = textureDesc("scene/sendDown.png", params)
            val assets = listOf(
                    "background" to backgroundAsset,
                    "sendUp" to sendUpAsset,
                    "sendDown" to sendDownAsset
            )

            assets.forEach {
                val (name, asset) = it
                am.load(asset)
                am.finishLoading()
                skin.add(name, am.get(asset), Texture::class.java)
            }
            Button.ButtonStyle(
                    skin.getDrawable("sendUp"),
                    skin.getDrawable("sendDown"), null).let { skin.add("send", it, Button.ButtonStyle::class.java) }
        }
    }
}

fun textureDesc(s: String, params: TextureLoader.TextureParameter): AssetDescriptor<Texture> = AssetDescriptor(s, Texture::class.java, params)
