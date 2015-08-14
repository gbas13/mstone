package com.gbas.ms.model;

/**
 * Created by Teodorico on 26/07/2015.
 */
public class CartaDef {
    int id;
    int vida;
    int ataque;
    int coste;

    boolean jugableAMesa;

    String nombre;
    String image;
    String[] textoDescrip;


    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getTextoDescrip() {
        return textoDescrip;
    }

    public void setTextoDescrip(String[] textoDescrip) {
        this.textoDescrip = textoDescrip;
    }
}
