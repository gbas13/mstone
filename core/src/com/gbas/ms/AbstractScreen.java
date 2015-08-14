package com.gbas.ms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.gbas.ms.scene2d.DialogAviso;

/**
 * Created with IntelliJ IDEA.
 * User: teodorico
 * Date: 7/08/13
 * Time: 14:53
 * Screen comun para todas las screenes
 */
public abstract class AbstractScreen implements Screen {
    protected static final int MENU_VIEWPORT_WIDTH = 800, MENU_VIEWPORT_HEIGHT = 480;

    public final MSGame game;

    protected SpriteBatch batch;
    protected Stage stage;

    public AbstractScreen(MSGame game) {
        this.game = game;

        this.stage = new Stage(new StretchViewport(MENU_VIEWPORT_WIDTH, MENU_VIEWPORT_HEIGHT));
    }


    protected String getName() {
        return "MSGame: AbstractScreen";
    }

    @Override
    public void render(float delta) {

        stage.act(delta);
        // the following code clears the screen with the given RGB color (black)
        Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
       stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(MSGame.LOG, "Resizing screen: " + getName() + " to: " + width + " x " + height);

               // resize the stage
        stage.getViewport().update(width, height, true);
        //stage.setViewport(new StretchViewport(MENU_VIEWPORT_WIDTH, MENU_VIEWPORT_HEIGHT));
    }

    @Override
    public void show() {
        Gdx.app.log( MSGame.LOG, "Showing screen: " + getName() );

        // set the stage as the input processor
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        Gdx.app.log( MSGame.LOG, "Hiding screen: " + getName() );

        dispose();
    }

    @Override
    public void pause() {
        Gdx.app.log( MSGame.LOG, "Pausing screen: " + getName() );

    }

    @Override
    public void resume() {
        Gdx.app.log( MSGame.LOG, "Resuming screen: " + getName() );

    }

    @Override
    public void dispose() {
        Gdx.app.log( MSGame.LOG, "Disposing screen: " + getName() );

        // dispose the collaborators

        if (batch!=null) batch.dispose();
    }

    public SpriteBatch getBatch()
    {
        if( batch == null ) {
            batch = new SpriteBatch();
        }
        return batch;
    }

    public void showMessage(String txt) {
        DialogAviso dlg = new DialogAviso("Atención", Assets.getSkin());
        dlg.button("Cerrar");
        dlg.text(txt);
        dlg.pack();
        dlg.setPosition(stage.getWidth()/2-dlg.getWidth()/2, stage.getHeight()/2-dlg.getHeight()/2);
        stage.addActor(dlg);
    }

    public void showMessageAndExit(String txt) {
        DialogAviso dlg = new DialogAviso("Atención", Assets.getSkin(), new DialogAviso.ExitableCallback() {
            @Override
            public void onCloseDialog() {
                Gdx.app.exit();
                System.exit(-1);

            }
        });
        dlg.button("Cerrar");
        dlg.text(txt);
        dlg.pack();
        dlg.setPosition(stage.getWidth()/2-dlg.getWidth()/2, stage.getHeight()/2-dlg.getHeight()/2);
        stage.addActor(dlg);
    }

    TextField.TextFieldListener listenerCloseOnExit = new TextField.TextFieldListener() {
        @Override
        public void keyTyped(TextField textField, char key) {
            if (key == '\n' || key == '\r') {
                textField.getOnscreenKeyboard().show(false);
            }
        }
    };

    public Label.LabelStyle getLabelStyleFromRelativePosition(int origen) {
        return origen < 0 ?
                Assets.getSkin().get(Label.LabelStyle.class) :
                Assets.getSkin().get("label", Label.LabelStyle.class);
    }


}
