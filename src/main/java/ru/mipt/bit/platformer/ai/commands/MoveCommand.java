package ru.mipt.bit.platformer.ai.commands;

import ru.mipt.bit.platformer.Direction.Direction;
import ru.mipt.bit.platformer.ai.ICommand;
import ru.mipt.bit.platformer.abstractions.Moving;

public class MoveCommand implements ICommand {
    private final Moving moving;
    private final Direction direction;

    public MoveCommand(Moving moving, Direction direction) {
        this.moving = moving;
        this.direction = direction;
    }

    @Override
    public void execute() {
        moving.move(direction);
    }
}