package ru.mipt.bit.platformer.abstractions;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Collection;

public interface Collidability {
    default boolean findCollision(Collidability collidableObject) {
        for (GridPoint2 firstObject : this.getCoordinateList()) {
            for (GridPoint2 secondObject : collidableObject.getCoordinateList()) {

                if (firstObject.equals(secondObject)) {
                    return true;
                }

            }
        }
        return false;
    }

    Collection<GridPoint2> getCoordinateList();
}
