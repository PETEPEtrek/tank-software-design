package ru.mipt.bit.platformer.abstractions;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Collection;
import java.util.Collections;

public class Tree  implements Collidability {
    private final float rotation;
    private final GridPoint2 treeObjectCoordinates;

    public Tree(GridPoint2 treeObjectCoordinates, float rotation) {
        this.rotation = rotation;
        this.treeObjectCoordinates = treeObjectCoordinates;
    }

    public float getRotation() {
        return rotation;
    }

    public GridPoint2 getCoordinates() {
        return treeObjectCoordinates;
    }

    @Override
    public Collection<GridPoint2> getCoordinateList() {
        return Collections.singleton(treeObjectCoordinates);
    }

}
