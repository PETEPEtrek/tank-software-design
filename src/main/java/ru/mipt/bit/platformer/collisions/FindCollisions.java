package ru.mipt.bit.platformer.collisions;

import ru.mipt.bit.platformer.abstractions.Collidability;

import java.util.List;

public class FindCollisions {
    private final List<Collidability> collidableObjects;

    public FindCollisions(List<Collidability> collidableObjects) {
        this.collidableObjects = collidableObjects;
    }

    public void addCollidable(Collidability collidable) {
        collidableObjects.add(collidable);
    }

    public boolean hasCollisions(Collidability collidable) {
        for (Collidability collidableObject : collidableObjects) {
            if (collidable.findCollision(collidableObject) && !collidable.equals(collidableObject)) {
                return true;
            }
        }
        return false;
    }
}
