package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;

import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.abstractions.Tree;
import ru.mipt.bit.platformer.Direction.Direction;

import java.util.ArrayList;
import java.util.List;


import static com.badlogic.gdx.Input.Keys.*;

public class MoveChecker {

    private final Tank tank;

    public MoveChecker(Tank tank) {
        this.tank = tank;
    }

    public void checkMoves() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            if (tank.isMoving()) {
                tank.move(Direction.UP);
            }
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            if (tank.isMoving()) {
                tank.move(Direction.LEFT);
            }
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            if (tank.isMoving()) {
                tank.move(Direction.DOWN);
            }
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            if (tank.isMoving()) {
                tank.move(Direction.RIGHT);
            }
        }
    }
}
