package com.github.yyYank.main

import com.github.yyYank.libgdx.endpoint.ServerEndPoint
import org.glassfish.tyrus.client.ClientManager
import org.glassfish.tyrus.server.Server
import java.net.URI
import java.util.concurrent.TimeUnit
import javax.websocket.ClientEndpointConfig

/**
 * Created by yy_yank on 2016/12/29.
 */
object Main {
    @JvmStatic fun main(args: Array<String>) {
        val server = Server("localhost", 8181, "/ws", null, ServerEndPoint::class.java)
        try {
            server.start()
//            val client1 = ClientManager.createClient()
//            val client2 = ClientManager.createClient()
//            client1.run {
//                asyncConnectToServer(ClientEndpoint("Aさんがログイン"), URI("ws://localhost:8181/ws/echo"))
////                asyncConnectToServer(ClientEndpoint("Bさんがログイン"), URI("ws://localhost:8181/ws/echo"))
//            }
//            client2.run {
////                asyncConnectToServer(ClientEndpoint("Cさんがログイン"), URI("ws://localhost:8181/ws/echo"))
////                asyncConnectToServer(ClientEndpoint("Dさんがログイン"), URI("ws://localhost:8181/ws/echo"))
////                asyncConnectToServer(ClientEndpoint("Eさんがログイン"), URI("ws://localhost:8181/ws/echo"))
//            }

            System.`in`.read()

        } finally {
            server.stop()
        }
    }
}