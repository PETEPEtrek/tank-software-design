package ru.mipt.bit.platformer.levelbuilder;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.graphics.TankGraphics;
import ru.mipt.bit.platformer.graphics.TankWithHpGraphics;
import ru.mipt.bit.platformer.ai.IAbstraction;
import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.graphics.HpToggle;

public class TankRenderer extends IRenderer{
    private final Texture texture;
    private final HpToggle showHp;

    public TankRenderer(Texture texture, HpToggle showHp) {
        this.texture  = texture;
        this.showHp = showHp;
    }

    @Override
    public void draw(IAbstraction abs) {
        if (!(abs instanceof Tank)) {
            return;
        }

        TankWithHpGraphics tankGraphics = new TankWithHpGraphics((Tank) abs, texture, renderer.getTileMovement(), showHp);
        renderer.addDrawableObject(tankGraphics);
    }
    @Override
    public void dispose() {
    	texture.dispose();
    }
}