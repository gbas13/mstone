package com.gbas.ms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gbas.ms.scene2d.Frame;

/**
 * Created by gbas on 10/01/14.
 */
public class PartidasScreen extends AbstractScreen {
    private Frame dialogbox2;
    private Frame selection;
    private Label lbJugadores;
    private Label lbPartidas;
    private Label lbTitle;

    private Label lbSelect[];
    private Button btJoin;

    ScrollPane scrollPane;
 //   PartidaNet partidaSelected;

    public PartidasScreen(MSGame g) {
        super(g);

/*

        dialogbox2 = new Frame(null);
        dialogbox2.setBounds(0, 0, 520, 450);
        scrollPane = new ScrollPane(dialogbox2, Assets.getSkin());
        scrollPane.setBounds(0, 0, 600, 480);
        scrollPane.setForceScroll(false, true);

        stage.addActor(scrollPane);
        selection = new Frame(Assets.getSkin().getDrawable("dialogbox"));
        selection.setBounds(600, 0, 200, 480);
        stage.addActor(selection);


        TextButton btnCrear = new TextButton("Crear partida", Assets.getSkin());
        btnCrear.setPosition(selection.getWidth() / 2 - btnCrear.getWidth() / 2, 8);
        btnCrear.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.netManager.send(new NOPartidaActionReq(OP_PARTIDA.CREA));
            }
        });
        if (!game.userConfiguration.isAnonimous()) {
            TextButton btnData = new TextButton("Modificar datos", Assets.getSkin());
            btnData.setPosition(selection.getWidth() / 2 - btnCrear.getWidth() / 2, 38);
            btnData.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new DataScreen(game));
                }
            });
            selection.addActor(btnData);
        }
        lbJugadores = new Label("", Assets.getSkin());
        lbPartidas = new Label("", Assets.getSkin());

        selection.addActor(btnCrear);
        selection.addActor(lbJugadores);
        selection.addActor(lbPartidas);

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = Assets.skin.getFont("red-font");
        lbTitle = new Label("PARTIDAS", style);
        BitmapFont.TextBounds partidas = style.font.getBounds("PARTIDAS");
        lbTitle.setPosition(selection.getWidth() / 2 - partidas.width / 2, 440);
        selection.addActor(lbTitle);

        // Solicita el estado de las partidas
        game.netManager.send(new NOPartidasReq());
*/
    }

/*
    public void updatePartidas(InfoPartidasNet infoPartidasNet) {
        dialogbox2.clear();
        int height = Math.max(450, 100 + (100 * (infoPartidasNet.getPartidas().size() / 5)));
        dialogbox2.setHeight(height);
        scrollPane.layout();

        int enJuego = infoPartidasNet.getNumJugadoreTotal() - infoPartidasNet.getNumJugadoresLibres();
        lbJugadores.setText("" + enJuego + "/" + infoPartidasNet.getNumJugadoreTotal() + " Jugadores");
        lbPartidas.setText("" + infoPartidasNet.getNumPartidasEnJuego() + "/" + infoPartidasNet.getPartidas().size() + " Partidas");

        lbJugadores.validate();
        lbPartidas.validate();

        lbJugadores.setPosition(selection.getWidth() / 2 - lbJugadores.getWidth() / 2, 78);
        lbPartidas.setPosition(selection.getWidth() / 2 - lbPartidas.getWidth() / 2, 108);


        int pos = 0;
        for (final PartidaNet partida : infoPartidasNet.getPartidas()) {
            MesaJuego mesaJuego = new MesaJuego(partida);
            // mesaJuego.setSize(90, 90);
            int px = 10 + ((pos % 5) * 102);
            //       int py = 360 - ((pos / 6) * 100);
            int py = height - (100 + ((pos / 5) * 100));
            mesaJuego.setBounds(px, py, 90, 90);
            dialogbox2.addActor(mesaJuego);

            pos++;

            mesaJuego.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.log(Mus.LOG, "Click en la partida " + partida.getId());
                    //game.netManager.send(new NOPartidaActionReq(OP_PARTIDA.UNE, partida.getId()));
                    drawPartidaSelected(partida);
                }
            });
        }
        if (partidaSelected != null) {
            for (PartidaNet partidaNet : infoPartidasNet.getPartidas()) {
                if (partidaNet.getId() == partidaSelected.getId()) {
                    drawPartidaSelected(partidaNet);
                    return;
                }
            }
            undrawSelected();

            // ELiminamos pq no se ha encontrado
            partidaSelected = null;

        }

    }

    private void undrawSelected() {
        // Elimina widget selected
        if (lbSelect != null) {
            for (Label label : lbSelect) {
                selection.removeActor(label);
            }
            selection.removeActor(btJoin);
        }
        lbSelect = null;
        btJoin = null;


    }

    private void drawPartidaSelected(final PartidaNet partida) {
        undrawSelected();

        partidaSelected = partida;

        lbSelect = new Label[4];
        Label.LabelStyle lbStyle = new Label.LabelStyle();
        lbStyle.font = Assets.getSkin().getFont("default-font");

        int count = 0, users = 0;
        for (UserConfiguration userConfiguration : partida.getJugadores()) {
            lbStyle.fontColor = count % 2 == 0 ? Color.BLUE : Color.RED;
            if (userConfiguration != null) {
                users++;
            }
            Label l = new Label(userConfiguration != null ? userConfiguration.getName() : " --- ", lbStyle);
            l.setBounds(10, 380 - (count * 30), selection.getWidth() - 20, 20);
            lbSelect[count] = l;
            count++;
            selection.addActor(l);

        }

        if (users < 4) {
            btJoin = new TextButton("Unirse", Assets.getSkin());
            btJoin.setPosition(selection.getWidth() / 2 - btJoin.getWidth() / 2, 220);
            btJoin.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.netManager.send(new NOPartidaActionReq(OP_PARTIDA.UNE, partida.getId()));
                }
            });
            selection.addActor(btJoin);
        }

    }
*/
}
