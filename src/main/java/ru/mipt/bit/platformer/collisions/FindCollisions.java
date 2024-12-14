package ru.mipt.bit.platformer.collisions;

import ru.mipt.bit.platformer.abstractions.Collidability;
import ru.mipt.bit.platformer.eventmanager.Events;
import ru.mipt.bit.platformer.eventmanager.EventListener;
import ru.mipt.bit.platformer.abstractions.*;

import java.util.List;

public class FindCollisions implements EventListener {
    private final List<Collidability> collidableObjects;

    public FindCollisions(List<Collidability> collidableObjects) {
        this.collidableObjects = collidableObjects;
    }

    @Override
    public void update(Events event, Object object) {
        if (event.equals(Events.DELETE_TANK)) {
            collidableObjects.remove((Tank) object);
        }
        if (event.equals(Events.CREATE_BULLET)) {
            collidableObjects.add((Bullet) object);
        }
        if (event.equals(Events.DELETE_BULLET)) {
            collidableObjects.remove((Bullet) object);
        }
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
