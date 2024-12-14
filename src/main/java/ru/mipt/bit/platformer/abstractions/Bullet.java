package ru.mipt.bit.platformer.abstractions;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.ai.IAbstraction;
import ru.mipt.bit.platformer.collisions.FindCollisions;
import ru.mipt.bit.platformer.Direction.Direction;
import ru.mipt.bit.platformer.levelbuilder.Level;

import java.util.Collection;
import java.util.Collections;

import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Bullet implements Collidability, IAbstraction {
    private final Level level;

    private final float rotation;
    private float movementProgress = 0f;
    private float movementProgressCnt = 0f;

    private final Tank tank;
    private final FindCollisions collisionFinder;
    private final Direction direction;
    private final GridPoint2 coordinates;
    private final GridPoint2 toCoordinates;

    public Bullet(FindCollisions collisionFinder, Level level, Tank tank, Direction direction) {
        this.collisionFinder = collisionFinder;
        this.level = level;
        this.tank = tank;

        GridPoint2 directCoordinate = new GridPoint2(tank.getCoordinates());
        directCoordinate.add(direction.getChangeVector());
        this.coordinates = directCoordinate;

        this.toCoordinates = new GridPoint2(this.coordinates);
        this.rotation = tank.getRotation();

        this.direction = direction;

        this.toCoordinates.x += direction.getChangeVector().x * 10;
        this.toCoordinates.y += direction.getChangeVector().y * 10;
    }

    @Override
    public boolean findCollision(Collidability collidable) {
        for (GridPoint2 bulletCoordinates : this.getCoordinateList()) {
            for (GridPoint2 objectCoordinates : collidable.getCoordinateList()) {
                if (bulletCoordinates.equals(objectCoordinates)) {
                    
                    if (collidable != tank) {
                        collidable.registerDamage();
                    }

                    level.registerBulletDestruction(this);

                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public GridPoint2 getCoordinates() {
        return coordinates;
    }

     @Override
    public Collection<GridPoint2> getCoordinateList() {
        return Collections.singletonList(coordinates);
    }
    @Override
    public void processMovementProgress(float deltaTime) {
        if (collisionFinder.hasCollisions(this)) {
            return;
        }

        movementProgress = continueProgress(movementProgress, deltaTime, 1f);

        if (movementProgress - movementProgressCnt > 0.3f) {
            movementProgressCnt += .2f;
            coordinates.add(direction.getChangeVector());
        }
    }
    @Override
    public float getRotation() {
        return rotation;
    }

    public float getMovementProgress() {
        return movementProgress;
    }

    public GridPoint2 getToCoordinates() {
        return toCoordinates;
    }


}