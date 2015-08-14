package com.gbas.ms.model;

import java.io.Serializable;

/**
 * Created by Teodorico on 11/08/2015.
 */
public class JugadorNet implements Serializable {
    private String name;
    private int puntuacion;
    private boolean sonido;
    private boolean anonimo;

    public static JugadorNet getInstance(String name) {
        JugadorNet userConfiguration = new JugadorNet();
        userConfiguration.setName(name);
        userConfiguration.setSonido(true);
        userConfiguration.setPuntuacion(0);
        userConfiguration.setAnonimo(false);

        return userConfiguration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public boolean isSonido() {
        return sonido;
    }

    public void setSonido(boolean sonido) {
        this.sonido = sonido;
    }

    public boolean isAnonimo() {
        return anonimo;
    }

    public void setAnonimo(boolean anonimo) {
        this.anonimo = anonimo;
    }
}
