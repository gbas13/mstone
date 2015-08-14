package com.gbas.ms.server;

import com.gbas.gnet.ClientThread;
import com.gbas.gnet.server.ServerThread;
import com.gbas.ms.server.logic.UserConfiguration;
import com.gbas.ms.net.NOLoginRes;
import com.gbas.ms.net.NOVersionRes;
import com.gbas.ms.net.NetListenerServerMSGame;
import com.gbas.ms.server.dao.DaoService;
import com.gbas.ms.server.logic.JugadorHumano;
import com.gbas.ms.server.logic.Partida;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerMSGame implements NetListenerServerMSGame {
    static Logger logger = Logger.getLogger(ServerMSGame.class);
    final String version = "0.1";

    ServerThread server;

    // All players logged, not connected
    Map<ClientThread, JugadorHumano> players = new HashMap<ClientThread, JugadorHumano>();
    List<Partida> partidas = new ArrayList<Partida>();
    static private int partidasCounter = 0;

    // Esta tarea actualiza el estado de las partidas a los usuarios q aún no han ingresado en ninguna
    ThreadInfoPartidas threadInfoPartidas;

   /*
   --------------------------------------------------------------------
                           FUNCIONES COMMUNES, ARRANQUE, CERRAR, ...
   --------------------------------------------------------------------
    */

    public static void main(String[] args) {
        try {
            ServerMSGame serverMSGame = new ServerMSGame();

            while (serverMSGame.server.isRunServer()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            logger.error("Desconectado el servidor", e);
        }
    }

    public ServerMSGame() throws IOException {
        logger.info("Arrancando servidor");
        server = new ServerThread(this);
        logger.info("Servidor a la escucha");
        server.start();
        logger.info("Cargando las cartas");
        loadAllCards();

        threadInfoPartidas = new ThreadInfoPartidas(this);
        threadInfoPartidas.start();
    }

    private void loadAllCards() {
    }

    @Override
    public void netEventClose(ClientThread clientThread) {
        logger.info("Mensaje de desconexion");

        final JugadorHumano jugadorNet = players.get(clientThread);

        if (jugadorNet != null) {
            if (jugadorNet.getPartida() != null) {
                // Estaba jugando y se retiro
                logger.info("Adios a uno que jugaba");

                if (jugadorNet.getPartida().cancelByAbandono(jugadorNet)) {
                    partidas.remove(jugadorNet.getPartida());
                }

                jugadorNet.setPartida(null);
            } else {
                logger.info("Adios a uno sin partida");
            }
            players.remove(clientThread);
        } else {
            logger.warn("Un clientThread envió mensaje Close sin haber desconectado");
        }

        server.remove(clientThread);
    }


    @Override
    public void netEventPingReply(ClientThread clientThread, long l) {

    }

    @Override
    public void netEventPingLaunch(int i) {

    }


    /*
    --------------------------------------------------------------------
                            PANTALLA DE LOGIN
    --------------------------------------------------------------------
     */
    @Override
    public void versionRequired(String versionClient, ClientThread clientThread) {
        if (isVersionValid(versionClient)) {
            logger.info("Cliente conectado con una versión válida " + versionClient);
            clientThread.send(new NOVersionRes(true));
        } else {
            logger.warn("Cliente con la version " + versionClient + " servidor con version " + version);
            clientThread.send(new NOVersionRes(false, "Debe actualizar la versión"));
        }
    }

    private boolean isVersionValid(String versionClient) {
        return version.equals(versionClient);
    }


    @Override
    public void loginUsuario(String name, String password, ClientThread clientThread) {
        if (isPlaying(name)) {
            logger.info("Usuario " + name + " está jugando");
            clientThread.send(new NOLoginRes("Actualmente conectado."));
            return;
        }

        UserConfiguration userConfiguration;
        if (name == null) {
            userConfiguration = DaoService.getUserDescriptionDAO().createAnonimousUser();
        } else {
            userConfiguration = DaoService.getUserDescriptionDAO().loadUserDescription(name, password);
        }

        if (userConfiguration == null) {
            logger.info("Usuario " + name + " no corresponde la password");
            clientThread.send(new NOLoginRes("Nombre/Password erróneo"));
        } else {
            logger.info("Usuario " + name + " logueado en el server");
            final JugadorHumano cartagoConnection = new JugadorHumano(userConfiguration, clientThread);
            players.put(clientThread, cartagoConnection);

            clientThread.send(new NOLoginRes(userConfiguration));
        }
    }

    private boolean isPlaying(String name) {
        for (JugadorHumano jugadorMusNet : players.values()) {
            if (jugadorMusNet.getUserConfiguration().getName().equals(name)) {
                return true;
            }
        }
        return false;
    }



}
