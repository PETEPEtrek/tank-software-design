package ru.mipt.bit.platformer.levelbuilder;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.abstractions.Tree;
import ru.mipt.bit.platformer.graphics.Renderer;
import ru.mipt.bit.platformer.graphics.TankGraphics;
import ru.mipt.bit.platformer.graphics.TreeGraphics;

import java.util.ArrayList;
import java.util.List;

//refactored class for rendering textures
public class RendererBuilder {
    private final Renderer renderer;
    private final List<Texture> textures = new ArrayList<>();
    private final Texture tankTexture;
    private final Texture treeTexture;

    public RendererBuilder(String levelConfigFileName,
                           String tankTextureFile,
                           String treeTextureFile) {
        renderer = new Renderer(new SpriteBatch(), new TmxMapLoader().load(levelConfigFileName), new ArrayList<>());
        tankTexture = new Texture(tankTextureFile);
        textures.add(tankTexture);
        treeTexture = new Texture(treeTextureFile);
        textures.add(treeTexture);
    }

    public List<Texture> getTextures() {
        return textures;
    }

    public Renderer generateRenderer(ILevelBuilder levelBuilder) {
        generateTankGraphics(levelBuilder);
	generateAiTanksGraphics(levelBuilder);
        generateTreesGraphics(levelBuilder);
        return renderer;
    }

    private void generateTankGraphics(ILevelBuilder levelBuilder) {
        Tank tank = levelBuilder.getTank();
        TankGraphics tankGraphics = new TankGraphics(tank, tankTexture, renderer.getTileMovement());
        renderer.addDrawableObject(tankGraphics);
    }

    private void generateTreesGraphics(ILevelBuilder levelBuilder) {
        List<Tree> trees = levelBuilder.getTrees();
        
        for (Tree tree : trees) {
            TreeGraphics treeGraphics = new TreeGraphics(tree, treeTexture, renderer.getTileMovement());
            renderer.addDrawableObject(treeGraphics);
            renderer.moveRectangleAtTileCenter(treeGraphics.getRectangle(), tree.getCoordinates());
        }
    }

    private void generateAiTanksGraphics(ILevelBuilder levelBuilder) {
        generateTankGraphics(levelBuilder);
        List<Tank> aiTanks = levelBuilder.getAiTanks();
        for (Tank tank : aiTanks) {
            TankGraphics tankGraphics = new TankGraphics(tank, tankTexture, renderer.getTileMovement());
            renderer.addDrawableObject(tankGraphics);
        }
    }
}
