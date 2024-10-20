package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;

import ru.mipt.bit.platformer.abstractions.Tank;
import ru.mipt.bit.platformer.abstractions.Tree;
import ru.mipt.bit.platformer.Direction.Direction;
import ru.mipt.bit.platformer.ai.ICommand;
import ru.mipt.bit.platformer.ai.commands.*;

import java.util.ArrayList;
import java.util.List;


import static com.badlogic.gdx.Input.Keys.*;

public class MoveChecker {

    private final Tank tank;

    public MoveChecker(Tank tank) {
        this.tank = tank;
    }

    public ICommand checkMoves() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            return new MoveUpCommand(tank);
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
           return new MoveLeftCommand(tank);
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return new MoveDownCommand(tank);
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return new MoveRightCommand(tank);
        }
        return new StayCommand(tank);
    }
}
