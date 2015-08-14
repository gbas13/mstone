package com.gbas.ms.scene2d;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.gbas.ms.Assets;
import com.gbas.ms.model.CartaEnJuego;

/**
 * Created with IntelliJ IDEA.
 * User: teodorico
 * Date: 5/09/13
 * Time: 0:02
 * To change this template use File | Settings | File Templates.
 */

public class Naipe2d extends Actor implements Disposable {

    private Pixmap pixmap;
    private static Pixmap pixmapReverse;
    private Texture texture;
    private boolean dirty;
    boolean reverse;


    public Naipe2d(CartaEnJuego cartaEnJuego) {
        pixmap = new Pixmap(Gdx.files.getFileHandle(
                "blank.png", Files.FileType.Internal));
/*
        pixmapReverse = new Pixmap(Gdx.files.getFileHandle(
                "reverse.png", Files.FileType.Internal));
*/


        // draw moneda
        pixmap.drawPixmap(Assets.iconsPixmap, 7, 1, 2, 115,18,18);
        // draw nombre
        Assets.drawStringOnPixmap(cartaEnJuego.getCartaDef().getNombre(), pixmap, 21, 2);
        // draw atake
        pixmap.drawPixmap(Assets.iconsPixmap, 70, 1, 3, 83,18,20);
        // draw vida
        pixmap.drawPixmap(Assets.iconsPixmap, 88, 1, 2, 63,20,18);
        // mdraw imagen
        Pixmap imagenCarta = Assets.getImagenCarta(cartaEnJuego.getCartaDef().getImage());
        pixmap.drawPixmap(imagenCarta, pixmap.getWidth()/2-imagenCarta.getWidth()/2, 22);

        // Descripcion
        Assets.drawMultiStringOnPixmap("Si hay más manifestantes que policias, llamará a [#FF0000] un refuerzo [] cada turno", pixmap, 5, 110);

        this.texture = new Texture(pixmap);
        this.texture.bind(1);
        this.dirty = false;


        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
        setWidth(pixmap.getWidth());
        setHeight(pixmap.getHeight());


/*
        super(flipDrawable, Scaling.none, Align.bottom | Align.left);

        this.cartaEnJuego = cartaEnJuego;

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectable && (!flipped || selected)) {
                    doFlipMove();
                }
            }
        });
*/
    }

    /** Write the pixmap onto the texture if the pixmap has changed. */
    public void update() {
        if (dirty) {
            //makePixmap();
            texture.draw(pixmap, 0, 0);
            dirty = false;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        update();
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation(), 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    @Override
    public void dispose() {
        pixmap.dispose();
    }

    public void cogeCartaDeMazo(int fromX, int fromY, int toX, int toY) {

    }


/*
    @Override
    public void act(float delta) {
        super.act(delta);
        //TextureRegion frame = tiltAnimationCaminar.getKeyFrame(tiltAnimationStateTimeCaminar += delta, true);
        setDrawable(flipped ? flipDrawable : cartaDrawable);
    }

    public boolean isSelected() {
        return selected;
    }

    public void reverse() {
        flipped = !flipped;
    }

    public static Naipe2d create(CartaEnJuego cartaEnJuego) {
        if (flipDrawable == null) {
            final TextureRegion textureRegion = new TextureRegion(Assets.mainTexture, Constantes.CARTAS_SIZEX * 1, 4 * Constantes.CARTAS_SIZEY, Constantes.CARTAS_SIZEX, Constantes.CARTAS_SIZEY);
            flipDrawable = new TextureRegionDrawable(textureRegion);
        }
        Naipe2d naipe2d = new Naipe2d(cartaEnJuego);
         final TextureRegion textureRegion = new TextureRegion(Assets.mainTexture, Constantes.CARTAS_SIZEX * numero, palo * Constantes.CARTAS_SIZEY, Constantes.CARTAS_SIZEX, Constantes.CARTAS_SIZEY);
        naipe2d.cartaDrawable = new TextureRegionDrawable(textureRegion);
        naipe2d.setOrigin(naipe2d.getWidth() / 2, naipe2d.getHeight() / 2);
        return naipe2d;
    }

    public void changeNaipe(Naipe naipesNuevo, Texture texture) {
        naipe = naipesNuevo;
        int palo = naipe.getPalo().ordinal();
        int numero = naipe.getValor().ordinal();
        final TextureRegion textureRegion = new TextureRegion(texture, Constantes.CARTAS_SIZEX * numero, palo * Constantes.CARTAS_SIZEY, Constantes.CARTAS_SIZEX, Constantes.CARTAS_SIZEY);
        cartaDrawable = new TextureRegionDrawable(textureRegion);
        flipped = true;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void doFlip() {
        final Action completeAction = new Action() {
            public boolean act(float delta) {
                reverse();
                return true;
            }
        };

        addAction(Actions.sequence(
                Actions.parallel(
                        scaleTo(0.3f, 0.3f, .25f),
                        alpha(0.0f, .25f)
                ),
                completeAction,
                Actions.parallel(
                        scaleTo(1.0f, 1.0f, .25f),
                        alpha(1.0f, .25f)
                )
        ));

    }
    public Action doFlip2() {
        final Action completeAction = new Action() {
            public boolean act(float delta) {
                reverse();
                return true;
            }
        };

        return Actions.sequence(
                Actions.parallel(
                        scaleTo(0.1f, 1.0f, .25f),
                        alpha(0.1f, .25f)
                ),
                completeAction,
                Actions.parallel(
                        scaleTo(1.0f, 1.0f, .25f),
                        alpha(1.0f, .25f)
                )
        );

    }
    public void doFlipMove() {
        final Action completeAction = new Action() {
            public boolean act(float delta) {
                reverse();
                selected = !selected;
                return true;
            }
        };
        addAction(Actions.parallel(
                Actions.moveBy(0, selected ? -Constantes.OFFSET_SELECTOR : Constantes.OFFSET_SELECTOR, .5f),
                Actions.sequence(
                        Actions.parallel(
                                scaleTo(0.1f, 1.0f, .25f),
                                alpha(0.1f, .25f)
                        ),
                        completeAction,
                        Actions.parallel(
                                scaleTo(1.0f, 1.0f, .25f),
                                alpha(1.0f, .25f)
                        )
                )
        ));


    }
*/

}
