package ru.mipt.bit.platformer.abstractions;

import ru.mipt.bit.platformer.levelbuilder.Level;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Direction.Direction;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import ru.mipt.bit.platformer.collisions.FindCollisions;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank implements Collidability {
    private final Level level;
    private final float movementSpeed;
    private float playerMovementProgress;
    private float playerRotation;
    private Direction currentDirection;
    private final FindCollisions collisionFinder;
    private float hp = 3;
    private long lastShoot = new Date().getTime();
    
    // player current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    private GridPoint2 playerCoordinates;
    // which tile the player want to go next
    private GridPoint2 playerDestinationCoordinates;


    public Tank( Level level,
                 Direction currentDirection,
                 FindCollisions collisionFinder,
                 float playerMovementProgress,
                 float movementSpeed,
                 GridPoint2 playerCoordinates, 
                 GridPoint2 playerDestinationCoordinates) {
        this.level = level;
        this.movementSpeed = movementSpeed;
        this.collisionFinder = collisionFinder;
        this.playerCoordinates = playerCoordinates;
        this.playerDestinationCoordinates = playerDestinationCoordinates;
        this.playerMovementProgress = playerMovementProgress;
        this.currentDirection = currentDirection;
    }

    public float getHp() {
        return hp;
    }

    public void shoot() {
        if (!canChootInThisTick()) return;
        Bullet bullet = new Bullet(collisionFinder, level, this, currentDirection);
        level.registerBulletCreation(bullet);
        collisionFinder.addCollidable(bullet);
    }

    private boolean canChootInThisTick() {
        long nowDate = new Date().getTime();
        if (nowDate - lastShoot > 1000) {
            lastShoot = nowDate;
            return true;
        }
        return false;
    }

    public float getPlayerMovementProgress() {
        return playerMovementProgress;
    }

    public float getPlayerRotation() {
        return playerRotation;
    }

    public GridPoint2 getPlayerCoordinates() {
        return playerCoordinates;
    }

    public GridPoint2 getPlayerDestinationCoordinates() {
        return playerDestinationCoordinates;
    }

    public boolean isMoving() {
        return isEqual(playerMovementProgress, 1f);
    }

    private boolean hasCollision() {
        return collisionFinder.hasCollisions(this);
    }

    // function for moving in all 4 directions
    public void move(Direction direction) {
        if (!isEqual(playerMovementProgress, 1f)) {
            return;
        }

        var directionVector = direction.getChangeVector();
        playerDestinationCoordinates.x += directionVector.x;
        playerDestinationCoordinates.y += directionVector.y;

        if (hasCollision()) {
            playerDestinationCoordinates.x -= directionVector.x;
            playerDestinationCoordinates.y -= directionVector.y;
        } else {
            playerMovementProgress = 0f;
        }
        playerRotation = direction.getRotation();
        currentDirection = direction;
    }

    public void processMovementProgress(float deltaTime) {
        playerMovementProgress = continueProgress(playerMovementProgress, deltaTime, movementSpeed);
        if (isEqual(playerMovementProgress, 1f)) {
            // record that the player has reached his/her destination
            playerCoordinates.set(playerDestinationCoordinates);
        }
    }

    @Override
    public Collection<GridPoint2> getCoordinateList() {
        return Arrays.asList(playerCoordinates, playerDestinationCoordinates);
    }

    @Override
    public void registerDamage() {
        hp--;
        if (hp <= 0) {
            collisionFinder.deleteCollidable(this);
            level.registerTankDestruction(this);
        }
    }

}
