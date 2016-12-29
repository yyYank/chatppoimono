package com.github.yyYank.sever.endpoint



import javax.websocket.*
import javax.websocket.server.ServerEndpoint


/**
 * Created by yy_yank on 2016/12/29.
 */
@ServerEndpoint("/echo")
class ServerEndPoint {

    @OnOpen
    fun onOpen(session: Session) {
        println("server-[open] " + session)
    }

    @OnMessage
    fun onMessage(message: String, session: Session): String {
        println("server-[message][$message] $session")
        return message
    }


    @OnClose
    fun onClose(session: Session) {
        println("server-[close] " + session)
    }

    @OnError
    fun onError(session: Session, t : Throwable) {
        println("server-[error] " + session)
    }
}