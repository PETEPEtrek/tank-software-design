package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.util.GdxGameUtils;
import ru.mipt.bit.platformer.util.TileMovement;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class TankWithHpGraphics implements DrawInterface {
    private HpToggle toggler;
    protected Rectangle rectangle;

    protected final Tank tank;

    private final Texture texture;
    private final TextureRegion textureRegion;

    private final TileMovement tileMovement;

    public TankWithHpGraphics(Tank tank, Texture texture, TileMovement tileMovement, HpToggle toggler) {
        this.tank = tank;
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(textureRegion);
        this.tileMovement = tileMovement;
        this.toggler = toggler;
    }

    @Override
    public void drawMove() {
        rectangle = tileMovement.moveRectangleBetweenTileCenters(rectangle, tank.getCoordinates(),
                tank.getDestinationCoordinates(), tank.getMovementProgress());
    }

    @Override
    public Object getDrawnObject() {
        return tank;
    }

    private static TextureRegion getHealthBar(float health, Color color) {
        Pixmap pixmap = new Pixmap((int) (90 * health / 100), 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, (int) (90 * health / 100), 20);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegion(texture);
    }

    @Override
    public void drawTexture(Batch batch) {
        if (toggler.getNum()) {
            TextureRegion healthBgBar = getHealthBar(100, Color.RED);
            TextureRegion healthLeftBar = getHealthBar(tank.getHp(), Color.GREEN);
            Rectangle hpRectangle = new Rectangle(rectangle);
            hpRectangle.y += 90;
            GdxGameUtils.drawTextureRegionUnscaled(batch, healthBgBar, hpRectangle, 0);
            GdxGameUtils.drawTextureRegionUnscaled(batch, healthLeftBar, hpRectangle, 0);
        }
        rectangle = tileMovement.moveRectangleBetweenTileCenters(rectangle, tank.getCoordinates(),
                tank.getDestinationCoordinates(), tank.getMovementProgress());

        drawTextureRegionUnscaled(batch, textureRegion, rectangle, tank.getRotation());
    }
}