package com.github.yyYank.libgdx.endpoint


import com.github.yyyank.libgdx.Logger
import java.util.concurrent.CopyOnWriteArraySet
import javax.websocket.*
import javax.websocket.server.PathParam
import javax.websocket.server.ServerEndpoint


/**
 * Created by yy_yank on 2016/12/29.
 */
@ServerEndpoint("/chat/{guest-id}")
class ServerEndPoint {

    companion object {
        private val sessions = CopyOnWriteArraySet<Session>()
    }


    @OnOpen
    fun onOpen(@PathParam("guest-id") guestId: String, session: Session) {
        println("server-[open] $guestId")
        sessions.add(session)
        for (s in sessions) {
            s.asyncRemote.sendText("${guestId}さんが入室しました")
        }
    }

    @OnMessage
    fun onMessage(@PathParam("guest-id") guestId: String, message: String, session: Session) {
        println("server-[message][$message] $session")
        // broadcast
        for (s in sessions) {
            s.asyncRemote.sendText("[$guestId] $message")
        }
    }


    @OnClose
    fun onClose(@PathParam("guest-id") guestId: String, session: Session) {
        println("server-[close] " + session)
        sessions.remove(session)
        // broadcast
        for (s in sessions) {
            s.asyncRemote.sendText(guestId + "さんが退室しました")
        }
    }

    @OnError
    fun onError(session: Session, t: Throwable) {
        println("server-[error] " + t.message)
    }
}