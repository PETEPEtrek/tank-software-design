package ru.mipt.bit.platformer.abstractions;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Direction.Direction;
import ru.mipt.bit.platformer.collisions.FindCollisions;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class TankTest {

    @Test
    public void tankRotateMoveUpNoObstacle() {
        FindCollisions collisionFinder = new FindCollisions(new ArrayList<>());
        Tank tank = new Tank(Direction.UP, collisionFinder, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionFinder.addCollidable(tank);
        tank.move(Direction.UP);

        assertEquals(new GridPoint2(1, 1), tank.getPlayerDestinationCoordinates());
        assertEquals(90f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankRotateNotMoveUpObstacle() {
        FindCollisions collisionFinder = new FindCollisions(new ArrayList<>());
        Tank tank = new Tank(Direction.UP, collisionFinder, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionFinder.addCollidable(tank);
        Tree tree = new Tree(new GridPoint2(1, 1), 0f);
        collisionFinder.addCollidable(tree);
        tank.move(Direction.UP);

        assertEquals(new GridPoint2(1, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(90f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankRotateMoveRightNoObstacle() {
        FindCollisions collisionFinder = new FindCollisions(new ArrayList<>());
        Tank tank = new Tank(Direction.UP, collisionFinder, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionFinder.addCollidable(tank);
        tank.move(Direction.RIGHT);

        assertEquals(new GridPoint2(2, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(0f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankRotateNotMoveRightObstacle() {
        FindCollisions collisionFinder = new FindCollisions(new ArrayList<>());
        Tank tank = new Tank(Direction.UP, collisionFinder, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionFinder.addCollidable(tank);
        Tree tree = new Tree(new GridPoint2(2, 0), 0f);
        collisionFinder.addCollidable(tree);
        tank.move(Direction.RIGHT);

        assertEquals(new GridPoint2(1, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(0f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankRotateMoveDownNoObstacle() {
        FindCollisions collisionFinder = new FindCollisions(new ArrayList<>());
        Tank tank = new Tank(Direction.UP, collisionFinder, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionFinder.addCollidable(tank);
        tank.move(Direction.DOWN);

        assertEquals(new GridPoint2(1, -1), tank.getPlayerDestinationCoordinates());
        assertEquals(-90f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankRotateNotMoveDownObstacle() {
        FindCollisions collisionFinder = new FindCollisions(new ArrayList<>());
        Tank tank = new Tank(Direction.UP, collisionFinder, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionFinder.addCollidable(tank);
        Tree tree = new Tree(new GridPoint2(1, -1), 0f);
        collisionFinder.addCollidable(tree);
        tank.move(Direction.DOWN);

        assertEquals(new GridPoint2(1, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(-90f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankRotateMoveLeftNoObstacle() {
        FindCollisions collisionFinder = new FindCollisions(new ArrayList<>());
        Tank tank = new Tank(Direction.UP, collisionFinder, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionFinder.addCollidable(tank);
        tank.move(Direction.LEFT);

        assertEquals(new GridPoint2(0, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(-180f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankRotateNotMoveLeftObstacle() {
        FindCollisions collisionFinder = new FindCollisions(new ArrayList<>());
        Tank tank = new Tank(Direction.UP, collisionFinder, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        collisionFinder.addCollidable(tank);
        Tree tree = new Tree(new GridPoint2(0, 0), 0f);
        collisionFinder.addCollidable(tree);
        tank.move(Direction.LEFT);

        assertEquals(new GridPoint2(1, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(-180f, tank.getPlayerRotation(), 0.1f);
    }
}