package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.util.GdxGameUtils;
import ru.mipt.bit.platformer.util.TileMovement;
import ru.mipt.bit.platformer.abstractions.Bullet;
import ru.mipt.bit.platformer.levelbuilder.RendererBuilder;
import ru.mipt.bit.platformer.eventmanager.EventListener;
import ru.mipt.bit.platformer.eventmanager.Events;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

import java.util.List;

//refactored class for level rendering
public class Renderer implements EventListener {
    private final Batch batch;
    private final MapRenderer levelRenderer;
    private final RendererBuilder rendererBuilder;
    private final TiledMapTileLayer groundLayer;
    private final TileMovement tileMovement;
    private final List<DrawInterface> drawables;

    public Renderer(RendererBuilder rendererBuilder, Batch batch, TiledMap level, List<DrawInterface> drawables) {
        this.batch = batch;
        this.drawables = drawables;
        this.rendererBuilder = rendererBuilder;
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }
    
    @Override
    public void update(Events event, Object object) {
        if (event.equals(Events.CREATE_BULLET)) {
            rendererBuilder.generateBulletGraphics((Bullet) object);
        }
        if (event.equals(Events.DELETE_BULLET) || event.equals(Events.DELETE_TANK)) {
            for (DrawInterface drawable : drawables) {

                if (drawable.getDrawnObject() == object) {
                    deleteDrawableObject(drawable);
                }
            }
            
        }
    }



    public void doRender() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
        
        for (DrawInterface drawable : drawables) {
            drawable.drawMove();
        }
        
        levelRenderer.render();

        batch.begin();
        for (DrawInterface drawable : drawables) {
            drawable.drawTexture(batch);
        }
        batch.end();
    }

    //for render builder
    public void moveRectangleAtTileCenter(Rectangle rectangle, GridPoint2 coordinates) {
        GdxGameUtils.moveRectangleAtTileCenter(groundLayer, rectangle, coordinates);
    }

    //for render builder
    public TileMovement getTileMovement() {
        return tileMovement;
    }

    //for render builder
    public void addDrawableObject(DrawInterface drawable) {
        drawables.add(drawable);
    }

    //for render builder
    private void deleteDrawableObject(DrawInterface drawable) {
        drawables.remove(drawable);
    }

}