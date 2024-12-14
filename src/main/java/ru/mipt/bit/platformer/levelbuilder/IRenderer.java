package ru.mipt.bit.platformer.levelbuilder;

import com.badlogic.gdx.utils.Disposable;
import ru.mipt.bit.platformer.ai.IAbstraction;
import ru.mipt.bit.platformer.graphics.Renderer;

public abstract class IRenderer implements Disposable {
    protected Renderer renderer;

    public void draw(IAbstraction abs) {
    }
    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

}