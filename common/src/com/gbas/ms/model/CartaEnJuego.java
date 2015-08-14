package com.gbas.ms.model;

/**
 * Created by Teodorico on 26/07/2015.
 */
public class CartaEnJuego {
    CartaDef cartaDef;

    int vida;
    int ataque;
    int coste; // Sólo cuando está en mano


    boolean enMesa;



    public CartaDef getCartaDef() {
        return cartaDef;
    }

    public void setCartaDef(CartaDef cartaDef) {
        this.cartaDef = cartaDef;
    }

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

    public boolean isEnMesa() {
        return enMesa;
    }

    public void setEnMesa(boolean enMesa) {
        this.enMesa = enMesa;
    }
}
