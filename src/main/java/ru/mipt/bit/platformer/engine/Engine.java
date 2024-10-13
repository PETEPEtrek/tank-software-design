package ru.mipt.bit.platformer.engine;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.MoveChecker;
import ru.mipt.bit.platformer.abstractions.Tank;

import java.util.List;

//refactored class for moving tank
public class Engine {

    private final Tank tank;
    private final MoveChecker moveChecker;

    public Engine(Tank tank) {
        this.tank = tank;
        this.moveChecker = new MoveChecker(tank);
    }

    private float getDeltaTime() {
        return Gdx.graphics.getDeltaTime();
    }

    public void doCalculations() {
        moveChecker.checkMoves();
        tank.processMovementProgress(getDeltaTime());
    }

}