package com.gbas.ms.server.logic;

import com.gbas.gnet.NetObject;
import com.gbas.ms.model.CartaEnJuego;
import com.gbas.ms.model.Mazo;

import java.util.List;

/**
 * Created by Teodorico on 26/07/2015.
 */
public abstract class Jugador {
    UserConfiguration userConfiguration;

    int cristales;

    Partida partida;
    CartaEnJuego cabeza;
    List<CartaEnJuego> enMesa;
    List<CartaEnJuego> enMano;
    Mazo mazoCartas;


    public Jugador(UserConfiguration userConfiguration) {
        this.userConfiguration = userConfiguration;
    }

    public UserConfiguration getUserConfiguration() {
        return userConfiguration;
    }

    public void setUserConfiguration(UserConfiguration userConfiguration) {
        this.userConfiguration = userConfiguration;
    }

    public abstract void send(NetObject nObj);


    public int getCristales() {
        return cristales;
    }

    public void setCristales(int cristales) {
        this.cristales = cristales;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public CartaEnJuego getCabeza() {
        return cabeza;
    }

    public void setCabeza(CartaEnJuego cabeza) {
        this.cabeza = cabeza;
    }

    public List<CartaEnJuego> getEnMesa() {
        return enMesa;
    }

    public void setEnMesa(List<CartaEnJuego> enMesa) {
        this.enMesa = enMesa;
    }

    public List<CartaEnJuego> getEnMano() {
        return enMano;
    }

    public void setEnMano(List<CartaEnJuego> enMano) {
        this.enMano = enMano;
    }

    public Mazo getMazoCartas() {
        return mazoCartas;
    }

    public void setMazoCartas(Mazo mazoCartas) {
        this.mazoCartas = mazoCartas;
    }
}
