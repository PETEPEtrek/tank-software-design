package ru.mipt.bit.platformer.abstractions;
import ru.mipt.bit.platformer.Direction.Direction;
import com.badlogic.gdx.math.GridPoint2;

public interface Moving {
	GridPoint2 getDestinationCoordinates();
    void move(Direction direction);
}