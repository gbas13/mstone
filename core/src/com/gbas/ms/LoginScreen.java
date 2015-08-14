package com.gbas.ms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gbas.ms.model.JugadorNet;
import com.gbas.ms.net.NOLoginReq;
import com.gbas.ms.net.NOVersionReq;


public class LoginScreen extends AbstractScreen {
    String txtLogin = "";
    String txtPassword="";
    private Label labelVersion;

    public LoginScreen(MSGame musgame) {
        super(musgame);


        final Preferences prefs = Gdx.app.getPreferences("MSGameGDX Preferences");

        Label headLabel = new Label("IDENTIFÍCATE", Assets.getSkin(), "shadow");


        txtLogin = prefs.getString("login", "");
        Label nameLabel = new Label("Nombre :", Assets.getSkin());
        final TextField nameText = new TextField(txtLogin, Assets.getSkin());
        nameText.setTextFieldListener(listenerCloseOnExit);

        Label passwordLabel = new Label("Contraseña :", Assets.getSkin());
        txtPassword = prefs.getString("pass", "");
        final TextField addressText = new TextField(txtPassword, Assets.getSkin());
        addressText.setPasswordMode(true);
        addressText.setTextFieldListener(listenerCloseOnExit);

        final CheckBox checkBoxRecordar = new CheckBox("Recordar", Assets.getSkin());
        if (txtLogin.length() > 0 || txtPassword.length() > 0) {
            checkBoxRecordar.setChecked(true);
        }

        TextButton btnLogin = new TextButton("Conectar", Assets.getSkin());
        TextButton btnAnonimo = new TextButton("Anónimo", Assets.getSkin());


        Group grp = new Group();
        grp.setSize(800, 480);


        stage.addActor(Assets.fondoTexture);
        stage.addActor(grp);

        nameLabel.setPosition(390-nameLabel.getWidth(), 345-80);
        nameText.setPosition(410, 340-80);
        passwordLabel.setPosition(390-passwordLabel.getWidth(), 285-80);
        addressText.setPosition(410, 280-80);
        checkBoxRecordar.setPosition(390, 220-60);
        btnLogin.setPosition(380-btnLogin.getWidth(), 40);
        btnAnonimo.setPosition(420, 40);

        labelVersion=new Label("", Assets.getSkin(), "pl_0");
        labelVersion.setPosition(700, 24);

        headLabel.setPosition(400-headLabel.getWidth()/2, 345);

        grp.addActor(headLabel);
        grp.addActor(nameLabel);
        grp.addActor(nameText);
        grp.addActor(passwordLabel);
        grp.addActor(addressText);
        grp.addActor(checkBoxRecordar);
        grp.addActor(btnLogin);
        grp.addActor(btnAnonimo);
        grp.addActor(labelVersion);

        btnLogin.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!validaField(nameText.getText())) {
                    showMessage("Nombre"+MSGame.MESSAGEVALIDACION);
                    return;
                }
                if (!validaField(nameText.getText())) {
                    showMessage("Contraseña"+MSGame.MESSAGEVALIDACION);
                    return;
                }
                if (checkBoxRecordar.isChecked()) {
                    prefs.putString("login", nameText.getText());
                    prefs.putString("pass", addressText.getText());
                    prefs.flush();
                }
                game.netManager.send(new NOLoginReq(nameText.getText(), addressText.getText()));
            }
        });

        btnAnonimo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.netManager.send(new NOLoginReq());
            }
        });

        game.netManager.send(new NOVersionReq(game.version));

    }

    public void versionResponse(boolean valid, String txtInvalid) {
        if (valid) {
            labelVersion.setText("Versión: " + game.version);
        } else {
            game.messageAndExit(txtInvalid);
        }
    }

    public void loginResponse(JugadorNet jugadorNet, String txtProblem) {
        if (jugadorNet !=null) {
            game.jugadorNet = jugadorNet;
            game.setScreen(new PartidasScreen(game));
        } else {
            showMessage(txtProblem);
        }

    }

    public boolean validaField(String text) {
        if (text.length() > 3 && text.length() < 13) {
            for (int i = 0; i < text.length(); i++) {
                if (!Character.isLetterOrDigit(text.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
