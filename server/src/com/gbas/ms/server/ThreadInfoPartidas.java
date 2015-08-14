package com.gbas.ms.server;

import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Teodorico on 09/07/2014.
 */
public class ThreadInfoPartidas extends Thread {
    static Logger logger = Logger.getLogger(ThreadInfoPartidas.class);
    private static final long SLEEP_TIME = 4000;

    boolean updateInfoPartidas=true;

    private ServerMSGame serverMSGame;
    boolean running;

    public ThreadInfoPartidas(ServerMSGame serverMSGame) {
        this.serverMSGame = serverMSGame;
        running=true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (updateInfoPartidas) {
               // sendInfoPartidas();
                updateInfoPartidas = false;
            }
        }

    }

    public void setUpdateInfoPartidas(boolean updateInfoPartidas) {
        this.updateInfoPartidas = updateInfoPartidas;
    }

    /**
     * Envía las partidas a un jugador que acaba de incorporarse
     * @param jugadorMusNet
     */
/*
    public void sendToNewPlayer(JugadorMusNet jugadorMusNet) {
        final InfoPartidasNet infoPartidasNet = createInfoPartidasNet();
        jugadorMusNet.getThread().send(new NOPartidasRes(infoPartidasNet));
        updateInfoPartidas=true;
    }

    private void sendInfoPartidas() {
        final InfoPartidasNet infoPartidasNet = createInfoPartidasNet();
        int boredPlayers = 0;
        for (JugadorMusNet jugadorMusNet : serverMus.players.values()) {
            if (jugadorMusNet.getPartida() == null) {
                jugadorMusNet.getThread().send(new NOPartidasRes(infoPartidasNet));
                boredPlayers++;
            }
        }
        logger.info("Enviadas la información de partidas a " + boredPlayers + " jugadores aburridos");
    }


    public InfoPartidasNet createInfoPartidasNet() {
        final InfoPartidasNet infoPartidasNet = new InfoPartidasNet();

        final ArrayList<PartidaNet> partidaNets = new ArrayList<PartidaNet>();
        for (Partida partida : serverMus.partidas) {
            partidaNets.add(partida.getPartidaNet());
        }
        infoPartidasNet.setPartidas(partidaNets);
        infoPartidasNet.setNumJugadoresLibres(countJugadoreBored());
        infoPartidasNet.setNumJugadoreTotal(serverMus.players.size());
        infoPartidasNet.setNumPartidasEnJuego(countPartidasEnJuego());

        return infoPartidasNet;
    }

    private int countPartidasEnJuego() {
        int partidasEnJuego=0;

        for (Partida partida : serverMus.partidas) {
            if (partida.getStado() == OP_ESTADO_PARTIDA.EN_JUEGO) {
                partidasEnJuego++;
            }
        }
        return partidasEnJuego;
    }

    private int countJugadoreBored() {
        int boredPlayers = 0;
        for (JugadorMusNet jugadorMusNet : serverMus.players.values()) {
            if (jugadorMusNet.getPartida() == null) {
                boredPlayers++;
            }
        }
        return boredPlayers;
    }
*/
}
