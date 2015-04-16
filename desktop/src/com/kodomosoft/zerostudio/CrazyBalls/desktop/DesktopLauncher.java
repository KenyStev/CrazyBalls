package com.kodomosoft.zerostudio.CrazyBalls.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kodomosoft.zerostudio.CrazyBalls.CrazyBalls;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "CrazyBalls";
		config.width = 440;
		config.height = 740;
		config.resizable = false;
		new LwjglApplication(new CrazyBalls(), config);
	}
}
