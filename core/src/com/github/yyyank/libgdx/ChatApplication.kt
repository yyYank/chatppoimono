package com.github.yyyank.libgdx;

import com.badlogic.gdx.Game
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.FitViewport
import com.github.yyyank.libgdx.scene.chat.ChatScene
import org.glassfish.tyrus.server.Server
import kotlin.properties.Delegates


class ChatApplication : Game() {


    var config: ChatApplicationConfig by Delegates.notNull<ChatApplicationConfig>()
    val am = AssetManager();
    val skin = Skin();
    var server: Server? = null

    override fun create() {
//        server = Server("localhost", 8181, "/ws", null, ServerEndPoint::class.java)
//        server?.start()


        config = createConfig();
        setScreen(ChatScene(this, am))
    }


    private fun createConfig(): ChatApplicationConfig {
        AssetInitializer.ScreenSkin.initialize(skin, am)
        val gameWidth = 256f
        val gameHeight = 512f
        val viewport = FitViewport(gameWidth, gameHeight)
        return ChatApplicationConfig(gameWidth, gameHeight, viewport, skin);
    }

    override fun dispose() {
        super.dispose()
//        server?.stop()
    }


}
