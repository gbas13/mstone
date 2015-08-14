package com.gbas.ms.android;

import android.os.Bundle;

import android.view.WindowManager;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gbas.ms.MSGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);



		// prevent the screen from dimming/sleeping (no permission required)
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// create the game
		AndroidApplicationConfiguration androidApplicationConfiguration = new AndroidApplicationConfiguration();
		androidApplicationConfiguration.useAccelerometer=false;
		androidApplicationConfiguration.useCompass=false;
		androidApplicationConfiguration.useWakelock=true;
		androidApplicationConfiguration.useGLSurfaceView20API18=true;

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MSGame(), config);
	}
}
