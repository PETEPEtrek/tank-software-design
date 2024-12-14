package ru.mipt.bit.platformer.abstractions;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.ai.IAbstraction;

import java.util.Collection;
import java.util.Collections;

public class Tree  implements Collidability, IAbstraction {
    private final float rotation;
    private final GridPoint2 treeObjectCoordinates;

    public Tree(GridPoint2 treeObjectCoordinates, float rotation) {
        this.rotation = rotation;
        this.treeObjectCoordinates = treeObjectCoordinates;
    }
    @Override
    public float getRotation() {
        return rotation;
    }
    @Override
    public GridPoint2 getCoordinates() {
        return treeObjectCoordinates;
    }
    @Override
    public void processMovementProgress(float deltaTime) {
    }

    @Override
    public Collection<GridPoint2> getCoordinateList() {
        return Collections.singleton(treeObjectCoordinates);
    }

}
