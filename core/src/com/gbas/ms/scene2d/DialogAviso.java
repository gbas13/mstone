package com.gbas.ms.scene2d;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class DialogAviso extends Dialog {
    ExitableCallback exitableCallback;

    public interface ExitableCallback {
        void onCloseDialog();
    }

    public DialogAviso(String title, Skin skin) {
        super(title, skin);
        this.exitableCallback = null;
    }

    public DialogAviso(String title, Skin skin, ExitableCallback exitableCallback) {
        super(title, skin);
        this.exitableCallback = exitableCallback;
    }

    @Override
    protected void result(Object object) {
        if (exitableCallback != null) {
            exitableCallback.onCloseDialog();
        }
        super.result(object);
    }
}
