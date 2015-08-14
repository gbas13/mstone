package com.gbas.ms.services;

import com.badlogic.gdx.Gdx;
import com.gbas.gnet.ClientThread;
import com.gbas.gnet.NetObject;
import com.gbas.ms.MSGame;

import java.io.IOException;
import java.net.Socket;


/**
 * A service that manages the sound effects.
 */
public class NetManager {
    //public static final String SERVER_HOST = "mus.trebol-a.com";
    //public static final String SERVER_HOST = "188.76.71.39";
    public static final String SERVER_HOST = "127.0.01";

    ClientThread client;
    private MSGame game;

    public NetManager(MSGame game) {
        this.game = game;
    }


    public void send(NetObject netObject) {
        client.send(netObject);
    }

    public void startConnection() {
        try {
            final Socket so = new Socket(SERVER_HOST, 6677);
            client = new ClientThread(game, so, "ClientThread");
            client.start();
        } catch (IOException e) {
            Gdx.app.log(MSGame.LOG, "Creando socket", e);
            game.errorMessage("Parece que el servidor no está activo.\nPruebe en otro momento.");
        }
    }

    public boolean running() {
        return client != null && client.running;
    }
}
