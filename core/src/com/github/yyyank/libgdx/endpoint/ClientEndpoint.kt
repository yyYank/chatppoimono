package com.github.yyYank.libgdx.endpoint

import javax.websocket.*
import javax.websocket.MessageHandler.Whole
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.websocket.ClientEndpoint


/**
 * Created by yy_yank on 2016/12/29.
 */
@ClientEndpoint
class ClientEndpoint(val message : String) {

    @OnOpen
    fun onOpen(session: Session, config: EndpointConfig) {
        println("client-[open] " + session)
        println("----------")
        println("${message}を送信します")
//        TimeUnit.SECONDS.sleep(10)
        session.getBasicRemote().sendText(message)
    }

    @OnMessage
    fun onMessage(message: String, session: Session) {
        println("client-[message][$message] $session")
        TimeUnit.SECONDS.sleep(10)
        println("----------")
        println("${message}を送信します")
        session.asyncRemote.sendText(message)
    }

    @OnClose
    fun onClose(session: Session) {
        println("client-[close] $session")
    }

    @OnError
    fun onError(session: Session?, t: Throwable?) {
        println("client-[error] ${t?.message} $session")
    }
}