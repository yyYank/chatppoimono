package com.github.yyYank.main

import com.github.yyYank.libgdx.endpoint.ServerEndPoint
import org.glassfish.tyrus.server.Server

/**
 * Created by yy_yank on 2016/12/29.
 */
object Main {
    @JvmStatic fun main(args: Array<String>) {
        val server = Server("localhost", 8181, "/ws", mapOf(), ServerEndPoint::class.java)
        try {
            server.start()
            System.`in`.read()
        } finally {
            server.stop()
        }
    }
}