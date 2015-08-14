package com.gbas.ms.server.logic;

import com.gbas.gnet.ClientThread;
import com.gbas.gnet.NetObject;
import com.gbas.ms.model.JugadaPosible;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teodorico on 02/08/2015.
 */
public class JugadorHumano extends Jugador {
    ClientThread clientThread;


    public JugadorHumano(UserConfiguration userConfiguration, ClientThread clientThread) {
        super(userConfiguration);
        this.clientThread = clientThread;
    }

    public List<JugadaPosible> calculaJugadasPosibles(int cristales, JugadorHumano opponent) {
        List<JugadaPosible> jugadasPosibles=new ArrayList<JugadaPosible>();





        return jugadasPosibles;
    }

    @Override
    public void send(NetObject nObj) {
        clientThread.send(nObj);

    }
}
