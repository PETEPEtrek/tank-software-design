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

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

import java.util.List;

//refactored class for level rendering
public class Renderer {
    private final Batch batch;
    private final MapRenderer levelRenderer;
    private final TiledMapTileLayer groundLayer;
    private final TileMovement tileMovement;
    private final List<DrawInterface> drawables;

    public Renderer(Batch batch, TiledMap level, List<DrawInterface> drawables) {
        this.batch = batch;
        this.drawables = drawables;
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
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

}