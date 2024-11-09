package ru.mipt.bit.platformer.ai.commands;

import ru.mipt.bit.platformer.Direction.Direction;
import ru.mipt.bit.platformer.ai.ICommand;
import ru.mipt.bit.platformer.abstractions.Tank;

public class MoveDownCommand implements ICommand {
    private final Tank tank;

    public MoveDownCommand(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void doCommand() {
        tank.move(Direction.DOWN);
    }
}