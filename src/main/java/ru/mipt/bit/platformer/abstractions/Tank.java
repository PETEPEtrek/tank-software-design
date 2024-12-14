package ru.mipt.bit.platformer.abstractions;

import ru.mipt.bit.platformer.levelbuilder.Level;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Direction.Direction;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import ru.mipt.bit.platformer.collisions.FindCollisions;
import ru.mipt.bit.platformer.ai.IAbstraction;
import ru.mipt.bit.platformer.ai.commands.IShoot;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank implements Collidability, IAbstraction, IShoot, Moving {
    private final Level level;
    private final float movementSpeed;
    private float tankMovementProgress;
    private float tankRotation;
    private Direction currentDirection;
    private final FindCollisions collisionFinder;
    private float hp = 3;
    private long lastShoot = new Date().getTime();
    
    // tank current position coordinates on level 10x8 grid (e.g. x=0, y=1)
    private GridPoint2 tankCoordinates;
    // which tile the tank want to go next
    private GridPoint2 tankDestinationCoordinates;


    public Tank( Level level,
                 Direction currentDirection,
                 FindCollisions collisionFinder,
                 float tankMovementProgress,
                 float movementSpeed,
                 GridPoint2 tankCoordinates, 
                 GridPoint2 tankDestinationCoordinates) {
        this.level = level;
        this.movementSpeed = movementSpeed;
        this.collisionFinder = collisionFinder;
        this.tankCoordinates = tankCoordinates;
        this.tankDestinationCoordinates = tankDestinationCoordinates;
        this.tankMovementProgress = tankMovementProgress;
        this.currentDirection = currentDirection;
    }

    public float getHp() {
        return hp;
    }
    @Override
    public void shoot() {
        if (!canChootInThisTick()) return;
        Bullet bullet = new Bullet(collisionFinder, level, this, currentDirection);
        level.registerBulletCreation(bullet);
    }

    private boolean canChootInThisTick() {
        long nowDate = new Date().getTime();
        if (nowDate - lastShoot > 1000) {
            lastShoot = nowDate;
            return true;
        }
        return false;
    }

    public float getMovementProgress() {
        return tankMovementProgress;
    }
    @Override
    public float getRotation() {
        return tankRotation;
    }
    @Override
    public GridPoint2 getCoordinates() {
        return tankCoordinates;
    }

    public GridPoint2 getDestinationCoordinates() {
        return tankDestinationCoordinates;
    }

    public boolean isMoving() {
        return isEqual(tankMovementProgress, 1f);
    }

    private boolean hasCollision() {
        return collisionFinder.hasCollisions(this);
    }

    // function for moving in all 4 directions
    public void move(Direction direction) {
        if (!isEqual(tankMovementProgress, 1f)) {
            return;
        }

        var directionVector = direction.getChangeVector();
        tankDestinationCoordinates.x += directionVector.x;
        tankDestinationCoordinates.y += directionVector.y;

        if (hasCollision()) {
            tankDestinationCoordinates.x -= directionVector.x;
            tankDestinationCoordinates.y -= directionVector.y;
        } else {
            tankMovementProgress = 0f;
        }
        tankRotation = direction.getRotation();
        currentDirection = direction;
    }
    @Override
    public void processMovementProgress(float deltaTime) {
        tankMovementProgress = continueProgress(tankMovementProgress, deltaTime, movementSpeed);
        if (isEqual(tankMovementProgress, 1f)) {
            // record that the tank has reached his/her destination
            tankCoordinates.set(tankDestinationCoordinates);
        }
    }

    @Override
    public Collection<GridPoint2> getCoordinateList() {
        return Arrays.asList(tankCoordinates, tankDestinationCoordinates);
    }

    @Override
    public void registerDamage() {
        hp--;
        if (hp <= 0) {
            level.registerTankDestruction(this);
        }
    }

}
