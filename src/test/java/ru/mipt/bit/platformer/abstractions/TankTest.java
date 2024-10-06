package ru.mipt.bit.platformer.abstractions;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Direction.Direction;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class TankTest {

    @Test
    public void tankRotateMoveUpNoObstacle() {

        Tank tank = new Tank(Direction.UP, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        tank.moveUp(new GridPoint2(2, 2), Direction.UP);

        assertEquals(new GridPoint2(1, 1), tank.getPlayerDestinationCoordinates());
        assertEquals(90f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankRotateNotMoveUpObstacle() {
        Tank tank = new Tank(Direction.UP, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        Tree tree = new Tree(new GridPoint2(1, 1), 0f);
        tank.moveUp(tree.getCoordinates(), Direction.UP);

        assertEquals(new GridPoint2(1, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(90f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankRotateMoveRightNoObstacle() {

        Tank tank = new Tank(Direction.UP, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        tank.moveRight(new GridPoint2(2, 2), Direction.RIGHT);

        assertEquals(new GridPoint2(2, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(0f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankRotateNotMoveRightObstacle() {

        Tank tank = new Tank(Direction.UP, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        Tree tree = new Tree(new GridPoint2(2, 0), 0f);
        tank.moveRight(tree.getCoordinates(), Direction.RIGHT);

        assertEquals(new GridPoint2(1, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(0f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankRotateMoveDownNoObstacle() {
        Tank tank = new Tank(Direction.UP, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        tank.moveDown(new GridPoint2(2, 2), Direction.DOWN);

        assertEquals(new GridPoint2(1, -1), tank.getPlayerDestinationCoordinates());
        assertEquals(-90f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankRotateNotMoveDownObstacle() {

        Tank tank = new Tank(Direction.UP, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        Tree tree = new Tree(new GridPoint2(1, -1), 0f);
        tank.moveDown(tree.getCoordinates(), Direction.DOWN);

        assertEquals(new GridPoint2(1, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(-90f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankRotateMoveLeftNoObstacle() {

        Tank tank = new Tank(Direction.UP, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        tank.moveLeft(new GridPoint2(2, 2), Direction.LEFT);

        assertEquals(new GridPoint2(0, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(-180f, tank.getPlayerRotation(), 0.1f);
    }

    @Test
    public void tankRotateNotMoveLeftObstacle() {

        Tank tank = new Tank(Direction.UP, 1f, 0.4f, new GridPoint2(1, 0), new GridPoint2(1, 0));
        Tree tree = new Tree(new GridPoint2(0, 0), 0f);
        tank.moveLeft(tree.getCoordinates(), Direction.LEFT);

        assertEquals(new GridPoint2(1, 0), tank.getPlayerDestinationCoordinates());
        assertEquals(-180f, tank.getPlayerRotation(), 0.1f);
    }
}