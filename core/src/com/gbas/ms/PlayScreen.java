package com.gbas.ms;

/**
 * Created with IntelliJ IDEA.
 * User: teodorico
 * Date: 7/08/13
 * Time: 14:58
 * Pantalla menu al inicio del juego
 */
public class PlayScreen extends AbstractScreen {

    public PlayScreen(MSGame game) {
        super(game);
    }


    @Override
    public void show() {
        super.show();    //To change body of overridden methods use File | Settings | File Templates.

        init();
    }

    public void init() {
    }

}
