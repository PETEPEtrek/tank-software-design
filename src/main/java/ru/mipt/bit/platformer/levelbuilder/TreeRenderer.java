package ru.mipt.bit.platformer.levelbuilder;

import com.badlogic.gdx.graphics.Texture;
import ru.mipt.bit.platformer.graphics.TreeGraphics;
import ru.mipt.bit.platformer.ai.IAbstraction;
import ru.mipt.bit.platformer.abstractions.Tree;

public class TreeRenderer extends IRenderer{
    private final Texture texture;

    public TreeRenderer(Texture texture) {
        this.texture  = texture;
    }

    @Override
    public void draw(IAbstraction abs) {
        if (!(abs instanceof Tree)) {
            return;
        }
        Tree tree = (Tree) abs;
        TreeGraphics treeGraphics = new TreeGraphics((Tree) abs, texture, renderer.getTileMovement());
        renderer.addDrawableObject(treeGraphics);
        renderer.moveRectangleAtTileCenter(treeGraphics.getRectangle(), tree.getCoordinates());
    }
    @Override
    public void dispose() {
        texture.dispose();
    }
}