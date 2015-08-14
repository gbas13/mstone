package com.gbas.ms;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.gbas.ms.model.CartaDef;
import com.gbas.ms.model.CartaEnJuego;
import com.gbas.ms.scene2d.Naipe2d;

/**
 * Created with IntelliJ IDEA.
 * User: teodorico
 * Date: 7/08/13
 * Time: 14:58
 * Pantalla menu al inicio del juego
 */
public class TestScreen extends PlayScreen {
/*
    private final PartidaNet partidaNet;
    private Mazo mz;
*/

    public static final int MIPOSICION = 2; // Posición Mia con rescpecto al server (0-3)
    public static final int JUGADOR_MANO = 1; // Posición de Mano con rescpecto al server (0-3)
    private int count1;
    private int count2;
    private int juegos1;
    private int juegos2;
    private int vaca1;
    private int vaca2;
    private int finalOrdago;
    private Naipe2d naipe2d;

/*
    public class NetManagerFake extends NetManager {

        public NetManagerFake(MSGame game) {
            super(game);
        }

        @Override
        public void send(NetObject netObject) {
            Gdx.app.log(MSGame.LOG, "Enviado (FAKE) " + netObject.toString());
        }
    }
*/

    public TestScreen(MSGame game) {
        super(game);


/*
        partidaNet = new PartidaNet();
        partidaNet.positionPlayer = MIPOSICION;

        UserConfiguration ud1 = new UserConfiguration();
        ud1.setName("Player 1");
        ud1.setImageUser(23);

        UserConfiguration ud2 = new UserConfiguration();
        ud2.setName("Player 2");
        ud2.setImageUser(25);
        UserConfiguration ud3 = new UserConfiguration();
        ud3.setName("Player 3");
        ud3.setImageUser(63);
        UserConfiguration ud4 = new UserConfiguration();
        ud4.setName("Player 4");
        ud4.setImageUser(38);

        UserConfiguration[] userDescriptions = {
                ud1, ud2, ud3, ud4
        };
        partidaNet.setJugadores(userDescriptions);

        game.partidaNet = partidaNet;
        //setBackgroundTexture(Assets.textureTapeteRegion);

         game.netManager = new NetManagerFake(game);
*/
    }

    public void doTest() {
        CartaEnJuego cartaEnJuego = new CartaEnJuego();
        cartaEnJuego.setAtaque(3);
        cartaEnJuego.setVida(1);
        CartaDef cartaDef = new CartaDef();
        cartaEnJuego.setCartaDef(cartaDef);
        cartaDef.setVida(1);
        cartaDef.setAtaque(3);
        cartaDef.setCoste(2);
        cartaDef.setImage("muni.png");
        cartaDef.setNombre("Munipa");
        cartaDef.setTextoDescrip(new String[]{"Hola", "Que tal"});
        naipe2d = new Naipe2d(cartaEnJuego);
        stage.addActor(naipe2d);
        naipe2d.setPosition(100,100);
        naipe2d.addAction(Actions.forever(Actions.parallel(Actions.rotateBy(180, 2f), Actions.sequence(Actions.scaleTo(1.2f, 1.2f, .9f), Actions.scaleTo(.8f, .8f, .9f)))));

    }

/*

    public void doTest() {
        mz = new Mazo();
        doStartGame(partidaNet);

        testReparto(mz);

        testApuestas();

        testScore();

*/
/*
        testSay();

        testCambiaMisCartas();

        testCambiaOtro();

        testMuestraTodas(18);

        testMuestraPuntos();

        testChat();

        testFases();

        testRecogida();

        testInput();
*//*



    }

    private void testScore() {
        addAction(new Action() {
            @Override
            public boolean act(float delta) {

                ScorePartida scorePartida = new ScorePartida();
                scorePartida.setFinalByAbandono(false);
                scorePartida.setFinalizada(true);
                scorePartida.setTiempoJuegoSeg(MathUtils.random(0, 5000));

                ArrayList<ScoreVaca> scoreVacas = generaVacasPartida();
                scorePartida.setEquipoWin(vaca1 > vaca2 ? 1 : 2);
                scorePartida.setPuntos(new int[]{vaca1, vaca2});
                scorePartida.setScoreVacas(scoreVacas);


                score.show(scorePartida);
                return true;
            }
        }, 3f);

    }

    public ArrayList<int[]> generaPuntosRonda(int maxt1, int maxt2) {
        ArrayList<int[]> puntos1 = new ArrayList<int[]>();
        for (int i = 0; i < 4; i++) {
            int puntos = MathUtils.random(1, 6);
            boolean b = MathUtils.randomBoolean();

            if (b) {
                puntos1.add(new int[]{puntos, 0});
                maxt1 += puntos;
            } else {
                puntos1.add(new int[]{0, puntos});
                maxt2 += puntos;
            }
            if (maxt1 > 40 || maxt2 > 40) {
                break;
            }
        }
        return puntos1;
    }

    public  ArrayList<ScoreRonda> generaPuntosJuego() {
        count1 = 0;
        count2 = 0;

        ArrayList<ScoreRonda> scoreRondas = new ArrayList<ScoreRonda>();

        while (count1 < 40 && count2 < 40) {
            ArrayList<int[]> ints = generaPuntosRonda(count1, count2);
            ScoreRonda scoreRonda = new ScoreRonda();
            scoreRonda.setPuntos(ints);
            for (int[] anInt : ints) {
                count1 += anInt[0];
                count2 += anInt[1];
            }
            scoreRondas.add(scoreRonda);
        }
        return scoreRondas;
    }

    public ArrayList<ScoreJuego> generaJuegosVaca() {
        juegos1 = 0;
        juegos2 = 0;
        finalOrdago = 0;
        ArrayList<ScoreJuego> scoreJuegos = new ArrayList<ScoreJuego>();
        while (juegos1 < 2 && juegos2 < 2) {
            ArrayList<ScoreRonda> scoreRondas = generaPuntosJuego();
            ScoreJuego scoreJuego = new ScoreJuego();
            if (MathUtils.random(0, 20) == 14) {
                scoreJuego.setFinalByOrdago(true);
                finalOrdago = MathUtils.random(1, 2);
                scoreJuego.setEquipoWin(finalOrdago);
                scoreJuego.setFinalByOrdago(true);

            } else {
                scoreJuego.setFinalByOrdago(false);
                scoreJuego.setScoreRonda(scoreRondas);
                scoreJuego.setPuntos(new int[]{count1, count2});
                scoreJuego.setEquipoWin(count1 > count2 ? 1 : 2);
                scoreJuegos.add(scoreJuego);
                if (scoreJuego.getEquipoWin() == 1) {
                    juegos1++;
                } else {
                    juegos2++;
                }
            }
        }
        return scoreJuegos;
    }

    public ArrayList<ScoreVaca> generaVacasPartida() {
        vaca1 = 0;
        vaca2 = 0;
        ArrayList<ScoreVaca> scoreVacas = new ArrayList<ScoreVaca>();

        while (vaca1 < 2 && vaca2 < 2) {
            ArrayList<ScoreJuego> scoreJuegos = generaJuegosVaca();
            ScoreVaca scoreVaca = new ScoreVaca();
            scoreVaca.setScoreJuego(scoreJuegos);
            scoreVaca.setEquipoWin(juegos1 > juegos2 ? 1 : 2);
            scoreVaca.setPuntos(new int[]{juegos1, juegos2});
            scoreVacas.add(scoreVaca);
            if (juegos1 > juegos2) {
                vaca1++;
            } else {
                vaca2++;
            }
        }

        return scoreVacas;

    }


    private void testRecogida() {

    }

    private void testInput() {

        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                inputButtons.makeMusIn();
                return true;
            }
        }, 1f);

       addAction(new Action() {
            @Override
            public boolean act(float delta) {
                inputButtons.makeApuestaOut(0);
                return true;
            }
        }, 3f);


        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                inputButtons.makeApuestaIn();
                return true;
            }
        }, 4f);

        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                inputButtons.makeRespuestaIn();
                return true;
            }
        }, 10f);

        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                inputButtons.makeAceptaOrdagoIn();
                return true;
            }
        }, 14f);

        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                inputButtons.makeConteoIn();
                return true;
            }
        }, 18f);

        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                inputButtons.makeApuestaIn();
                return true;
            }
        }, 22f);

        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                inputButtons.makeRespuestaIn();
                return true;
            }
        }, 26f);

        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                inputButtons.makeDescartarIn();
                return true;
            }
        }, 30f);

        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                inputButtons.makeApuestaIn();
                return true;
            }
        }, 34f);

        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                inputButtons.makeRespuestaIn();
                return true;
            }
        }, 38f);

    }

    private void testFases() {
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                cambioFase("GRANDES",0);
                return true;
            }
        }, 8f);
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                cambioFase("CHICAS",0);
                return true;
            }
        }, 10f);
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                cambioFase("CONTEO",0);
                return true;
            }
        }, 18f);

    }


    private void testChat() {
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                chat.chatMsg("Hola", getLabelStyleFromRelativePosition(0));
                chat.chatMsg("Hola", getLabelStyleFromRelativePosition(1));
                chat.chatMsg("Hola", getLabelStyleFromRelativePosition(2));
                chat.chatMsg("Hola", getLabelStyleFromRelativePosition(3));

                for (int i = 0; i < 30; i++) {
                    chat.chatMsg("Contando y volviendo a contar ", getLabelStyleFromRelativePosition(i%4));
                }
                return true;
            }
        }, 05f);
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                chat.chatMsg("Adios", getLabelStyleFromRelativePosition(0));
                chat.chatMsg("Adios", getLabelStyleFromRelativePosition(1));
                chat.chatMsg("Adios", getLabelStyleFromRelativePosition(2));
                chat.chatMsg("Adios", getLabelStyleFromRelativePosition(3));
                return true;
            }
        }, 15f);

    }
    private void testMuestraTodas(float at) {
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                muestraTodas(new Naipe[][]{
                        {mz.getNaipe(), mz.getNaipe(), mz.getNaipe(), mz.getNaipe()},
                        {mz.getNaipe(), mz.getNaipe(), mz.getNaipe(), mz.getNaipe()},
                        {mz.getNaipe(), mz.getNaipe(), mz.getNaipe(), mz.getNaipe()},
                        {mz.getNaipe(), mz.getNaipe(), mz.getNaipe(), mz.getNaipe()}

                });
                return true;
            }
        }, at);

    }

    private void testMuestraPuntos() {
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                puntos.addPuntos(1, 1);
                return true;
            }
        }, 8f);
         addAction(new Action() {
            @Override
            public boolean act(float delta) {
                puntos.addPuntos(2, 6);
                return true;
            }
        }, 9f);

    }

    private void testCambiaMisCartas() {
        stage.addAction(Actions.sequence(
                delay(8f),
                new Action() {
                    @Override
                    public boolean act(float delta) {
                        naipesJugador[0].get(0).doFlipMove();
                        naipesJugador[0].get(2).doFlipMove();
                        naipesJugador[0].get(3).doFlipMove();

                        return true;
                    }
                },
                delay(2f),
                new Action() {
                    @Override
                    public boolean act(float delta) {
                        doCambioMi(new Naipe[]{mz.getNaipe(), mz.getNaipe(), mz.getNaipe()});

                        return true;
                    }
                }
        ));


    }

    public void addAction(Action action, float at) {
        stage.addAction(Actions.sequence(
                delay(at),
                action
        ));

    }

    private void testSay() {
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                say(1, "No tengo par", 0);
                return true;
            }
        }, 4f);
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                say(2, "No tengo juego", 0);
                return true;
            }
        }, 5f);
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                say(3, "Quiero mus", 0);
                return true;
            }
        }, 6f);
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                say(0, "Venga, jueguemos", 0);
                return true;
            }
        }, 7f);

    }

    protected void testReparto(Mazo mz) {
        doReparto(new Naipe[]{mz.getNaipe(), mz.getNaipe(), mz.getNaipe(), mz.getNaipe()}, JUGADOR_MANO);
    }

    protected void testCambiaOtro() {
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                doCambioOtro(3, 3);
                return true;
            }
        }, 8f);
       addAction(new Action() {
            @Override
            public boolean act(float delta) {
                doCambioOtro(2, 1);
                return true;
            }
        }, 12f);

    }
    protected void testApuestas() {
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                apuestas.setPosition(0);
                return true;
            }
        }, 3f);
       addAction(new Action() {
            @Override
            public boolean act(float delta) {
                apuestas.setTextValue(0, "Pasado");
                return true;
            }
        }, 5f);
      addAction(new Action() {
            @Override
            public boolean act(float delta) {
                apuestas.setPosition(1);
                return true;
            }
        }, 6f);
      addAction(new Action() {
            @Override
            public boolean act(float delta) {
                apuestas.setTextValue(1, "Apuesta(10)");
                return true;
            }
        }, 8f);

        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                apuestas.setPosition(2);
                return true;
            }
        }, 9f);
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                apuestas.setTextValue(2, "Cobrada(12)");
                return true;
            }
        }, 10f);
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                apuestas.setPosition(3);
                return true;
            }
        }, 11f);
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                apuestas.setTextValue(3, "Cobrada(1)");
                return true;
            }
        }, 12f);
       addAction(new Action() {
            @Override
            public boolean act(float delta) {
                apuestas.clear();
                return true;
            }
        }, 15f);
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                apuestas.setPosition(0);
                return true;
            }
        }, 17f);
        addAction(new Action() {
            @Override
            public boolean act(float delta) {
                apuestas.setTextValue(0, "Pasado");
                return true;
            }
        }, 19f);
    }
*/
}
