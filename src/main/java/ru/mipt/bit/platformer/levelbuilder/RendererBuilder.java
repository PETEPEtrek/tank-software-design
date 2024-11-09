package ru.mipt.bit.platformer.levelbuilder;

import java.util.concurrent.CopyOnWriteArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.abstractions.Tree;
import ru.mipt.bit.platformer.abstractions.Bullet;
import ru.mipt.bit.platformer.graphics.Renderer;
import ru.mipt.bit.platformer.graphics.TankGraphics;
import ru.mipt.bit.platformer.graphics.TreeGraphics;
import ru.mipt.bit.platformer.graphics.BulletGraphics;
import ru.mipt.bit.platformer.graphics.HpToggle;
import ru.mipt.bit.platformer.graphics.TankWithHpGraphics;


import java.util.ArrayList;
import java.util.List;

//refactored class for rendering textures
public class RendererBuilder {
    private final Renderer renderer;
    private final List<Texture> textures = new ArrayList<>();
    private final Texture tankTexture;
    private final Texture treeTexture;
    private final Texture bulletTexture;
    private final HpToggle showHp;

    public RendererBuilder(String levelConfigFileName,
                           String tankTextureFile,
                           String treeTextureFile,
                           String bulletTextureFile,
                           HpToggle showHp) {
        renderer = new Renderer(this, new SpriteBatch(), new TmxMapLoader().load(levelConfigFileName), new CopyOnWriteArrayList<>());
        tankTexture = new Texture(tankTextureFile);
        this.showHp = showHp;
        textures.add(tankTexture);
        treeTexture = new Texture(treeTextureFile);
        bulletTexture = new Texture(bulletTextureFile);
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
        Tank tank = levelBuilder.getLevel().getTank();
        TankGraphics tankGraphics = new TankWithHpGraphics(tank, tankTexture, renderer.getTileMovement(), showHp);
        renderer.addDrawableObject(tankGraphics);
    }

    private void generateTreesGraphics(ILevelBuilder levelBuilder) {
        List<Tree> trees = levelBuilder.getLevel().getTrees();
        
        for (Tree tree : trees) {
            TreeGraphics treeGraphics = new TreeGraphics(tree, treeTexture, renderer.getTileMovement());
            renderer.addDrawableObject(treeGraphics);
            renderer.moveRectangleAtTileCenter(treeGraphics.getRectangle(), tree.getCoordinates());
        }
    }

    private void generateAiTanksGraphics(ILevelBuilder levelBuilder) {
        generateTankGraphics(levelBuilder);
        List<Tank> aiTanks = levelBuilder.getLevel().getAiTanks();
        for (Tank tank : aiTanks) {
            TankGraphics tankGraphics = new TankWithHpGraphics(tank, tankTexture, renderer.getTileMovement(), showHp);
            renderer.addDrawableObject(tankGraphics);
        }
    }

    public void generateBulletGraphics(Bullet bullet) {
        BulletGraphics bulletGraphics = new BulletGraphics(bullet, bulletTexture, renderer.getTileMovement());
        renderer.addDrawableObject(bulletGraphics);
    }
}
