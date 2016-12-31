package com.github.yyyank.libgdx.scene.chat

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.github.yyyank.libgdx.AssetInitializer
import com.github.yyyank.libgdx.ChatApplication
import com.github.yyyank.libgdx.Logger
import com.github.yyyank.libgdx.domain.Position
import com.github.yyyank.libgdx.register
import com.github.yyyank.libgdx.widget.Text
import org.glassfish.tyrus.client.ClientManager
import java.net.URI
import java.util.concurrent.atomic.AtomicInteger
import javax.websocket.*

/**
 * Created by yy_yank on 2016/12/29.
 */
class ChatScene(chatApplication: ChatApplication, am: AssetManager) : ScreenAdapter() {

    val stage: Stage
    val skin: Skin = chatApplication.config.skin
    val client = ClientManager.createClient()
    val clientEndpoint: ClientEndpoint
    var session: Session? = null


    init {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        AssetInitializer.ScreenSkin.initialize(skin, am)
        Logger.debug("${this.javaClass.name} init")
        stage = Stage(chatApplication.config.viewport)
        clientEndpoint = ClientEndpoint(stage, this)
        // 背景画像
        stage.register(Image(skin, "background"), Position(0f, 0f))
        // チャットボタン
        val chatButton = Button(skin, "send")
        chatButton.setSize(100f, 50f)
        stage.register(chatButton, Position(80f, 0f),
                object : ClickListener() {
                    override fun clicked(event: InputEvent, x: Float, y: Float) {
                        Gdx.input.getTextInput(object : Input.TextInputListener {
                            override fun input(text: String?) {
                                Gdx.app.postRunnable(Runnable {
                                    session?.asyncRemote?.sendText(text)
                                })
                            }

                            override fun canceled() {
                            }
                        }, "チャットメッセージ送信", "", "")
                    }
                }
        )
    }

    override fun render(delta: Float) {
        super.render(delta)
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        Logger.debug("${this.javaClass.name} resize")
        stage.viewport.update(width, height)
    }

    override fun show() {
        Logger.debug("${this.javaClass.name} show")
        Gdx.input.inputProcessor = stage
        Gdx.input.getTextInput(object : Input.TextInputListener {
            override fun input(text: String?) {
                Gdx.app.postRunnable(Runnable {
                    // ユーザー名
                    stage.register(Text("ユーザー名${text}", 20, Position(80f, 500f)))

                    client.run {
                        asyncConnectToServer(clientEndpoint, URI("ws://localhost:8181/ws/chat/${text}"))
                    }
                })
            }

            override fun canceled() {
            }
        }, "ユーザー名を決める", "", "")
    }

    override fun hide() {
        Logger.debug("${this.javaClass.name} hide")
        dispose()
    }

    override fun pause() {
        Logger.debug("${this.javaClass.name} pause")
        super.pause()
    }

    override fun resume() {
        Logger.debug("${this.javaClass.name} resume")

    }

    override fun dispose() {
        Logger.debug("${this.javaClass.name} dispose")
        stage.dispose()
    }
}

@javax.websocket.ClientEndpoint
class ClientEndpoint(val stage: Stage, val scene: ChatScene) {
    val defaultOffsetY = 490
    val offsetY = AtomicInteger(defaultOffsetY)
    val textHeight = 22

    @OnOpen
    fun onOpen(session: Session, config: EndpointConfig) {
        Logger.debug("client-[open] " + session)
        scene.session = session
    }

    @OnMessage
    fun onMessage(message: String, session: Session) {
        scene.session = session
        Logger.debug("client-[message][$message] $session")
        Logger.debug("----------")
        Gdx.app.postRunnable {
            val textObject = Text(message, 20, Position(0f, offsetY.addAndGet(-textHeight).toFloat()))
            stage.register(textObject)
        }
    }

    @OnClose
    fun onClose(session: Session) {
        Logger.debug("client-[close] $session")
    }

    @OnError
    fun onError(session: Session?, t: Throwable?) {
        Logger.debug("client-[error] ${t?.message} $session")
    }
}