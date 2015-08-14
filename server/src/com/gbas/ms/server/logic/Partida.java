package com.gbas.ms.server.logic;

import com.gbas.ms.model.CartaDef;
import com.gbas.ms.net.NOGameAbandonedByOpponent;
import com.gbas.ms.server.Util;

import java.util.List;

/**
 * Created by Teodorico on 02/08/2015.
 */
public class Partida {
    public static List<CartaDef> allCartas;

    Jugador player1, player2;
    Jugador jugando;
    Jugador enEspera;

    private int cristales;
    private int fase;
    private boolean playing;

    public void startGame(Jugador player1, Jugador player2) {

        // Envia heroes (cliente muestra presentacion)
        determinaEnJuegoEnEspera(Util.rd.nextBoolean());
        // envia cartas iniciales a jugadores (3 y 4)
        // Espera cambios o aceptación o fin de tiempo
        fase = 0;
        playing = true;
        continuaJuego();
    }

    private void continuaJuego() {
        while(playing) {
            cristales = Math.max(fase / 2, 10);
            determinaEnJuegoEnEspera(fase % 2 == 0);
            turnoPara();
            fase++;
        }
    }

    private void determinaEnJuegoEnEspera(boolean determinante) {
        jugando = determinante ? player1 : player2;
        enEspera = !determinante ? player2 : player1;
    }

    public void turnoPara() {
        // activa tiempo de juego
        // recoge y envía una carta (si hay)
        if (!checkVictory()) {
            // envía cristales
            // Acciones automáticas preturno
            // espera_jugada
        }
    }

    //Comprueba que ambas cabezas tienen vida
    private boolean checkVictory() {
        return false;
    }

    public void esperaJugada() {
        // determina cartas con posibles jugadas
        // Envía a jugador sus posibles jugadas
        // Espera acciones jugador
    }

    public boolean cancelByAbandono(JugadorHumano jugadorNet) {
        if (jugadorNet == player1) {
            player2.send(new NOGameAbandonedByOpponent());
            return true;
        } else if (jugadorNet == player2) {
            player1.send(new NOGameAbandonedByOpponent());
            return true;
        }
        return false;
    }
}
