package ru.mipt.bit.platformer.levelbuilder;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.graphics.BulletGraphics;
import ru.mipt.bit.platformer.ai.IAbstraction;
import ru.mipt.bit.platformer.abstractions.Bullet;

public class BulletRenderer extends IRenderer{
    private final Texture texture;

    public BulletRenderer(Texture texture) {
        this.texture  = texture;
    }

    @Override
    public void draw(IAbstraction abs) {
        if (!(abs instanceof Bullet)) {
            return;
        }

        BulletGraphics bulletGraphics = new BulletGraphics((Bullet) abs, texture, renderer.getTileMovement());
        renderer.addDrawableObject(bulletGraphics);
    }
    @Override
    public void dispose() {

    }
}