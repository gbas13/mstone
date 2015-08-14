package com.gbas.ms;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.gbas.gnet.ClientThread;
import com.gbas.ms.model.JugadorNet;
import com.gbas.ms.net.NetListenerClientMSGame;
import com.gbas.ms.services.*;

public class MSGame extends Game implements NetListenerClientMSGame {
	public static final String LOG = MSGame.class.getSimpleName();
	public static final boolean DEV_MODE = true;
	public static final String MESSAGEVALIDACION = " debe tener entre 4 y 12 caracteres alfanuméricos";

	public PreferencesManager preferencesManager;
	public MusicManager musicManager;
	public SoundManager soundManager;
	public NetManager netManager;

	//public PartidaNet partidaNet;
	final String version = "0.1";
	public JugadorNet jugadorNet;

	@Override
	public void create() {
		Gdx.app.log(MSGame.LOG, "Creating game");

		// create the preferences service
		preferencesManager = new PreferencesManager();

		// create the music manager service
		musicManager = new MusicManager();
		musicManager.setVolume(preferencesManager.getVolume());
		musicManager.setEnabled(preferencesManager.isMusicEnabled());

		// create the sound manager service
		soundManager = new SoundManager();
		soundManager.setVolume(preferencesManager.getVolume());
		soundManager.setEnabled(preferencesManager.isSoundEnabled());



		Assets.loadAll();


		if (DEV_MODE) {
			TestScreen screen = new TestScreen(this);
			setScreen(screen);
			screen.doTest();
		} else {
			// create net service
			netManager = new NetManager(this);
			netManager.startConnection();
		}
	}
	/*
    --------------------------------------------------------------------
                            FUNCIONES COMUNES
    --------------------------------------------------------------------
     */
	@Override
	public void netEventClose(ClientThread clientThread) {
		Gdx.app.log(MSGame.LOG, "Servidor cerró");
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				//partidaNet=null;
				messageAndExit("Por problemas desconocidos\nel servidor se ha desconectado.\n\nVuelva a intentarlo más tarde.");
			}
		});

	}

	@Override
	public void setVersion(boolean valid, String txtInvalid) {

	}

	@Override
	public void errorMessage(final String txt) {
		final AbstractScreen abstractScreen = (AbstractScreen) getScreen();
		Gdx.app.postRunnable(new Runnable() {
			@Override
			public void run() {
				abstractScreen.showMessage(txt);
			}
		});
	}

	@Override
	public void loginResponse(JugadorNet jugadorNet, String txtProblem) {

	}

	@Override
	public void oponentAbandoned(ClientThread clientThread) {

	}

	public void messageAndExit(String txt) {
		AbstractScreen abstractScreen = (AbstractScreen) getScreen();
		abstractScreen.showMessageAndExit(txt);
	}


	@Override
	public void netEventOpen(ClientThread clientThread) {

	}
}
