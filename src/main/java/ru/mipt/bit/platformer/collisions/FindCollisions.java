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

    public void deleteCollidable(Collidability collidable) {
        collidableObjects.remove(collidable);
    }

    public boolean hasCollisions(Collidability collidable) {
        for (Collidability collidableObject : collidableObjects) {
            if (!collidable.equals(collidableObject) && collidable.findCollision(collidableObject)) {
                return true;
            }
        }
        return false;
    }

}
