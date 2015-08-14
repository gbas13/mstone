package com.gbas.ms.server.logic;

import com.gbas.ms.model.Mazo;

import java.io.Serializable;
import java.util.List;

public class UserConfiguration implements Serializable {
    private String name;
    private String passwd;
    private int puntuacion;
    private boolean sonido;
    private boolean anonimo;

    List<Mazo> mazoList;

    public static UserConfiguration getInstance(String name) {
        UserConfiguration userConfiguration = new UserConfiguration();
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

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public boolean isAnonimo() {
        return anonimo;
    }

    public void setAnonimo(boolean anonimo) {
        this.anonimo = anonimo;
    }
}
