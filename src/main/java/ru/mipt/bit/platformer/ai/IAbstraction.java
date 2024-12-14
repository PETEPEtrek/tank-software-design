package ru.mipt.bit.platformer.ai;

import com.badlogic.gdx.math.GridPoint2;

public interface IAbstraction {
    void processMovementProgress(float deltaTime);
    GridPoint2 getCoordinates();
    float getRotation();
}