package com.gbas.ms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import java.util.HashMap;
import java.util.Map;

public class Assets {
    private final static String FILE_IMAGE_ATLAS = "skin/skin2.atlas";
    private final static String FILE_UI_SKIN = "skin/skin2.json";
    public static TextureAtlas imageAtlas;
    public static Skin skin;


    public static Texture mainTexture;
    public static Texture dlgTexture;
    public static Image fondoTexture;
    private static Texture tapeteTexture;
    private static Texture mesaTexture;
    private static Texture avatarTexture;

    public static NinePatch textureTapeteRegion;

    public static TextureRegion mesaJuego[];
    public static TextureRegion txtAvatar[];
    public static TextureRegion btnChange[];

    public static TextureRegion btnGreen[];
    public static TextureRegion btnCaramel[];
    public static TextureRegion btnGray[];
    public static TextureRegion btnGrayMini[];

    public static TextureRegion txtAmarracoOro[];
    public static TextureRegion txtAmarracoPlata[];
    public static TextureRegion txtBtnPressLeft[];
    public static TextureRegion txtBtnPressRight[];
    public static TextureRegion playVoice[];

    public static TextureRegion txtRegionFichaJugador;
    public static TextureRegion txtShadow;
    public static NinePatch dlg[];


    private static BitmapFont fontBlue;
    private static Pixmap fontBluePixmap;
    public static BitmapFont.BitmapFontData fontBlueData;
    private static BitmapFont fontMini;
    private static Pixmap fontMiniPixmap;
    public static BitmapFont.BitmapFontData fontMiniData;

    public static Pixmap iconsPixmap;
    private static Map<String, Pixmap> mapaImagenes=new HashMap<String, Pixmap>();

    public static Skin getSkin() {
        if (skin == null) {
            FileHandle skinFile = Gdx.files.internal(FILE_UI_SKIN);
            skin = new Skin(skinFile);
        }
        return skin;
    }

    public static void loadAll() {
        loadTextForPixmap();
        relaseResources();
        loadImages();
        loadButtons();
        loadAnimations();
        loadSoundsAndMusics();
    }

    private static void loadTextForPixmap() {
        fontBlue = new BitmapFont(Gdx.files.internal("skin/blueFont.fnt"));
        fontBlueData = fontBlue.getData();
        fontBluePixmap = new Pixmap(Gdx.files.internal(fontBlueData.imagePaths[0]));

        fontMini = new BitmapFont(Gdx.files.internal("skin/microFont.fnt"));
        fontMiniData = fontMini.getData();
        fontMiniData.markupEnabled = true;
        fontMiniPixmap = new Pixmap(Gdx.files.internal(fontMiniData.imagePaths[0]));

    }

    public static void drawStringOnPixmap(String toPrint, Pixmap pixmap, int px, int py) {
        for (int i = 0; i < toPrint.length(); i++) {
            BitmapFont.Glyph glyph = fontBlueData.getGlyph(toPrint.charAt(i));

            // draw the character onto our base pixmap
            pixmap.drawPixmap(fontBluePixmap, px, py,
                    glyph.srcX, glyph.srcY, glyph.width, glyph.height);

            px += 1 + glyph.width;
        }
    }
    public static void drawMultiStringOnPixmap(String toPrint, Pixmap pixmap, int px, int py) {
        GlyphLayout glyphLayout = new GlyphLayout(Assets.fontMini, toPrint, Assets.fontMini.getColor(), 104, Align.center, true);
        for (GlyphLayout.GlyphRun glyphRun : glyphLayout.runs) {
            int ppx=px;
            for (BitmapFont.Glyph glyph : glyphRun.glyphs) {
                // draw the character onto our base pixmap
                pixmap.drawPixmap(fontMiniPixmap, ppx, (int) (py + (-glyphRun.y)),
                        glyph.srcX, glyph.srcY, glyph.width, glyph.height);
                ppx += glyph.xadvance;

            }

        }
    }
    public static int tamanyoString(String toPrint) {
        int sizex=0;
        for (int i = 0; i < toPrint.length(); i++) {
            BitmapFont.Glyph glyph = fontBlueData.getGlyph(toPrint.charAt(i));

            sizex += 1 + glyph.width;
        }
        return sizex;
    }

    private static void relaseResources() {
        skin = null;
        imageAtlas = null;
    }

    public static void loadImages() {
        iconsPixmap = new Pixmap(Gdx.files.internal("icons.png"));


    }

    public static void loadButtons() {
    }

    public static void loadAnimations() {
    }

    public static void loadSoundsAndMusics() {
    }

    public static Pixmap getImagenCarta(String image) {
        Pixmap px = mapaImagenes.get(image);
        if (px == null) {
            px = new Pixmap(Gdx.files.internal(image));
            mapaImagenes.put(image, px);
        }
        return px;
    }
}
