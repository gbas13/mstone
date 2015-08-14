package com.gbas.ms.scene2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
* Created by gbas on 15/03/14.
*/
public class Frame extends Group {
    private Drawable background;

    public Frame(Drawable background) {
        this.background = background;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawBackground(batch, parentAlpha);
        super.draw(batch, parentAlpha);
    }

    /** Called to draw the background, before clipping is applied (if enabled). Default implementation draws the background
     * drawable. */
    protected void drawBackground (Batch batch, float parentAlpha) {
        if (background != null) {
            Color color = getColor();
            batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
            background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
    }

    public void setBackground(Drawable dialogbox) {
        background = dialogbox;
    }
}
