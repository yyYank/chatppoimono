package com.github.yyyank.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.yyyank.libgdx.ChatApplication;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 256;
        config.height = 512;
        new LwjglApplication(new ChatApplication(), config);
    }
}
