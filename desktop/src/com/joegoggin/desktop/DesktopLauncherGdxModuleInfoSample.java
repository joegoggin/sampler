package com.joegoggin.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.joegoggin.GdxModuleInfoSample;

public class DesktopLauncherGdxModuleInfoSample {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GdxModuleInfoSample(), config);
	}
}
