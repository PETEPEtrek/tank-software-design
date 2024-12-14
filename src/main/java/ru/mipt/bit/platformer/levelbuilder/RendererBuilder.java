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
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.mipt.bit.platformer.ai.IAbstraction;

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
    private final List<IRenderer> drawRenderers;

    public RendererBuilder(String levelConfigFileName,
                           String tankTextureFile,
                           String treeTextureFile,
                           String bulletTextureFile,
                           HpToggle showHp, List<IRenderer> drawRenderers) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("appContext.xml");

        TmxMapLoader loader = ctx.getBean("tmxmaploader", TmxMapLoader.class);
        var lvl = loader.load(levelConfigFileName);
        var batch = ctx.getBean("spritebatch", SpriteBatch.class);
        tankTexture = new Texture(tankTextureFile);
        this.showHp = showHp;
        textures.add(tankTexture);
        treeTexture = new Texture(treeTextureFile);
        bulletTexture = new Texture(bulletTextureFile);
        textures.add(treeTexture);
        textures.add(bulletTexture);

        renderer = new Renderer(this, batch, lvl, new CopyOnWriteArrayList<>());

        for (var drawRenderer: drawRenderers) {
            drawRenderer.setRenderer(renderer);
        }
        this.drawRenderers = drawRenderers;
    }

    public List<Texture> getTextures() {
        return textures;
    }

    public Renderer generateRenderer(ILevelBuilder levelBuilder) {
        generateGraphics(levelBuilder);
        return renderer;
    }

    private void generateGraphics(ILevelBuilder levelBuilder) {
        List<IAbstraction> abstractions = levelBuilder.getLevel().getAbstractions();
        for (IAbstraction abstraction : abstractions) {
            for (IRenderer drawRenderer: drawRenderers) {
                drawRenderer.draw(abstraction);
            }
        }
    }

    public HpToggle getHpToggle() {
        return showHp;
    }
}
